package edu.project.authorization.service;

import edu.project.authorization.domain.MemberVO;
import edu.project.authorization.domain.SecurityMemberVO;
import edu.project.authorization.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userDetails : " + username);
        MemberVO memberVO = memberRepository.findByUserId(username);
        User user = new SecurityMemberVO(memberVO);
        return user;
//            return new SecurityMemberVO();
//        return Optional.ofNullable(memberRepository.findByUserId(username))
//                .filter(m -> m != null)
//                .map(m -> new SecurityMemberVO(m)).get();
    }
}
