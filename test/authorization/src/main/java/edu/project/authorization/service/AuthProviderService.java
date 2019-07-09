package edu.project.authorization.service;

import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        log.info("id >>>>>>>>>>>>>> " + id);
        log.info("passwd >>>>>>>>>>>>>> " + passwd);

        MemberVO memberVO = memberRepository.findByUserId(id);
//        if (memberVO == null || !memberVO.getUserPasswd().equals(passwd)) {
//        if (memberVO == null) {
//             return null;
//        }
        memberVO.setUserPasswd(null);

        List<GrantedAuthority> grantedAuthoritiyList = new ArrayList<>();
        grantedAuthoritiyList.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(memberVO, null, grantedAuthoritiyList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
