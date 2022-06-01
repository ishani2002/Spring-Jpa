package com.hcl.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.modal.Employee;
@Repository
public interface EnployeeRepository extends CrudRepository <Employee, Long> {

}
