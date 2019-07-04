package edu.project.authorization.controller;

import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

//    @ResponseBody
    @GetMapping(value = "/login")
    public Object login() {
        return new Object();
    }

    @PostMapping(value = "/register")
    public MemberVO register(@RequestBody MemberVO memberVO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVO.setUserPasswd(passwordEncoder.encode(memberVO.getUserPasswd()));
        MemberRoleVO memberRoleVO = new MemberRoleVO();
        memberRoleVO.setRoleName("MASTER");
        memberVO.setMemberRole(Arrays.asList(memberRoleVO));
        return memberRepository.save(memberVO);
    }
}
