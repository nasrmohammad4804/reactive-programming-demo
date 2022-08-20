package com.nasr.webfluxdemo.dto.view;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductViewDto {

    @NotEmpty(message = "product name is mandatory")
    private String name;

    private Long categoryId;

    @Min(value = 5)
    private Long capacity;

    @NotNull(message = "product price is mandatory")
    private Double price;
}
