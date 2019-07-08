package edu.project.authorization.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SecurityMemberVO extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionID = 1L;

    public SecurityMemberVO(MemberVO memberVO) {
        super(memberVO.getUserId(), memberVO.getUserPasswd(), makeGrantedAuthority(memberVO.getMemberRole()));
        log.info("SecurityMemberVO>>>>>>>>>>>>>>>>>>>>>userId : " + memberVO.getUserId());
        log.info("SecurityMemberVO>>>>>>>>>>>>>>>>>>>>>password : " + memberVO.getUserPasswd());
    }

    public static List<GrantedAuthority> makeGrantedAuthority(List<MemberRoleVO> roles) {
        log.info("makeGrantedAuthority>>>>>>>>>>>>>>>>>>>>>");
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role-> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
