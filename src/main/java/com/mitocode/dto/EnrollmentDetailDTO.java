package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.mitocode.model.Course;
import com.mitocode.model.Enrollment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDetailDTO {
    private Integer id;
   @JsonBackReference
   private EnrollmentDTO enrollment;
    @NotNull
    @JsonIncludeProperties(value = {"idCourse"})
    private CourseDTO course;
    @NotNull
    private String classroom;
}
