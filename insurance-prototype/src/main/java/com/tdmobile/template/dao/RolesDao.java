package com.tdmobile.template.dao;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.Roles;

@Component
public class RolesDao extends CrudDao<Roles>{

	public RolesDao() {
		super(Roles.class);
	}

}
