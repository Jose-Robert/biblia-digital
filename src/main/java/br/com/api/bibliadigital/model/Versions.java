package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Versions implements Serializable {

    private String version;
    private String verses;
}
