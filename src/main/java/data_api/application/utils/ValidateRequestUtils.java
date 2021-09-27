package data_api.application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import data_api.model.request.DataParameters;
import org.springframework.stereotype.Component;
import data_api.application.exception.DataApiException;
import data_api.application.exception.ErrorCodes;


import java.util.ArrayList;



@Component
public class ValidateRequestUtils {
    public DataParameters validateUserRegistrationParameters(ObjectNode parameters) throws JsonProcessingException {
        int parametersCount = 2;
        checkJsonFieldsCount(parameters, parametersCount);

        JsonNode cityField = checkJsonField(parameters, "city");
        JsonNode servicesField = checkJsonField(parameters, "services");

        isStringJsonField(cityField);
        isArrayJsonField(servicesField);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<String> city = objectMapper.readValue(servicesField.toString(),ArrayList.class);
        ArrayList<String> services = objectMapper.readValue(servicesField.toString(),ArrayList.class);
        if(servicesField.has(String.valueOf(String.class))){
            System.out.println("ok");
        }
        else{
            System.out.println("no");
        }



        return new DataParameters(city,services);
    }

    private void checkJsonFieldsCount(ObjectNode parameters, int count) {
        if (parameters.size() != count) {
            ObjectNode body = JsonResponseGenerator
                    .generateErrorResponseJson(ErrorCodes.JSON_VALIDATION_ERROR,
                            "Check the number of fields in the message");

            throw new DataApiException("Validation input parameters size not equals " + count, body);
        }
    }

    private JsonNode checkJsonField(ObjectNode parameters, String fieldName) {
        if (parameters.has(fieldName)) {
            return parameters.path(fieldName);
        }

        ObjectNode body = JsonResponseGenerator
                .generateErrorResponseJson(ErrorCodes.JSON_VALIDATION_ERROR,
                        "Not found " + fieldName + " field");

        throw new DataApiException("Not found " + fieldName + " field", body);
    }

    private String isStringJsonField(JsonNode field) {
        if (field.isTextual()) {
            return field.textValue();
        }

        ObjectNode body = JsonResponseGenerator
                .generateErrorResponseJson(ErrorCodes.JSON_VALIDATION_ERROR,
                        field + " value must be a string");

        throw new DataApiException(field + " value must be a string", body);
    }

    private static void isArrayJsonField(JsonNode field) {
        if (!field.isArray()) {
            ObjectNode body = JsonResponseGenerator
                    .generateErrorResponseJson(ErrorCodes.JSON_VALIDATION_ERROR,
                            field + " value must be a array");

            throw new DataApiException(field + " value must be a array", body);
        }
    }

}