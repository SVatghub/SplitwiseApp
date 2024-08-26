package com.sahil.SplitwiseApp.DTO.exceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class NullFieldExceptionDTO {
    String msg;
}
