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

        /* token 생성 후 token을 파라미터로 인증 진행.  Spring Security에서 설정한 인증 적용됨 */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        Authentication authentication = authenticationManager.authenticate(token);

        /* 인증받은 결과를 SecurityContextHolder에서 getContext()를 통해 context로 받아온 후,
        이 Context 인증결과를 Set해줌. 이로써 서버의 SecurityContext에는 인증값이 설정됨. */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /* Session의 속성값에 SecurityContext값을 넣어줌*/
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return new AuthenticationTokenVO(authentication.getName(), authentication.getAuthorities(), httpSession.getId());
    }

    @GetMapping(value = "/signout")
    public String signOut() {
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>singout");
        return "signout";
    }

    @PostMapping(value = "/signup")
    public MemberVO signUp(@RequestBody AuthenticationRequestVO authenticationRequestVO, HttpSession httpSession) {

        MemberRoleVO memberRoleVO = new MemberRoleVO();
        memberRoleVO.setRoleName("USER");

        MemberVO memberVO = new MemberVO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVO.setUserPasswd(passwordEncoder.encode(authenticationRequestVO.getUserPasswd()));
        memberVO.setUserId(authenticationRequestVO.getUserId());
        memberVO.setUserEmail(authenticationRequestVO.getUserEmail());
        memberVO.setUserFirstName(authenticationRequestVO.getUserFirstName());
        memberVO.setUserLastName(authenticationRequestVO.getUserLastName());
        memberVO.setMemberRole(Arrays.asList(memberRoleVO));
        return memberRepository.save(memberVO);
    }
}