package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.dto.UserRequestTO;
import br.com.api.bibliadigital.model.dto.UserResponseTO;
import br.com.api.bibliadigital.model.dto.UserV2;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.utils.ExtractBearerToken;
import br.com.api.bibliadigital.utils.HttpHeadersCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExtractBearerToken extractBearerToken;
    @Autowired
    private HttpHeadersCreator httpHeadersCreator;

    @PostMapping
    public ResponseEntity<UserResponseTO> createUser(@RequestBody UserRequestTO request, HttpServletRequest servletRequest) {
        httpHeadersCreator.extractHeaderAuthorization(servletRequest);
        UserResponseTO response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserV2> findUser(@PathVariable("email") String email, HttpServletRequest request) {
        httpHeadersCreator.extractHeaderAuthorization(request);
        UserV2 user = userService.findUser(email);
        return ResponseEntity.ok().body(user);
    }
}
