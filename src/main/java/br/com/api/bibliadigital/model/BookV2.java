package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookV2 implements Serializable {

    private Abbrev abbrev;
    private String author;
    private String group;
    private String name;
    private String version;

}
