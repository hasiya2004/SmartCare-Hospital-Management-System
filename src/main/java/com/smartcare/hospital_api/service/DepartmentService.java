package com.smartcare.hospital_api.service;

import com.smartcare.hospital_api.entity.Department;
import com.smartcare.hospital_api.exception.ResourceNotFoundException;
import com.smartcare.hospital_api.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Transactional
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, Department updated) {
        Department existing = getDepartmentById(id);
        existing.setName(updated.getName());
        existing.setLocation(updated.getLocation());
        if (updated.getHeadDoctor() != null) {
            existing.setHeadDoctor(updated.getHeadDoctor());
        }
        return departmentRepository.save(existing);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) throw new ResourceNotFoundException("Department not found");
        departmentRepository.deleteById(id);
    }
}