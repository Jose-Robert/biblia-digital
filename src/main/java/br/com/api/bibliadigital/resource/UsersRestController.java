package br.com.api.bibliadigital.resource;

import br.com.api.bibliadigital.model.Stats;
import br.com.api.bibliadigital.model.dto.*;
import br.com.api.bibliadigital.service.UserService;
import br.com.api.bibliadigital.shared.HttpHeadersCreator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/users")
public class UsersRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpHeadersCreator httpHeaders;

    @ApiOperation(value = "Criar usuário", notes = "Esse metódo cadastra um usuário na API")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseTO> createUser(@RequestBody UserRequestTO userRequestTO,
                                                     HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        UserResponseTO response = userService.createUser(userRequestTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Buscar usuário", notes = "Esse metódo busca usuario pelo email - Authenticated: Yes")
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserV2> findUser(@PathVariable("email") String email,
                                           HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        UserV2 user = userService.findUser(email);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "Buscar estatísticas",
            notes = "Esse metódo busca todas as estatítiscas - Authenticated: Yes")
    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stats> findAllStatistics(HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        Stats stats = userService.findAllStatistics();
        return ResponseEntity.ok().body(stats);
    }

    @ApiOperation(value = "Atualiza token", notes = "Esse metódo atualiza o token do usuário")
    @PutMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseV2> updateTokenUser(@RequestBody UserRequestV2 requestTO) {
        UserResponseV2 responseTO = userService.updateToken(requestTO);
        return ResponseEntity.ok().body(responseTO);
    }

    @ApiOperation(value = "Remover usuário", notes = "Esse metódo remove um usuário - Authenticated: Yes")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteUser(@RequestBody UserRequestV2 requestTO,
                                             HttpServletRequest servletRequest) {
        httpHeaders.getAuthorization(servletRequest);
        String msg = userService.deleteUser(requestTO);
        return ResponseEntity.ok().body(msg);
    }

    @ApiOperation(value = "reenviar senha", notes = "Esse metódo envia nova senha do usuário para email")
    @PostMapping(value = "/password/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> resendPasswordUser(@PathVariable(value = "email") String email) {
        String msg = userService.sendEmail(email);
        return ResponseEntity.ok().body(msg);
    }
}
