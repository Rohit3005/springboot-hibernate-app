package com.demo.dao;

import com.demo.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeImpl implements EmployeeDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee);
        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public Employee getDataBy(int empId) {
        Session session = factory.openSession();
        return (Employee) session.get(Employee.class, empId);
    }

    @Override
    public List<Employee> getAllData() {
        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public List<Employee> getDataByName(String empName) {

        return getAllData().stream().filter(e -> e.getEmpName().equals(empName)).collect(Collectors.toList());
    }

    @Override
    public void updateData(int empId, Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee1 = getDataBy(empId);
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        session.update(employee1);
        transaction.commit();

    }

    @Override
    public void deleteDataById(int empId) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = getDataBy(empId);
        session.delete(employee);
        transaction.commit();
    }

    @Override
    public void deleteAllData() {

        Session session = factory.openSession();
        for (Employee employee : getAllData()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }

    }
}
