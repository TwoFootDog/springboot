package edu.project.authorization.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomHeaderResVO {
    private boolean isSuccess;
    private String code;
    private String msg;
}
