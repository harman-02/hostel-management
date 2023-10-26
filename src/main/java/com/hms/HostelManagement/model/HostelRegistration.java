package com.hms.HostelManagement.model;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class HostelRegistration {
    @Id
    private Integer hostelId;
    @Id
    private Integer session;
    private String hostelWardenId;
}
