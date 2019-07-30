package edu.project.authorization.service;

import edu.project.authorization.domain.MemberRoleVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserAuthService extends UserDetailsService {
    public List<GrantedAuthority> makeGrantedAuthority(List<MemberRoleVO> role);
//    public AuthenticationResVO userSignIn(AuthenticationReqVO authenticationRequestVO, HttpSession httpSession);
//    public AuthenticationResVO userSignUp(AuthenticationReqVO authenticationRequestVO, HttpSession httpSession);
}
