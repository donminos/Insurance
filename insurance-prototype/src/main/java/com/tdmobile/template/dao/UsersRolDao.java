package com.tdmobile.template.dao;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.UsersRol;

@Component
public class UsersRolDao extends CrudDao<UsersRol>{

    public UsersRolDao() {
	super(UsersRol.class);
    }

}
