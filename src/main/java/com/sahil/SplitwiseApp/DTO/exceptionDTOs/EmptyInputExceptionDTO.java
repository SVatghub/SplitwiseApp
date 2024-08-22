package com.sahil.SplitwiseApp.DTO.exceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class EmptyInputExceptionDTO {
    String msg;
}
