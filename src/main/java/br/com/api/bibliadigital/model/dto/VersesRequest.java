package br.com.api.bibliadigital.model.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersesRequest implements Serializable {

    private String version;
    private String search;
}
