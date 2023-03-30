package pl.arkani.LZ_2022301_LX.model;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class MisiaSays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    double age;
    String phrase;

    String translation;

    String comment;

   @CreationTimestamp
    private LocalDateTime createdOn;

   @UpdateTimestamp
    private LocalDateTime updatedOn;


//    @PostConstruct
//    void started() {
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//    }

}
