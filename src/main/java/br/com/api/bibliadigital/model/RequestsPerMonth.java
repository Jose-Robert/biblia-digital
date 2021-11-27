package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestsPerMonth implements Serializable {

    private String range;
    private Integer total;
}
