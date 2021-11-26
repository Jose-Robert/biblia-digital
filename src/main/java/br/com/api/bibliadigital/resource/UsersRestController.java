package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseTO> createUser(@RequestBody UserRequestTO request) {
        UserResponseTO response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
