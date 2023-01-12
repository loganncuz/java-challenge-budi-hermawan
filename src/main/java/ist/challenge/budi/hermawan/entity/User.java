package ist.challenge.budi.hermawan.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="ist_user",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username"}, name="USER_UNIQUE_ID"),
        indexes={
                @Index(columnList="id",name="Index_id"),
                @Index(columnList="username",name="username")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username",length=25)
    private String userName;

    @Column(name="password",length=25)
    private String password;
}
