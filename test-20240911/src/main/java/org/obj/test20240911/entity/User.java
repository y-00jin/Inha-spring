package org.obj.test20240911.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
}
