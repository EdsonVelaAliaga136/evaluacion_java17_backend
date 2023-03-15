package com.mitocode.service.impl;

import com.mitocode.model.Student;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IStudentRepo;
import com.mitocode.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {
    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }
}
