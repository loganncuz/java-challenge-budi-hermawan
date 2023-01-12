package ist.challenge.budi.hermawan.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Transient;

@Getter
@Setter
public class ResponsePayload {
    @Column(name="message")
    private String message;

    @Column(name="status")
    private int status;

    @Transient
    @Column(name="error")
    private boolean error;

}
