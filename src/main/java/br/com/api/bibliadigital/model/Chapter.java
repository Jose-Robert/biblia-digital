package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chapter implements Serializable {

    private Integer number;
    private Integer verses;
}
