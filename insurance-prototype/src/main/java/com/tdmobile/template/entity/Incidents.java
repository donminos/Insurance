package com.tdmobile.template.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Incidents")
public class Incidents implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3148143786078564646L;

    @PrePersist
    private void prePersist() {
	this.idIncident = UUID.randomUUID().toString();
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idIncident;
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
    @Column(name = "RegistryDate", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    // @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registryDate;
    
    @JoinColumn(name = "idPolicies", referencedColumnName = "idPolicies")
    @ManyToOne(optional = false)
    private Policies idPolicies;
}
