package br.com.zup.file.generator.format.gateway.http.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class ObjectTypeError {
    private String message;
    private String field;
    private Object parameter;
}
