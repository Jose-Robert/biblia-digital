package br.com.api.bibliadigital.model.dto;

import br.com.api.bibliadigital.model.Abbrev;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookV3 implements Serializable {

    private Abbrev abbrev;
    private String name;
    private String author;
    private String chapters;
    private String group;
    private String testament;
}
