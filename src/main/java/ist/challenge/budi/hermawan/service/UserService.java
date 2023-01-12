package ist.challenge.budi.hermawan.service;

import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository; 

    public List<User> findUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

    public  ResponsePayload updateUser(User userPayload){
        ResponsePayload responsePayload=new ResponsePayload();
        Optional<User> user=userRepository.findById(userPayload.getId());
        if(user.isEmpty()){
            responsePayload.setMessage(HttpStatus.CONFLICT.getReasonPhrase());
            responsePayload.setStatus(HttpStatus.CONFLICT.value());
            responsePayload.setError(true);
        }else{
            if(!user.get().getUserName().equals(userPayload.getUserName())){
                responsePayload.setMessage("Username sudah terpakai");
                responsePayload.setStatus(HttpStatus.CONFLICT.value());
                responsePayload.setError(true);
            }else{
                if(user.get().getPassword().equals(userPayload.getPassword())){
                    responsePayload.setMessage("Password tidak boleh sama dengan password sebelumnya");
                    responsePayload.setStatus(HttpStatus.BAD_REQUEST.value());
                    responsePayload.setError(true);
                }else{
                    user.get().setPassword(userPayload.getPassword());
                    userRepository.save(user.get());
                    responsePayload.setMessage(HttpStatus.CREATED.getReasonPhrase());
                    responsePayload.setStatus(HttpStatus.CREATED.value());
                }
            }
        }
        return responsePayload;
    }
}
