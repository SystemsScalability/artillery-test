package usb.systems.scalability.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private String model;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private String color;

  @Column(name = "engine_type", nullable = false)
  private String engineType;

  @Column(nullable = false)
  private double price;

  @Column(nullable = false)
  private int mileage;

  private String description;
}
