package com.tdmobile.template.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Policies")
public class Policies implements Serializable {
    public Policies() {

    }

    public Policies(String idPolicies) {
	this.idPolicies = idPolicies;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -2479344316392462706L;

    @PrePersist
    private void prePersist() {
	this.idPolicies = UUID.randomUUID().toString();
	this.finishPolice = DateUtils.addMonths(this.registryDate, this.monthDuration);
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idPolicies;
    @Basic(optional = false)
    @Column(name = "RegistryDate", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    // @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registryDate;
    @Basic(optional = false)
    @Column(name = "MonthDuration")
    @NotNull
    private Integer monthDuration;
    @Basic(optional = false)
    @Column(name = "FinishPolice", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    // @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date finishPolice;
    @Basic(optional = false)
    @Column(name = "Paid", columnDefinition = "tinyint(1) default 1")
    @NotNull
    private Boolean Paid;

    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private Users idUser;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "idPolicies", fetch = FetchType.LAZY)
    private List<Incidents> lstIncidents;
}
