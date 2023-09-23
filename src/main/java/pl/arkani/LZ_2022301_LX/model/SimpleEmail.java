package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.Entity;
import lombok.*;

//@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleEmail {
    String name;
    String email;
    String subject;
    String message;
    boolean htmlFormat;
    boolean like;
}
