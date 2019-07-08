package edu.project.authorization.controller;

import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@CrossOrigin("*")
@RestController
//@RequestMapping("/member")
@Slf4j
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping(value = "/signin")
    public String signIn() {
        log.info("controller login>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>signin");
        return "signin";
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
