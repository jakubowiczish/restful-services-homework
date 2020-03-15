package com.distributedsystems.restfulserviceshomework.model.quote.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteExternal {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("en")
    private String quote;

    private String author;

    private double rating;
}
