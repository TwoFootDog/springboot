package edu.project.authorization.controller;

import edu.project.authorization.domain.*;
import edu.project.authorization.repository.MemberRepository;
import edu.project.authorization.service.UserAuthService;
import edu.project.authorization.util.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Iterator;


@CrossOrigin("*")
@RestController
//@RequestMapping("/auth")
@Slf4j
public class MemberController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    UserAuthService userAuthService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    /* 로그인 API */
    @PostMapping(value = "/signin")
    public AuthenticationTokenVO signIn(@RequestBody AuthenticationRequestVO authenticationRequestVO, HttpSession httpSession) {
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        String userId = authenticationRequestVO.getUserId();
        String userPasswd = authenticationRequestVO.getUserPasswd();
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>signin" + userId + userPasswd);

        /* token 생성 후 token을 파라미터로 인증 진행.  Spring Security에서 설정한 인증 적용됨 */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, userPasswd);
        Authentication authentication = authenticationManager.authenticate(token);
        log.info("token>>>>>>>>>>>>>>>>: " + token);
        log.info("httpSession.getId()>>>>>>>>>>>>>>>>: " + httpSession.getId());

        /* 인증받은 결과를 SecurityContextHolder에서 getContext()를 통해 context로 받아온 후,
        이 Context 인증결과를 Set해줌. 이로써 서버의 SecurityContext에는 인증값이 설정됨. */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /* Session의 속성값에 SecurityContext값을 넣어줌 */
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        /* Jwt 생성 */
        String jwt = jwtTokenProvider.generateToken(authentication);
        return new AuthenticationTokenVO(authentication.getName(), authentication.getAuthorities(), jwt);
    }

    /* 로그아웃 API */
    @PostMapping(value = "/signout")
    public String signOut() {
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>singout");
        return "signout";
    }

    /* 회원가입 API */
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
//        try {
//            MemberVO memberVO1 = memberRepository.save(memberVO);
//
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberVO.getUserId(), memberVO.getUserPasswd());
//            Authentication authentication = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        } catch (Exception e) {
//            log.info("Signup Fail");
//        }

        return memberRepository.save(memberVO);
    }


    /* user의 정보를 가져오는 API. 입력값은 현재 설정된 user의 principal 정보를 가져옴 */
    @GetMapping(value = "/getUserInfo")
    public GetUserInfoResVO getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        MemberVO memberVO = memberRepository.findByUserId(userDetails.getUsername());   // 현재 principal에 셋팅된 userId로 user정보 조회

        GetUserInfoResVO response = new GetUserInfoResVO();
        response.setUserId(userDetails.getUsername());
        response.setAuthorities(userDetails.getAuthorities());
        response.setUserEmaill(memberVO.getUserEmail());
        response.setUserFirstName(memberVO.getUserFirstName());
        response.setUserLastName(memberVO.getUserLastName());

        return response;
    }
}