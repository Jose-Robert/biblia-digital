package br.com.api.bibliadigital.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestTO implements Serializable {

    private String name;
    private String email;
    private Integer password;
    private Boolean notifications;
}
