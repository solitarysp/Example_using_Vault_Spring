package com.lethanh98.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DecryptRequestDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Data muốn giải mã", required = true, example = "Lê")
    @NotNull(message = "Data muốn giải mã")
    private String cipherText;
}
