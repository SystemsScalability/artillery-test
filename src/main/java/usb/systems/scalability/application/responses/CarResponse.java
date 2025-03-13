package usb.systems.scalability.application.responses;

import java.util.UUID;

public record CarResponse(
        UUID id,
        String brand,
        String model,
        int year,
        String color,
        String engineType,
        double price,
        int mileage,
        String description
) {}
