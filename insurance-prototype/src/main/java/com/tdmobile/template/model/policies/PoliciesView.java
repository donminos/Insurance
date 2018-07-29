package com.tdmobile.template.model.policies;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoliciesView implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3317184906229938748L;
    
    private String idPolicies;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private String registryDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private String finishPolice;
    private Integer monthDuration;
    

}
