package com.tdmobile.template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdmobile.template.dao.UsersRolDao;
import com.tdmobile.template.entity.UsersRol;

@Service
public class UsersRolService implements ServiceImp<UsersRol>{
    
    @Autowired
    private UsersRolDao usersRolDao;

    @Override
    public List<UsersRol> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<UsersRol> findfield(String fieldName, Object field) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public UsersRol findId(Object id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void insert(UsersRol entity) {
	usersRolDao.persist(entity);
    }

    @Override
    public void edit(UsersRol entity) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void delete(UsersRol entity) {
	usersRolDao.remove(entity);
	
    }
    
    
}
