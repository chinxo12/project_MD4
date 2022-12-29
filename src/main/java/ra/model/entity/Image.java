package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Table(name = "image")
@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private int imageId;
    @Column(name = "imageLink")
    private String imageLink;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
