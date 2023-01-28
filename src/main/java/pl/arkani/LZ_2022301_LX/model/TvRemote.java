package pl.arkani.LZ_2022301_LX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TvRemote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
   // @Column(name="`desc`")

    private String name;
    private String button;
    private String button2;
    private String hex_code;
    private String hex_code2;
    private String type;
    private Integer c_bits;
    private Integer f_check;
    private String dec_;
    private String dec_string;
    private String block;
    private Integer c_in_block;
    private String block_class;
    private String button_class;
    private Integer c_in_line;





}
