package com.tdmobile.template.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdmobile.template.dao.HistoryLocationsAdjusterDao;
import com.tdmobile.template.dao.InsuranceAdjusterDao;
import com.tdmobile.template.dao.UsersDao;
import com.tdmobile.template.entity.HistoryLocationsAdjuster;
import com.tdmobile.template.entity.InsuranceAdjuster;
import com.tdmobile.template.entity.Users;
import com.tdmobile.template.model.insuranceadjuster.CreateLocation;

@Service
public class InsuranceAdjusterService {

    @Autowired
    InsuranceAdjusterDao insuranceAdjusterDao;

    @Autowired
    UsersDao usersDao;

    @Autowired
    HistoryLocationsAdjusterDao historyLocationsAdjusterDao;

    @Transactional(readOnly = true)
    public List<InsuranceAdjuster> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public List<InsuranceAdjuster> findfield(String fieldName, Object field) {
	return insuranceAdjusterDao.findfield(fieldName, field);
    }

    @Transactional(readOnly = true)
    public InsuranceAdjuster findId(Object id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public void insert(InsuranceAdjuster entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void edit(InsuranceAdjuster entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void delete(InsuranceAdjuster entity) {
	// TODO Auto-generated method stub

    }

    @Transactional
    public void registerLocation(CreateLocation location, String token) {
	// http://maps.google.com/maps/api/geocode/json?address=19.4367372,-99.1843784
	// podria servir para algo
	Users user = usersDao.findfield("Token", token).get(0);
	InsuranceAdjuster ia = insuranceAdjusterDao.findForIdUser(user.getIdUser());
	ia.setLatitude(String.valueOf(location.getLatitude()));
	ia.setLongitude(String.valueOf(location.getLongitude()));
	insuranceAdjusterDao.merge(ia);
	historyLocationsAdjusterDao.persist(new HistoryLocationsAdjuster(ia.getIdInsuranceAdjuster(),
		String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), location.getProvider(),
		location.getTime(), location.getAccuracy(), location.getAltitude(), location.getLocationProvider()));
    }

}
