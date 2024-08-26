package com.sahil.SplitwiseApp.DTO.exceptionDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResourceNotFoundExceptionDTO {
    String message;
}
