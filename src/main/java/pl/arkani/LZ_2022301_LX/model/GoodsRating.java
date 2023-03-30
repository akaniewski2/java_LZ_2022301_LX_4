package pl.arkani.LZ_2022301_LX.model;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;


//    @NotNull
//    @NotEmpty /*gt > 0*/
//    @NotBlank /*trim gt >0*/
    @Size(min=3)
    String item;

    @NotEmpty(message = "cannot be empty.")
    @Size(min = 5, max = 250)
    String mark;

    String country;

    String shop;

    String rating;

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
