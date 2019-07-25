package edu.project.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoResVO {
    private String userId;
    private String userEmaill;
    private String userFirstName;
    private String userLastName;
    private Collection authorities;
}
