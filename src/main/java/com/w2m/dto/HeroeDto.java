package com.w2m.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class HeroeDto {

    @NotNull
    @NotBlank
    private String heroeName;
}
