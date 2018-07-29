package com.tdmobile.template.dao;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.Users;

@Component
public class UsersDao extends CrudDao<Users>{

	public UsersDao() {
		super(Users.class);
	}

}
