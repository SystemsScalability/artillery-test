package usb.systems.scalability.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usb.systems.scalability.domain.entities.Car;
import usb.systems.scalability.application.requests.CarRequest;
import usb.systems.scalability.application.responses.CarResponse;
import usb.systems.scalability.application.services.CarService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping
  public List<CarResponse> getAllCars() {
    return carService.getAllCars().stream()
            .map(car -> new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getDescription()))
            .toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarResponse> getCarById(@PathVariable UUID id) {
    return carService.getCarById(id)
            .map(car -> new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getDescription()))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/brand/{brand}")
  public List<CarResponse> getCarByBrand(@PathVariable String brand) {
    return carService
            .getCarByBrandName(brand)
            .stream()
            .map(car -> new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getDescription()))
            .toList();
  }

  @GetMapping("/years")
  public List<CarResponse> getCarBetweenYears(
          @RequestParam int startYear, @RequestParam int endYear) {
    return carService
            .getCarBetweenYears(startYear, endYear)
            .stream()
            .map(car -> new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getDescription()))
            .toList();
  }

  @GetMapping("/prices")
  public List<CarResponse> getCarBetweenPrices(
          @RequestParam double minPrice, @RequestParam double maxPrice) {

    return carService
            .getCarBetweenPrices(minPrice, maxPrice)
            .stream()
            .map(car -> new CarResponse(
                    car.getId(),
                    car.getBrand(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getEngineType(),
                    car.getPrice(),
                    car.getMileage(),
                    car.getDescription()))
            .toList();
  }

  @PostMapping
  public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest carRequest) {
    Car car = carService.createCar(carRequest);
    CarResponse carResponse = new CarResponse(
            car.getId(),
            car.getBrand(),
            car.getModel(),
            car.getYear(),
            car.getColor(),
            car.getEngineType(),
            car.getPrice(),
            car.getMileage(),
            car.getDescription());

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(carResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CarResponse> updateCar(@PathVariable UUID id, @RequestBody CarRequest carRequest) {
    Car car = carService.updateCar(id, carRequest);
    CarResponse carResponse = new CarResponse(
            car.getId(),
            car.getBrand(),
            car.getModel(),
            car.getYear(),
            car.getColor(),
            car.getEngineType(),
            car.getPrice(),
            car.getMileage(),
            car.getDescription());

    return ResponseEntity.ok(carResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
    carService.deleteCar(id);

    return ResponseEntity.noContent().build();
  }
}
