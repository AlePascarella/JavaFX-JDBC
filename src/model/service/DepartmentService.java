package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	
	// Retornar uma lista de departamentos
	public List<Department> findAll () {
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Books"));
		list.add(new Department(2, "Computer"));
		list.add(new Department(3, "Electronics"));
		return list;
	}
}
