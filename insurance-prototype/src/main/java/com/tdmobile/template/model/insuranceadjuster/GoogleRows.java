package com.tdmobile.template.model.insuranceadjuster;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleRows implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -2454770769764540481L;
    private List<GoogleElements> elements;
}
