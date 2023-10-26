package com.hms.HostelManagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Session {
    @Id
    private int sessionId ;
    private String sessionName;
    private Date startDate;
}
