package data_api.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import data_api.application.utils.JsonResponseGenerator;
import data_api.application.utils.ValidateRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import data_api.model.request.DataParameters;


@RestController
@RequestMapping(value = "/api/v1/data")
public class DataController {
    private final ValidateRequestUtils validateRequestUtils;

    @Autowired
    public DataController(ValidateRequestUtils validateRequestUtils) {
        this.validateRequestUtils = validateRequestUtils;
    }

    @PostMapping(value = "/request")
    public ResponseEntity<?> cityData(@RequestBody ObjectNode json) {
        DataParameters dataParameters = validateRequestUtils.validateUserRegistrationParameters(json);

        ObjectNode response = JsonResponseGenerator.generateSuccessResponseJson();

        return ResponseEntity.ok(response);
    }
}
