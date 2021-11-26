package br.com.api.bibliadigital.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseTO implements Serializable {

    private String name;
    private String email;
    private String token;
    private Boolean notifications;
}
