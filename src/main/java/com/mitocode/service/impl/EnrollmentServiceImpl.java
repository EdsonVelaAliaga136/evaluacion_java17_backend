package com.mitocode.service.impl;

import com.mitocode.model.Enrollment;
import com.mitocode.repository.IEnrollmentRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, Integer> implements IEnrollmentService {

    private final IEnrollmentRepo repo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return repo;
    }
}
