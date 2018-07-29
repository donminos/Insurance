package com.tdmobile.template.model.insuranceadjuster;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleElements implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4186167607639012592L;
    private GoogleDistance distance;
    private GoogleDuration duration;
    private String status;
}
