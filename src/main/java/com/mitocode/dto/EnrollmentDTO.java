package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {
    private Integer idEnrollment;
    @NotNull
    private LocalDateTime dateRegistrationEnrollment;
    @NotNull
    @JsonIncludeProperties(value = {"idStudent"})
    private StudentDTO student;
    @NotNull
    @JsonManagedReference
    private List<EnrollmentDetailDTO> enrollmentDetails;
    @NotNull
    private boolean statusEnrollment;
}
