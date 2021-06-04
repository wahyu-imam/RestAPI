package com.infosys.learning.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonRequest {

    @NotNull
    private int id;

    @NotEmpty
    @NotNull
    private String nama;

    @NotEmpty
    @NotNull
    private String email;

}
