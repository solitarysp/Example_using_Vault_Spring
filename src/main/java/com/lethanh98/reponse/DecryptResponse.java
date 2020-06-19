package com.lethanh98.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DecryptResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Data gốc", required = true, example = "Lê")
    @NotNull(message = "Data gốc")
    private String data;
}
