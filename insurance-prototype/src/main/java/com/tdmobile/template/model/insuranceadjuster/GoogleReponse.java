package com.tdmobile.template.model.insuranceadjuster;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleReponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8616069631281585681L;
    private String[] destination_addresses;
    private String[] origin_addresses;
    private List<GoogleRows> rows;
    private String status;
}
