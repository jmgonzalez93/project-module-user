package org.project.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="project-base")
public class ApplicationProperties {

    @Value("${password-ex-reg.var}")
    private String exRegPass;
}
