package edu.project.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthenticationTokenVO {
    private String userId;
    private Collection authorities;
    private String token;
}
