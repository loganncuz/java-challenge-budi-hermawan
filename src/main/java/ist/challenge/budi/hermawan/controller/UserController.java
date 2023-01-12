package ist.challenge.budi.hermawan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.payload.UserPayload;
import ist.challenge.budi.hermawan.service.LoginService;
import ist.challenge.budi.hermawan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value="onlinestore", description="User Endpoint")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "View a list of User", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() throws Exception {
        List<User> users=userService.findUsers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(users);
    }

    @ApiOperation(value = "Change User Password", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PutMapping("/updateUser")
    @Produces("application/json")
    public ResponseEntity<?> update(@RequestBody User body) throws Exception { 
        ResponsePayload responsePayload=userService.updateUser(body);
        return ResponseEntity
                .status(responsePayload.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(responsePayload);
    }

}
