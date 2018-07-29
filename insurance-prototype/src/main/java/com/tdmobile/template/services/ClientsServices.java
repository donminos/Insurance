package com.tdmobile.template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.dao.ClientsDao;
import com.tdmobile.template.entity.Clients;

@Service
public class ClientsServices {
    @Autowired
    ClientsDao clientsDao;

    @Transactional(readOnly = true)
    public List<Clients> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public List<Clients> findfield(String fieldName, Object field) {
	return clientsDao.findfield(fieldName, field);
    }
    
    @Transactional(readOnly = true)
    public Clients findForUser(Long idUser) {
	return clientsDao.findForUser(idUser);
    }

    @Transactional(readOnly = true)
    public Clients findId(Object id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public void insert(Clients entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void edit(Clients entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void delete(Clients entity) {
	// TODO Auto-generated method stub

    }

}
