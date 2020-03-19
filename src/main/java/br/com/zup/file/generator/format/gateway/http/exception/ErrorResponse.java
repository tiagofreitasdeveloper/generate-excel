package br.com.zup.file.generator.format.gateway.http.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String message;
    private List<String> errors;
}
