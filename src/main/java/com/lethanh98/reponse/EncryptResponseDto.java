package com.lethanh98.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data

public class EncryptResponseDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Data muốn mã hóa", required = true, example = "Lê")
    @NotNull(message = "Data muốn mã hóa không thể null")
    private String data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Data sau khi mã hóa", required = true, example = "Lê")
    @NotNull(message = "Data sau khi mã hóa")
    private String cipherText;
}
