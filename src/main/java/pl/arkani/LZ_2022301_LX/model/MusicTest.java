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

public class MusicTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    // @Column(name="`desc`")
    //@NotBlank(message = "author is mandatory")
    private String author;

   // @NotBlank(message = "album is mandatory")
    private String album;

    private String url;

    private String type;

    private int rating;

    private int cd;

    private String owner;

    private String borrower;

    private String publishDate;




}