package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CulinaryRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

//    public CulinaryRecipes(CulinaryIngredients culinaryIngredients) {
//     this.culinaryIngredients.add(culinaryIngredients);
//    }

    String name;

    String description;

    String comment;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;


    @OneToMany(mappedBy = "culinaryRecipes",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CulinaryIngredients> culinaryIngredients;



}