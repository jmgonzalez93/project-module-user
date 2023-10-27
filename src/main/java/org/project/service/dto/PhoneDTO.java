package org.project.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PhoneDTO {


    private String number;

    private String citycode;

    private String contrycode;
}