package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeriodRequests implements Serializable {

    private Integer total;
    private List<Requests> requests;
}
