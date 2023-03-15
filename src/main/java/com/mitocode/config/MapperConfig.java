package com.mitocode.config;

import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Enrollment;
import com.mitocode.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class MapperConfig {
    /*@Bean
    public ModelMapper modelMapper(){
       return new ModelMapper();
    }*/
    @Bean("studentMapper")
    public ModelMapper studentMapper(){
        ModelMapper mapper = new ModelMapper();
        /*TypeMap<StudentDTO, Student> typeMap1 =  mapper.createTypeMap(StudentDTO.class, Student.class);
        TypeMap<Student, StudentDTO> typeMap2 =  mapper.createTypeMap(Student.class, StudentDTO.class);
        typeMap1.addMapping(StudentDTO::getNameStudentDTO, (dest, v) -> dest.setName((String)v));
        typeMap2.addMapping(Student::getName, (dest, v) -> dest.setNameStudentDTO((String)v));*/
        return mapper;
    }
    @Bean("enrollmentMapper")
    public ModelMapper enrollmentMapper(){
        ModelMapper mapper = new ModelMapper();
        /*TypeMap<EnrollmentDTO, Enrollment> typeMap1 =  mapper.createTypeMap(EnrollmentDTO.class, Enrollment.class);
        TypeMap<Enrollment, EnrollmentDTO> typeMap2 =  mapper.createTypeMap(Enrollment.class, EnrollmentDTO.class);
        typeMap1.addMapping(EnrollmentDTO::getIdStudentEnrollmentDTO, (dest, v) -> dest.getStudent().setId((Integer) v));
        typeMap2.addMapping(e -> e.getStudent().getId(), (dest, v) -> dest.setIdStudentEnrollmentDTO( (Integer) v));*/
        return mapper;
    }
    @Bean("courseMapper")
    public ModelMapper courseMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
