package com.tdmobile.template.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "InsuranceAdjuster")
public class InsuranceAdjuster implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2950084204898519105L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInsuranceAdjuster")
    private Long idInsuranceAdjuster;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "PrimaryLastName")
    private String primaryLastName;
    @Basic(optional = true)
    @Size(min = 1, max = 60)
    @Column(name = "SecundaryLastName")
    private String secundaryLastName;
    @Basic(optional = true)
    @Column(name = "InFunctions", columnDefinition = "tinyint(1) default 1")
    private Boolean inFunctions;
    /*@OneToMany(cascade = CascadeType.DETACH, mappedBy = "idInsuranceAdjuster", fetch = FetchType.LAZY)
    private List<LocationAdjuster> lstLocations;*/
    @Basic(optional = true)
    @Column(name = "latitude")
    private String latitude;
    @Basic(optional = true)
    @Column(name = "longitude")
    private String longitude;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @OneToOne(optional = false)
    private Users idUser;
    
}
