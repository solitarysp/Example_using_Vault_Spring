package com.lethanh98.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EncryptRequestDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiModelProperty(notes = "Data muốn mã hóa", required = true, example = "Lê")
    @NotNull(message = "Data muốn mã hóa không thể null")
    private String data;
}
