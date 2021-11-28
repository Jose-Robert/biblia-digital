package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Stats;
import br.com.api.bibliadigital.model.dto.*;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
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
    private HttpHeadersCreator httpHeaders;

    @PostMapping
    public ResponseEntity<UserResponseTO> createUser(@RequestBody UserRequestTO userRequestTO,
                                                     HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        UserResponseTO response = userService.createUser(userRequestTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserV2> findUser(@PathVariable("email") String email,
                                           HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        UserV2 user = userService.findUser(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/stats")
    public ResponseEntity<Stats> findAllStatistics(HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        Stats stats = userService.findAllStatistics();
        return ResponseEntity.ok().body(stats);
    }

    @PutMapping("/token")
    public ResponseEntity<TokenResponseTO> updateTokenUser(@RequestBody TokenRequestTO requestTO) {
        TokenResponseTO responseTO = userService.updateToken(requestTO);
        return ResponseEntity.ok().body(responseTO);
    }
}
