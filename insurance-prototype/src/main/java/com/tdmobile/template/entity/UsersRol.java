package com.tdmobile.template.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "UsersRol",uniqueConstraints= {@UniqueConstraint(columnNames={"idRoles","idUser"})})
public class UsersRol implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idUsersRol")
	private Long idUsersRol;
	@JoinColumn(name = "idRoles", referencedColumnName = "idRoles")
	@ManyToOne(optional = false)
	private Roles idRoles;
	@JoinColumn(name = "idUser", referencedColumnName = "idUser")
	@ManyToOne(optional = false)
	private Users idUser;

}
