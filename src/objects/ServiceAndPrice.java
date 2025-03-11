package objects;

import java.util.EnumMap;

public class ServiceAndPrice {
    // Use a static EnumMap to store service prices
    private static final EnumMap<ServiceType, Double> services = new EnumMap<>(ServiceType.class);

    // Static block to initialize the service prices
    static {
        services.put(ServiceType.Bathing, 20.0);
        services.put(ServiceType.Grooming, 30.0);
        services.put(ServiceType.NailTrimming, 15.0);
        services.put(ServiceType.DentalCare, 25.0);
        services.put(ServiceType.Styling, 40.0);
        services.put(ServiceType.EarCleaning, 10.0);
        services.put(ServiceType.Massage, 50.0);
    }

    // Static method to get the price of a service
    public static double getPrice(ServiceType service) {
        return services.getOrDefault(service, 0.0);
    }
}
