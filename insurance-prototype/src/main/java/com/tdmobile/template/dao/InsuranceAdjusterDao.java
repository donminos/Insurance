package com.tdmobile.template.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.InsuranceAdjuster;

@Component
public class InsuranceAdjusterDao extends CrudDao<InsuranceAdjuster> {

    public InsuranceAdjusterDao() {
	super(InsuranceAdjuster.class);
    }
    
    public InsuranceAdjuster findForIdUser(Long idUser) {
	Query query=em.createQuery("SELECT ia FROM InsuranceAdjuster ia INNER JOIN ia.idUser u WHERE u.idUser=:id",InsuranceAdjuster.class);
	query.setParameter("id", idUser);
	return (InsuranceAdjuster)query.getSingleResult();
    }

}
