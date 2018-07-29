package com.tdmobile.template.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HistoryLocationsAdjuster")
public class HistoryLocationsAdjuster implements Serializable {
    /**
     * 
     */
    public HistoryLocationsAdjuster() {

    }

    public HistoryLocationsAdjuster(Long idInsuranceAdjuster, String latitude, String longitude, String provider,
	    Date shippingTime, int accuracy, int altitude, int locationProvider) {
	this.idInsuranceAdjuster = idInsuranceAdjuster;
	this.latitude = latitude;
	this.longitude = longitude;
	this.provider = provider;
	this.shippingTime = shippingTime;
	this.accuracy = accuracy;
	this.altitude = altitude;
	this.locationProvider = locationProvider;
    }

    private static final long serialVersionUID = -2171954826897935156L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLocationAdjuster")
    private Long idLocationAdjuster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Latitude")
    private String latitude;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "Longitude")
    private String longitude;
    @Basic(optional = false)
    @Column(name = "DateRegister", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private Date dateRegister;
    @Basic(optional = false)
    @Column(name = "Provider")
    @Size(min = 1, max = 20)
    private String provider;
    @Basic(optional = true)
    @Column(name = "ShippingTime")
    private Date shippingTime;
    @Basic(optional = true)
    @Column(name = "Accuracy")
    private int accuracy;
    @Basic(optional = true)
    @Column(name = "Altitude")
    private int altitude;
    @Basic(optional = true)
    @Column(name = "LocationProvider")
    private int locationProvider;

    /*
     * @JoinColumn(name = "idInsuranceAdjuster", referencedColumnName =
     * "idInsuranceAdjuster")
     * 
     * @ManyToOne(optional = false) private InsuranceAdjuster idInsuranceAdjuster;
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInsuranceAdjuster")
    private Long idInsuranceAdjuster;
}
