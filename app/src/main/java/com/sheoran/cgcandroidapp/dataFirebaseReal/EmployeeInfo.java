package com.sheoran.cgcandroidapp.dataFirebaseReal;

public class EmployeeInfo {

    private String employeeName,employeeContactNumber,employeeAddress;

    public EmployeeInfo(String employeeName, String employeeContactNumber, String employeeAddress) {
        this.employeeName = employeeName;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeAddress = employeeAddress;
    }

    public EmployeeInfo() {

    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(String employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
}
