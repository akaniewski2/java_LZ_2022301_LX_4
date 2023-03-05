package pl.arkani.LZ_2022301_LX.model;


import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TechPageTmp {


    private String name;

    private String button;

    private String header;
    private String url;











}
