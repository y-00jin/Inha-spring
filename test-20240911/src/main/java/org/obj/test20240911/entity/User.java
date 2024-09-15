package org.obj.test20240911.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description    : User 객체 dto로 생각
 * packageName    : org.obj.test20240911.entity
 * fileName        : User
 * author         : kimminsol
 * date           : 9/11/24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 9/11/24        kimminsol       최초 생성
 */
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
