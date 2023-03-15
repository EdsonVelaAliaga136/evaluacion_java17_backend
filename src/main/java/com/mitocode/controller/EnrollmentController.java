package com.mitocode.controller;

import com.mitocode.dto.CourseDTO;
import com.mitocode.dto.EnrollmentDTO;
import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Course;
import com.mitocode.model.Enrollment;
import com.mitocode.model.Student;
import com.mitocode.service.IEnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final IEnrollmentService   service;
    @Qualifier("enrollmentMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> readAll() throws Exception{
        List<EnrollmentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Enrollment enrollment = service.readById(id);
        return new ResponseEntity<>(this.convertToDto(enrollment), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception{
        Enrollment enrollment = service.save(this.convertToEntity(enrollmentDTO));
        return new ResponseEntity<>(this.convertToDto(enrollment), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@Valid   @PathVariable("id") Integer id, @RequestBody EnrollmentDTO enrollmentDTO) throws Exception{

        enrollmentDTO.setIdEnrollment(id);
        Enrollment st = service.save(this.convertToEntity(enrollmentDTO) );
        return new ResponseEntity<>(this.convertToDto(st), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/test1")
    public ResponseEntity<Map<CourseDTO,List<StudentDTO>>> listarRelacion1() throws Exception {
        List<EnrollmentDTO> listDTO = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        Map<CourseDTO, List<StudentDTO>> mapEnrollment =
                listDTO.stream()
                        .flatMap(m-> m.getEnrollmentDetails()
                                .stream()
                                .map(d-> new AbstractMap.SimpleEntry<>(d.getCourse(), m.getStudent())))
                        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        return new ResponseEntity<>(mapEnrollment, HttpStatus.OK);
    }
    @GetMapping("/test2")
    public ResponseEntity<Map<String,List<String>>> listarRelacion2() throws Exception {
        List<EnrollmentDTO> listDTO = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        Map<String, List<String>> mapEnrollment =
                listDTO.stream()
                        .flatMap(m-> m.getEnrollmentDetails()
                                .stream()
                                .map(d-> new AbstractMap.SimpleEntry<>(d.getCourse().getNameCourse(), m.getStudent().getNameStudent())))
                        .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        return new ResponseEntity<>(mapEnrollment, HttpStatus.OK);
    }
    private EnrollmentDTO convertToDto(Enrollment enrollment){
        return mapper.map(enrollment, EnrollmentDTO.class);
    }
    private Enrollment convertToEntity(EnrollmentDTO enrollmentDTO){
        return mapper.map(enrollmentDTO, Enrollment.class);
    }
}
