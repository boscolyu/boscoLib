package org.bosco.spring.annotation.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@ConfigurationProperties(value="boscolib-properties")
@Validated
public class SpringPropertiesWithYaml {
}
