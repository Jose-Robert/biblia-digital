package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book implements Serializable {

    private Abbrev abbrev;
    private String author;
    private String chapters;
    private String comment;
    private String group;
    private String name;
    private String testament;

    public String getComment() {
        return comment != null ? comment : "";
    }
}
