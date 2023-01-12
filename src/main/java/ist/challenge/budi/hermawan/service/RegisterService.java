package ist.challenge.budi.hermawan.service;

import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.payload.UserPayload;
import ist.challenge.budi.hermawan.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    UserRepository userRepository;
    public ResponsePayload createUser(UserPayload userPayload){
        ResponsePayload responsePayload=new ResponsePayload();
        User user=userRepository.findByUserName(userPayload.getUserName());
        if(user==null){
            user=new User();
            user.setUserName(userPayload.getUserName());
            user.setPassword(userPayload.getPassword());
            userRepository.save(user);
            responsePayload.setMessage("Sukses Registrasi");
            responsePayload.setStatus(HttpStatus.CREATED.value());
        }else{
            responsePayload.setMessage("Username sudah terpakai");
            responsePayload.setStatus(HttpStatus.CONFLICT.value());
            responsePayload.setError(true);
        }



        return responsePayload;
    }

}
