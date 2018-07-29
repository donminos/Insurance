package com.tdmobile.template.model.users;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tdmobile.template.model.policies.PoliciesView;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8174506379868300696L;

    private String user;
    private String name;
    private String primaryLastName;
    private String secundaryLastName;
    private String email;
    private String street;
    private String settlement;
    private String town;
    private String city;
    private String state;
    private String zipCode;
    private String Telephone;
    private String mobile;
    private String token;
    private String role;
    private List<PoliciesView> policies;
}
