package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.*;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Size(min = 5)
  private String fullName;

  @NotEmpty
  @Email
  private String email;

  @NotNull
  @Min(value = 18)
  private Integer age;

  // Getters and Setters

}
