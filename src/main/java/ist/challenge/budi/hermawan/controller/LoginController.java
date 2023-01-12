package ist.challenge.budi.hermawan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ist.challenge.budi.hermawan.entity.User;
import ist.challenge.budi.hermawan.payload.ResponsePayload;
import ist.challenge.budi.hermawan.payload.UserPayload;
import ist.challenge.budi.hermawan.service.LoginService;
import ist.challenge.budi.hermawan.service.RegisterService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@RestController
@RequestMapping("/api")
@Api(value="onlinestore", description="Login Endpoint")
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "User Access", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping("/login")
    @Produces("application/json")
    public ResponseEntity<?> login(@RequestBody UserPayload body) throws Exception {
        ResponsePayload responsePayload=loginService.loginUser(body);
        return ResponseEntity
                .status(responsePayload.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(responsePayload);
    }
}
