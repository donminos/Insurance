package com.tdmobile.template.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.Policies;

@Component
public class PoliciesDao extends CrudDao<Policies> {
    public PoliciesDao() {
	super(Policies.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<Policies> findForUser(Long idUser){
	Query query=em.createQuery("SELECT p FROM Policies p INNER JOIN p.idUser u WHERE u.idUser=:user",Policies.class);
	query.setParameter("user", idUser);
	return query.getResultList(); 
    }
}
