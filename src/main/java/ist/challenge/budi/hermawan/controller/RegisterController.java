package ist.challenge.budi.hermawan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.payload.UserPayload;
import ist.challenge.budi.hermawan.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;

@RestController
@RequestMapping("/api")
@Api(value="onlinestore", description="Registration Endpoint")
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @ApiOperation(value = "Registration of new User", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping("/register")
    @Produces("application/json")
    public ResponseEntity<?> register(@RequestBody UserPayload body) throws Exception {
        User user = new User();
        ResponsePayload responsePayload=registerService.createUser(body);
        return ResponseEntity
                .status(responsePayload.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(responsePayload);
    }
}
