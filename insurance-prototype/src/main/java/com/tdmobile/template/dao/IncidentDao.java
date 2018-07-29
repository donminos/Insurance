package com.tdmobile.template.dao;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.Incidents;

@Component
public class IncidentDao extends CrudDao<Incidents> {

    public IncidentDao() {
	super(Incidents.class);
	// TODO Auto-generated constructor stub
    }

}
