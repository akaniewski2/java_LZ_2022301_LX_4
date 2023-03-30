package pl.arkani.LZ_2022301_LX.model;


import com.fasterxml.jackson.annotation.JsonView;
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

    public static class View {
        public interface Id{}
        public interface Basic extends Purchase.View.Id {}
        public interface Extended extends Purchase.View.Basic {}

    }

    public interface DataUpdateValidation {}
    @JsonView(Purchase.View.Id.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonView(Purchase.View.Basic.class)
    private String name;

    @JsonView(Purchase.View.Basic.class)
    @OneToMany(mappedBy = "purchaseCategory",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Purchase> purchases;

    @JsonView(Purchase.View.Basic.class)
    @Column
    private int orderr;

    @JsonView(Purchase.View.Basic.class)
    private int activeRec=1;


}
