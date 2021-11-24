package br.com.api.bibliadigital.model.dto;

import br.com.api.bibliadigital.model.Chapter;
import br.com.api.bibliadigital.model.Verses;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VersesV2 implements Serializable {

    private BookV2 book;
    private Chapter chapter;
    private List<Verses> verses;
}
