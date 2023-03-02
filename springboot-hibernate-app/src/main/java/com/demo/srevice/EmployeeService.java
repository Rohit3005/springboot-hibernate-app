package com.demo.srevice;

import com.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public Employee getDataBy(int empId);

    public List<Employee> getAllData();

    public List<Employee> getDataByName(String empName);

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();
}
