package br.com.api.bibliadigital.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserV2 implements Serializable {

    private String name;
    private String email;
    private String token;
    private Boolean notifications;
    private String lastLogin;
}
