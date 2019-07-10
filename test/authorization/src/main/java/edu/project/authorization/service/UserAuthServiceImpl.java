package edu.project.authorization.service;

import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    MemberRepository memberRepository;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = memberRepository.findByUserId(username);
        User user = new User(memberVO.getUserId(), memberVO.getUserPasswd(), makeGrantedAuthority(memberVO.getMemberRole()));
        return user;
    }

    @Override
    public List<GrantedAuthority> makeGrantedAuthority(List<MemberRoleVO> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        roles.forEach(role-> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
        return list;
    }
}
