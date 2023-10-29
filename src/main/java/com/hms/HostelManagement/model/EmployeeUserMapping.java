package com.hms.HostelManagement.model;

import lombok.Data;

@Data
public class EmployeeUserMapping {
    private String username;
    private Integer employeeId;
    private String jobType;
    private Integer hostelRegistrationId;
}
