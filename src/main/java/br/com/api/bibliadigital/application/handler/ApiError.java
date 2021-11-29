package br.com.api.bibliadigital.application.handler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiError<T> implements Serializable {

    private List<Error> errors = new ArrayList<>();
    private String dateTime;
    private int statusCode;

    public ApiError() {}

    public ApiError(List<Error> errors) {
        super();
        this.errors = errors;
    }

    public String getDateTime() {
        LocalDateTime dateAndTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dateTime = dateTimeFormatter.format(dateAndTime);
        return dateTime;
    }
}
