package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "catalog")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogId")
    private int catalogId;
    @Column(name = "catalogName")
    private String catalogName;
    @Column(name = "parentId")
    private int parentId;
    @JsonIgnore
    @OneToMany(mappedBy = "catalog")
    List<Product> listProduct = new ArrayList<>();

}
