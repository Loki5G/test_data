package data_api.application.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DataApiException extends RuntimeException {
    private final ObjectNode node;

    public DataApiException(String message, ObjectNode node) {
        super(message);
        this.node = node;
        //sdafsdfdasdfs
    }

    public ObjectNode getNode() {
        return node;
    }
}
