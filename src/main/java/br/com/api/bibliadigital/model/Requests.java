package br.com.api.bibliadigital.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requests implements Serializable {

    @SerializedName(value = "_id")
    private String id;
    private Integer count;
}
