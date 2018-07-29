package com.tdmobile.template.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UsersStatus")
public class UsersStatus implements Serializable {
    /**
    * 
    */
    public UsersStatus() {
    }

    public UsersStatus(Long idUsersStatus) {
	this.idUsersStatus = idUsersStatus;
    }

    private static final long serialVersionUID = -1526300928685883584L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsersStatus")
    private Long idUsersStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsersStatus")
    private List<Users> idUserList;
}
