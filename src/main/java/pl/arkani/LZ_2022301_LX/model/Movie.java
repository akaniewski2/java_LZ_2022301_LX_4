package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    // @Column(name="`desc`")
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String originalName;

    private String Country;
    private String description;

    //private Date productionDate;

}

