package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TvChannel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int id;
   // @Column(name="`desc`")

    private String name;
    private String description;

    private int active;
    private String code;
    private String Tag;

    int prior;







}
