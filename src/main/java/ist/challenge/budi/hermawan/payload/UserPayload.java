package ist.challenge.budi.hermawan.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
public class UserPayload {
    @Column(name="userName",length=25)
    private String userName;

    @Column(name="password",length=25)
    private String password;

}
