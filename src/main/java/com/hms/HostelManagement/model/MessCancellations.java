package com.hms.HostelManagement.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


//import java.util.Date;
@Data
public class MessCancellations {
    private Integer entryNo;
    private Integer hostelRegistrationId;
    private Integer rollNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
