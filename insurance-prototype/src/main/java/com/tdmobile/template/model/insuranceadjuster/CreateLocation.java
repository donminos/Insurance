package com.tdmobile.template.model.insuranceadjuster;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLocation implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 239913468784789128L;
    private String provider;
    private Date time;
    private float latitude;
    private float longitude;
    private int accuracy;
    private int altitude;
    private int locationProvider;
}
//[{"provider":"network","time":1526238405803,"latitude":19.2013207,"longitude":-99.0509185,"accuracy":20,"altitude":2441,"locationProvider":0}]