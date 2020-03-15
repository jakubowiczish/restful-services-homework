package com.distributedsystems.restfulserviceshomework.model.quote.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    private String author;
    private String quote;
    private double rating;
}
