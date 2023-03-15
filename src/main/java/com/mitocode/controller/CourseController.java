package com.mitocode.controller;

import com.mitocode.dto.CourseDTO;
import com.mitocode.model.Course;
import com.mitocode.service.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final ICourseService   service;
    @Qualifier("courseMapper")
    private final ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<CourseDTO>> readAll() throws Exception{
        List<CourseDTO> list = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Course course = service.readById(id);
        return new ResponseEntity<>(this.convertToDto(course), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO courseDTO) throws Exception{
        Course course = service.save(this.convertToEntity(courseDTO));
        return new ResponseEntity<>(this.convertToDto(course), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@Valid   @PathVariable("id") Integer id, @RequestBody CourseDTO courseDTO) throws Exception{

        courseDTO.setIdCourse(id);
        Course st = service.save(this.convertToEntity(courseDTO) );
        return new ResponseEntity<>(this.convertToDto(st), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CourseDTO convertToDto(Course course){
        return mapper.map(course, CourseDTO.class);
    }
    private Course convertToEntity(CourseDTO courseDTO){
        return mapper.map(courseDTO, Course.class);
    }
}
