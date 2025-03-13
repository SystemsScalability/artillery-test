package usb.systems.scalability.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usb.systems.scalability.domain.entities.Car;

import java.util.List;
import java.util.UUID;

public interface ICarRepository extends JpaRepository<Car, UUID> {

  List<Car> findByBrandIgnoreCase(String brand);

  List<Car> findByYearBetween(int startYear, int endYear);

  List<Car> findByPriceBetween(double minPrice, double maxPrice);
}
