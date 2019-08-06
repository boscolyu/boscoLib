package org.bosco.swagger.annotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@Component
@Api(tags = "swagger api test controller v1.0", description = "Swagger test API", consumes = "application/json", produces = "application/json")
public class SwaggerAnnotationImpl {


    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "test api note",
            produces = "application/json",
            notes = "description of test api")
    public String home(@ApiParam(value = "id", required = true) @PathVariable("id") Integer id) {
        return "Hello World of CustomAnnotationImpl!<br>" + id.toString();
    }

}
