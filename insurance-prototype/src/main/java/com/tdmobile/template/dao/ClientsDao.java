package com.tdmobile.template.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.Clients;

@Component
public class ClientsDao extends CrudDao<Clients>{

    public ClientsDao() {
	super(Clients.class);
	// TODO Auto-generated constructor stub
    }

    public Clients findForUser(Long idUser) {
	Query query=em.createQuery("SELECT c FROM Clients c INNER JOIN c.idUser u WHERE u.idUser=:user",Clients.class);
	query.setParameter("user", idUser);
	return (Clients)query.getSingleResult();
    }
}
