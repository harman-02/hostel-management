package com.hms.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.security.Timestamp;
import java.util.Date;

@Data
public class Complaint {
    @Id
    private Integer complaintId;
    private Integer rollNo;
    private String description;
    private Date date_of_complain;
    private String status;
}
