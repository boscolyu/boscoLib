package org.bosco.spring.annotation.properties;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
public class SpringProperties {


    private String testString = "test Autowired Properties";


}
