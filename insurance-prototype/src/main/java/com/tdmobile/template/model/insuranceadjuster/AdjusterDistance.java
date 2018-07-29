package com.tdmobile.template.model.insuranceadjuster;

import java.util.Comparator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdjusterDistance {

    public AdjusterDistance() {

    }

    public AdjusterDistance(Long idInsuranceAdjuster, Double distance, String latitude, String longitude) {
	this.idInsuranceAdjuster = idInsuranceAdjuster;
	this.distance = distance;
	this.latitude = latitude;
	this.longitude = longitude;
	this.timeTraslate=9999;
    }

    private Long idInsuranceAdjuster;
    private double distance;
    private String latitude;
    private String longitude;
    private int timeTraslate;

    /* Comparator for sorting the list by Student Name */
    public static Comparator<AdjusterDistance> DistanceComparator = new Comparator<AdjusterDistance>() {

	@Override
	public int compare(AdjusterDistance s1, AdjusterDistance s2) {
	    double distance1 = s1.getDistance();
	    double distance2 = s2.getDistance();

	    // ascending order
	    return Double.compare(distance1, distance2);

	    // descending order
	    // return Double.compare(distance2, distance1);
	}
    };
    /* Comparator for sorting the list by Student Name */
    public static Comparator<AdjusterDistance> traslateComparator = new Comparator<AdjusterDistance>() {

	@Override
	public int compare(AdjusterDistance s1, AdjusterDistance s2) {
	    int timeTraslate1 = s1.getTimeTraslate();
	    int timeTraslate2 = s2.getTimeTraslate();

	    // ascending order
	    return timeTraslate1-timeTraslate2;

	    // descending order
	    // return timeTraslate2-timeTraslate1;
	}
    };
}
