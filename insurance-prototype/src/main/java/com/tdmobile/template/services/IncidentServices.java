package com.tdmobile.template.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdmobile.template.dao.IncidentDao;
import com.tdmobile.template.dao.InsuranceAdjusterDao;
import com.tdmobile.template.entity.Incidents;
import com.tdmobile.template.entity.InsuranceAdjuster;
import com.tdmobile.template.entity.Policies;
import com.tdmobile.template.model.incidents.Create;
import com.tdmobile.template.model.insuranceadjuster.AdjusterDistance;
import com.tdmobile.template.model.insuranceadjuster.GoogleReponse;

@Service
public class IncidentServices {

    @Autowired
    IncidentDao incidentDao;
    
    @Autowired
    InsuranceAdjusterDao insuranceAdjusterDao;
    
    @Transactional(readOnly = true)
    public List<Incidents> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public List<Incidents> findfield(String fieldName, Object field) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional(readOnly = true)
    public Incidents findId(Object id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Transactional
    public void insert(Create entity) throws ClientProtocolException, IOException{
	List<InsuranceAdjuster> adjuster=insuranceAdjusterDao.findfield("inFunctions", true);
	List<AdjusterDistance> distances=new ArrayList<AdjusterDistance>();
	adjuster.forEach((ad)->{
	    double km=this.calculatePointDistance(ad.getLatitude(),ad.getLongitude(),entity.getLatitude(),entity.getLongitude());
	    distances.add(new AdjusterDistance(ad.getIdInsuranceAdjuster(),km,ad.getLatitude(),ad.getLongitude()));
	});
	Collections.sort(distances, AdjusterDistance.DistanceComparator);
	for(int i=0;i<=1;i++) {
	    HttpClient httpClient = HttpClientBuilder.create().build();
	    HttpGet request = new HttpGet("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+distances.get(i).getLatitude()+","+distances.get(i).getLongitude()+"&destinations="+entity.getLatitude()+","+entity.getLongitude()+"&mode=driving&language=es-US&key=AIzaSyCJN_zqti_la5Rn6yvTLjX2B-Mj2kVGmG8");
	    request.addHeader("Content-Type", "application/json");
	    HttpResponse response = httpClient.execute(request);
	    //System.out.println(response.getStatusLine().getStatusCode());
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

	    StringBuffer result = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) {
		result.append(line);
	    }
	    System.out.println(result);
	    ObjectMapper objectMapper = new ObjectMapper();
	    GoogleReponse resp = objectMapper.readValue(result.toString(), GoogleReponse.class);
	    distances.get(i).setTimeTraslate(resp.getRows().get(0).getElements().get(0).getDuration().getValue());
	    
	}
	Collections.sort(distances, AdjusterDistance.traslateComparator);
	System.out.println("idMas Cercano"+distances.get(0).getIdInsuranceAdjuster());
	ModelMapper modelMapper = new ModelMapper();
	Incidents incident = modelMapper.map(entity, Incidents.class);
	incident.setIdPolicies(new Policies("a7dfccbc-fe92-478c-9caf-b938b2ce778a"));
	incidentDao.persist(incident);
    }

    @Transactional(readOnly = true)
    public void edit(Incidents entity) {
	// TODO Auto-generated method stub

    }

    @Transactional(readOnly = true)
    public void delete(Incidents entity) {
	// TODO Auto-generated method stub

    }
    
    private double calculatePointDistance(String adLat,String adLong,String incLat,String incLong) {
	double deg2radMultiplier =Math.PI / 180;
	double lat1 = Double.parseDouble(adLat) * deg2radMultiplier;
	double lon1 = Double.parseDouble(adLong) * deg2radMultiplier;
	double lat2 = Double.parseDouble(incLat) * deg2radMultiplier;
	double lon2 = Double.parseDouble(incLong) * deg2radMultiplier;
	
	double radius = 6378.137; // earth mean radius defined by WGS84
        double dlon = lon2 - lon1;
        double distance = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(dlon)) * radius;
        System.out.println(distance);
        
        /*Convertidor de distancias KM, Millas o N
         * if (unit == 'K') {
                return (distance);
            } else if (unit == 'M') {
                return (distance * 0.621371192);
            } else if (unit == 'N') {
                return (distance * 0.539956803);
            } else {
                return 0;
            }
         */
        return distance;
    }

}
