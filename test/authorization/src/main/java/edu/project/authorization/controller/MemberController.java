package edu.project.authorization.controller;

import edu.project.authorization.domain.AuthenticationRequestVO;
import edu.project.authorization.domain.AuthenticationTokenVO;
import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;


@CrossOrigin("*")
@RestController
//@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping(value = "/signin")
    public AuthenticationTokenVO signIn(@RequestBody AuthenticationRequestVO authenticationRequestVO, HttpSession httpSession) {

        String userId = authenticationRequestVO.getUserId();
        String userPasswd = authenticationRequestVO.getUserPasswd();
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>signin" + userId + userPasswd);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//        MemberVO memberVO = memberRepository.findByUserId(userId);

//        log.info("signIn>>>>>>>>>>>>> : " + memberVO.getUserId() + memberVO.getMemberRole() + httpSession.getId());
        return new AuthenticationTokenVO(authentication.getName(), authentication.getAuthorities(), httpSession.getId());
//        return new AuthenticationTokenVO(memberVO.getUserId(), memberVO.getMemberRole(), httpSession.getId());
    }

    @GetMapping(value = "/signout")
    public String signOut() {
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>singout");
        return "signout";
    }

    @PostMapping(value = "/signup")
    public MemberVO signUp(@RequestBody MemberVO memberVO) {
        log.info("memberVO : " + memberVO);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVO.setUserPasswd(passwordEncoder.encode(memberVO.getUserPasswd()));
        MemberRoleVO memberRoleVO = new MemberRoleVO();
        memberRoleVO.setRoleName("MASTER");
        memberVO.setMemberRole(Arrays.asList(memberRoleVO));
        return memberRepository.save(memberVO);
    }
}
