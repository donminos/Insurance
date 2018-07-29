package com.tdmobile.template.dao;

import org.springframework.stereotype.Component;

import com.tdmobile.template.entity.HistoryLocationsAdjuster;

@Component
public class HistoryLocationsAdjusterDao extends CrudDao<HistoryLocationsAdjuster>{

    public HistoryLocationsAdjusterDao() {
	super(HistoryLocationsAdjuster.class);
	// TODO Auto-generated constructor stub
    }

}
