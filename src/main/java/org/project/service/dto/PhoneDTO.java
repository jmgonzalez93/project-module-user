package org.project.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

    @JsonProperty("number")
    private String number;

    @JsonProperty("city_code")
    private String citycode;

    @JsonProperty("country_code")
    private String countrycode;
}