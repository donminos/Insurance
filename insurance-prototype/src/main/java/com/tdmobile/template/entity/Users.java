package com.tdmobile.template.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(columnNames = { "User" }),
	@UniqueConstraint(columnNames = { "Email" }) })
public class Users implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Long idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "User")
    private String user;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PrimaryLastName")
    private String primaryLastName;
    @Size(max = 45)
    @Column(name = "SecundaryLastName")
    private String secundaryLastName;
    @Email
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "Email")
    private String email;
    @Column(name = "RegistrationDate", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    // @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "Password")
    private String password;
    @JoinColumn(name = "idUsersStatus", referencedColumnName = "idUsersStatus")
    @ManyToOne(optional = false)
    private UsersStatus idUsersStatus;
    @Size(max = 200)
    @Column(name = "Token")
    private String token;
    @Size(max = 200)
    @Column(name = "TokenSystem")
    private String tokenSystem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.EAGER)
    private List<UsersRol> userRoleList;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Clients dataClients;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUser")
    private InsuranceAdjuster adjuster;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private List<Policies> policies;
}
