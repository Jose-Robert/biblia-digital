package br.com.api.bibliadigital.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersesTO implements Serializable {

    private BookV2 book;
    private Chapter chapter;
    private List<Verses> verses;
}
