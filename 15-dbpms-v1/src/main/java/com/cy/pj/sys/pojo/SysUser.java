package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1337911850311626879L;
    private Integer id;
    private String username;
    private String password;
    private String salt;//盐值 md5
    private String email;
    private String mobile;
    private Integer valid=1;
    private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
