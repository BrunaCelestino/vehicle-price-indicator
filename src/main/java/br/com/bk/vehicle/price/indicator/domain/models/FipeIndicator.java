package br.com.bk.vehicle.price.indicator.domain.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FipeIndicator {
    private String code;
    private List<FipeIndicatorData> values;
}
