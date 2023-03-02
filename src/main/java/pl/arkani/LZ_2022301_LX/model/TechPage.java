package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TechPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    // @Column(name="`desc`")
    @NotBlank(message = "name is mandatory")
    private String name;

    String project;

    //String title;

    String url;

    String tag;

    String button;

    String description;
    String header;

    String content;

    int orderRow;

    int activeRow;





}
