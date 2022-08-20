package com.nasr.webfluxdemo.dto.view;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


public record CustomerViewDto(  @NotBlank(message = "firstName is mandatory")
                                String firstName,

                                @NotBlank(message = "lastName is mandatory")
                                String lastName,

                                @Email(message = ""/*, regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$"*/)
                                String email,

                                @NotBlank(message = "password is mandatory")
                                String password) implements Serializable {


}
