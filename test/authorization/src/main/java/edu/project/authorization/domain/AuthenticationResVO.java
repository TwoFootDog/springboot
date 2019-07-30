package edu.project.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResVO {
    private CustomHeaderResVO header;
    private String userId;
    private Collection authorities;
    private String token;
}
