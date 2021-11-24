package br.com.api.bibliadigital.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersesResponse implements Serializable {

    private String occurrence;
    private String version;
    private List<VersesV3> verses;
}
