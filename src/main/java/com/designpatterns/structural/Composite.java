package com.designpatterns.structural;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Composite {

    public static void main (String ... args) {
        Employee developer1 = new Developer(2, "dev-1", "IT", "Java");
        Employee developer2 = new Developer(3, "dev-2", "IT", ".Net");
        Employee developer3 = new Developer(3, "devOps-1", "IT", "devOps");
        Manager manager = new Manager(1, "manager", "IT");
        manager.addEmployee(developer1);
        manager.addEmployee(developer2);
        manager.addEmployee(developer3);

        StringJoiner sj = new StringJoiner(",");
        for(List<String> list : manager.getEmployeeDetails().values()) {
            list.stream().forEach(str -> sj.add(str));
        }

        System.out.println(sj);

        sj.setEmptyValue("");
        manager.removeEmployee(developer2);
        for(List<String> list : manager.getEmployeeDetails().values()) {
            list.stream().forEach(str -> sj.add(str));
        }

        System.out.println(sj);
    }
}

//Component
class Employee {
    private int id;
    private String name;
    private String dept;

    public Employee(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public Map<String, List<String>> getEmployeeDetails() {
        Map<String, List<String>> map = new HashMap();
        map.put("id", Arrays.asList(String.valueOf(id), name, dept));

        return map;
    }
}

//leaf
class Developer extends Employee {
    private String technology;

    public Developer(int id, String name, String dept, String technology) {
        super(id, name, dept);
        this.technology = technology;
    }

    @Override
    public Map<String, List<String>> getEmployeeDetails() {
        Map<String, List<String>> map = new HashMap();
        map.put(String.valueOf(super.getId()), Arrays.asList(String.valueOf(super.getId()), super.getName(), super.getDept(), technology));

        return map;
    }
}

//composite
class Manager extends Employee {
    private volatile List<Employee> employeeList;

    public Manager(int id, String name, String dept) {
        super(id, name, dept);
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Map<String, List<String>> getEmployeeDetails() {
        Map<String, List<String>> map = new HashMap();
        for (Employee employee : employeeList) {
            map.putAll(employee.getEmployeeDetails());
        }

        return map;
    }

    public synchronized void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public synchronized void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }
}
