package com.tdmobile.template.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.dao.RolesDao;
import com.tdmobile.template.entity.Roles;

@Service
public class RolesService {
    @Autowired
    private RolesDao rolesSession;

    @Transactional(readOnly = true)
    public List<Roles> findAll() {
	return rolesSession.findAll();
    }

    @Transactional(readOnly = true)
    public List<Roles> findUser(String idRoles) {
	return null;
    }

    @Transactional(readOnly = true)
    public Roles findId(Integer idRoles) {
	return null;
    }

    @Transactional
    public void insert() {
    }

    @Transactional
    public void edit() {
    }
}
