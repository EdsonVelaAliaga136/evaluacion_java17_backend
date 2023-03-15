package com.mitocode.controller;

import com.mitocode.dto.StudentDTO;
import com.mitocode.model.Student;
import com.mitocode.service.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final IStudentService   service;
    @Qualifier("studentMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<StudentDTO>> readAll() throws Exception{
        List<StudentDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Student student = service.readById(id);
        return new ResponseEntity<>(this.convertToDto(student), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO studentDTO) throws Exception{
        Student student = service.save(this.convertToEntity(studentDTO));
        return new ResponseEntity<>(this.convertToDto(student), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@Valid    @PathVariable("id") Integer id, @RequestBody StudentDTO studentDTO) throws Exception{

        studentDTO.setIdStudent(id);
        Student st = service.save(this.convertToEntity(studentDTO) );
        return new ResponseEntity<>(this.convertToDto(st), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<StudentDTO>> readByAgeDESC() throws Exception {
        List<StudentDTO> listSorted = service.readAll().stream().sorted(Comparator.comparingInt(Student::getAge).reversed())
                .map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(listSorted, HttpStatus.OK);
    }
    private StudentDTO convertToDto(Student student){
        return mapper.map(student, StudentDTO.class);
    }
    private Student convertToEntity(StudentDTO studentDTO){
        return mapper.map(studentDTO, Student.class);
    }
}
