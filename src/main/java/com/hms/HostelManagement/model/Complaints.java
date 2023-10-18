package com.hms.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.security.Timestamp;

@Data
public class Complaints {
    @Id
    private Integer complaintId;
    private Integer rollNo;
    private String description;
    private Timestamp timeStamp;
    private String status;
}
