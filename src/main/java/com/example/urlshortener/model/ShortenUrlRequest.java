package com.example.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortenUrlRequest {
    
    @NotBlank(message = "URL cannot be blank")
    @Pattern(
        regexp = "^(https?://)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([/\\w \\.-]*)*/?$",
        flags = Pattern.Flag.CASE_INSENSITIVE,
        message = "Invalid URL format"
    )
    private String url;
}