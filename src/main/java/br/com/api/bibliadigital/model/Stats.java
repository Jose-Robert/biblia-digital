package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stats implements Serializable {

    private String lastLogin;
    private List<RequestsPerMonth> requestsPerMonth;
}
