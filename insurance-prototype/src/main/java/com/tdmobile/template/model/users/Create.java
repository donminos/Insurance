package com.tdmobile.template.model.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Create implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer idUser;
    @NotEmpty
    @Size(min = 1, max = 45)
    private String user;
    @NotEmpty
    @Size(min = 1, max = 45)
    private String name;
    @Basic(optional = false)
    @NotEmpty
    @Size(min = 1, max = 45)
    private String primaryLastName;
    @Size(max = 45)
    private String secundaryLastName;
    @Email
    @NotEmpty
    @Size(min = 1, max = 60)
    private String email;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDate;
    private String password;
    private Integer status;
    @Size(max = 40)
    private String token;
    @Size(max = 40)
    private String tokenSystem;
    @NotEmpty
    private List<Integer> userRoles;
}
