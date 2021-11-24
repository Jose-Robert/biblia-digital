package br.com.api.bibliadigital.model.dto;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersesV3 implements Serializable {

    private BookV3 book;
    private Integer chapter;
    private Integer number;
    private String text;
}
