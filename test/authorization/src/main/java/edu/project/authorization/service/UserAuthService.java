package edu.project.authorization.service;

import edu.project.authorization.domain.AuthenticationRequestVO;
import edu.project.authorization.domain.AuthenticationTokenVO;
import edu.project.authorization.domain.MemberRoleVO;
import edu.project.authorization.domain.MemberVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserAuthService extends UserDetailsService {
    public List<GrantedAuthority> makeGrantedAuthority(List<MemberRoleVO> role);
//    public AuthenticationTokenVO userSignIn(AuthenticationRequestVO authenticationRequestVO, HttpSession httpSession);
//    public AuthenticationTokenVO userSignUp(AuthenticationRequestVO authenticationRequestVO, HttpSession httpSession);
}
