package ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "imagefeedback")
public class ImageFeedBack {
    @Id
    @Column(name = "imageId")
    private int imageFeedBackId;
    @Column(name = "imageLink")
    private String imageLink;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feedBackId")
    @JsonIgnore
    private FeedBack feedBack;
}
