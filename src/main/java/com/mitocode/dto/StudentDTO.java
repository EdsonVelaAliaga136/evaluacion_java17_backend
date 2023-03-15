package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
     private Integer idStudent;
     @NotNull
     @NotEmpty
     @Size(min = 3, max = 50)
     private String nameStudent;
     @NotNull
     @NotEmpty
     @Size(min = 3, max = 50)
     private String lastnameStudent;
     @NotNull
     @NotEmpty
     @Size(max = 15)
     private String dniStudent;
     @NotNull
     @Min(1)
     @Max(50)
     private int ageStudent;
}
