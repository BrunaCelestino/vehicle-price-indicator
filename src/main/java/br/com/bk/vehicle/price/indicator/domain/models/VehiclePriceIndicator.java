package br.com.bk.vehicle.price.indicator.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class VehiclePriceIndicator {
    private String fipeValue;
    private String icarrosValue;
    private String date;
}
