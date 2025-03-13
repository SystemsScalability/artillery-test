package usb.systems.scalability.application.services;

import org.springframework.stereotype.Service;
import usb.systems.scalability.application.requests.CarRequest;
import usb.systems.scalability.domain.entities.Car;
import usb.systems.scalability.domain.repositories.ICarRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {
  private final ICarRepository carRepository;

  public CarService(ICarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<Car> getAllCars() {
    return carRepository.findAll();
  }

  public Optional<Car> getCarById(UUID id) {
    return carRepository.findById(id);
  }

  public List<Car> getCarByBrandName(String brand) {
    return carRepository.findByBrandIgnoreCase(brand);
  }

  public List<Car> getCarBetweenYears(int startYear, int endYear) {
    return carRepository.findByYearBetween(startYear, endYear);
  }

  public List<Car> getCarBetweenPrices(double minPrice, double maxPrice) {
    return carRepository.findByPriceBetween(minPrice, maxPrice);
  }

  public Car createCar(CarRequest carRequest) {
    Car car = new Car();
    car.setBrand(carRequest.brand());
    car.setModel(carRequest.model());
    car.setYear(carRequest.year());
    car.setColor(carRequest.color());
    car.setEngineType(carRequest.engineType());
    car.setPrice(carRequest.price());
    car.setMileage(carRequest.mileage());
    car.setDescription(carRequest.description());
    return carRepository.save(car);
  }

  public Car updateCar(UUID id, CarRequest carRequest) {
    Car car = carRepository.findById(id).orElseThrow();
    car.setBrand(carRequest.brand());
    car.setModel(carRequest.model());
    car.setYear(carRequest.year());
    car.setColor(carRequest.color());
    car.setEngineType(carRequest.engineType());
    car.setPrice(carRequest.price());
    car.setMileage(carRequest.mileage());
    car.setDescription(carRequest.description());
    return carRepository.save(car);
  }

  public void deleteCar(UUID id) {
    carRepository.deleteById(id);
  }
}
