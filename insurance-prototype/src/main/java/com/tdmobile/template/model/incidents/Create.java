package com.tdmobile.template.model.incidents;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Create implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -3345956583602482870L;
    
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;

}
