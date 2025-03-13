package usb.systems.scalability.application.requests;

public record CarRequest(
        String brand,
        String model,
        int year,
        String color,
        String engineType,
        double price,
        int mileage,
        String description
) {}
