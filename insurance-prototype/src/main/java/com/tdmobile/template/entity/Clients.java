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
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Clients implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7244431977707722505L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Long idClient;
    @Basic(optional = true)
    @Size(min = 1, max = 120)
    @Column(name = "Street")
    private String street;
    @Basic(optional = true)
    @Size(min = 1, max = 120)
    @Column(name = "Settlement")
    private String settlement;
    @Basic(optional = true)
    @Size(min = 1, max = 120)
    @Column(name = "Town")
    private String town;
    @Basic(optional = true)
    @Size(min = 1, max = 120)
    @Column(name = "City")
    private String city;
    @Basic(optional = true)
    @Size(min = 1, max = 120)
    @Column(name = "State")
    private String state;
    @Basic(optional = true)
    @Size(min = 5, max = 5)
    @Column(name = "ZipCode")
    private String zipCode;
    @Basic(optional = true)
    @Size(min = 10, max = 10)
    @Column(name = "Telephone")
    private String Telephone;
    @Basic(optional = true)
    @Size(min = 10, max = 10)
    @Column(name = "Mobile")
    private String mobile;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @OneToOne(optional = false)
    private Users idUser;
}
