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
public class Zakupy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String towar;
    private String kupione;
    private long dt_dod;
    private long dt_zm;
    private String stan;
    private String kategoria;
    private int usun;
    private String uwagi;
    private String user_dod;
    private String user_zm;
    private long user_id;


}