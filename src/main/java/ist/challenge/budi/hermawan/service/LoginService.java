package ist.challenge.budi.hermawan.service;

import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.payload.UserPayload;
import ist.challenge.budi.hermawan.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserRepository userRepository;

    public ResponsePayload loginUser(UserPayload userPayload){
        ResponsePayload responsePayload=new ResponsePayload();
        responsePayload.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responsePayload.setStatus(HttpStatus.BAD_REQUEST.value());
        responsePayload.setError(true);
        if(userPayload.getPassword().equals("") || userPayload.getUserName().equals("")){
            responsePayload.setMessage("Username dan / atau password kosong");
            responsePayload.setStatus(HttpStatus.BAD_REQUEST.value());
            responsePayload.setError(true);
        }else {
            User user=userRepository.findByUserName(userPayload.getUserName());
            if(user!=null) {
                if (user.getUserName().equals(userPayload.getUserName()) && user.getPassword().equals(userPayload.getPassword())) {
                    responsePayload.setMessage("Sukses Login");
                    responsePayload.setStatus(HttpStatus.OK.value());
                    responsePayload.setError(false);
                }
            }
        }
        return  responsePayload;
    }
}
