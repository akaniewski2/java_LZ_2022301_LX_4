package pl.arkani.LZ_2022301_LX.model;


import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CulinaryIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    String comment;


   double quantity;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,optional = false /*optional = false nie pozowli zapisac course bez teacher */)
    @JoinColumn(name = "CULINARY_RECIPES_ID", referencedColumnName = "ID")
    @ToString.Exclude
    private CulinaryRecipes culinaryRecipes;
}
