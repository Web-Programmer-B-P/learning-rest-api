package rest.example.models;

import lombok.*;
import javax.persistence.*;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "faq")
public class Faq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PROTECTED)
    private long id;
    private String title;
    private String content;
}
