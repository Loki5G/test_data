package data_api.model.request;

public class DataParameters {
    private final String city;
    private final String services;


    public DataParameters(String city, String services) {
        this.city = city;
        this.services = services;
    }

    public String getCity() {
        return city;
    }

    public String getServices() {
        return services;
    }
}
