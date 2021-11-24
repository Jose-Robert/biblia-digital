package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Verse implements Serializable {

    private BookV2 book;
    private Integer chapter;
    private Integer number;
    private String text;
}
