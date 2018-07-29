package com.tdmobile.template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.dao.PoliciesDao;
import com.tdmobile.template.entity.Policies;

@Service
public class PoliciesServices {

    @Autowired
    PoliciesDao policiesDao;

    @Transactional(readOnly = true)
    public List<Policies> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public List<Policies> findfield(String fieldName, Object field) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public List<Policies> findForUser(Long idUser) {
	return policiesDao.findForUser(idUser);
    }

    @Transactional(readOnly = true)
    public Policies findId(Object id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public void insert(Policies entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void edit(Policies entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void delete(Policies entity) {
	// TODO Auto-generated method stub

    }

}
