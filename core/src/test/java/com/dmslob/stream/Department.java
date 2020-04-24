package com.dmslob.stream;

public class Department {
    Integer employeeId;
    String department;

    public Department(Integer employeeId, String department) {
        this.employeeId = employeeId;
        this.department = department;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }
}
