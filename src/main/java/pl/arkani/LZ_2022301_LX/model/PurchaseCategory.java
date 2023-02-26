package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "purchaseCategory",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Purchase> purchases;


    @Column
    private int orderr;


    private int activeRec=1;


}
