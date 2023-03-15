package com.mitocode.service.impl;

import com.mitocode.model.Course;
import com.mitocode.repository.ICourseRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {
    private final ICourseRepo repo;
    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }
}
