package com.hms.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class StudentUserMapping {
    @Id
    private String username;
    @Id
    private int roomNo;
    @Id
    private int sessionID;
    private int hostelId;
}
