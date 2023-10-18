package com.hms.HostelManagement.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Notice {
    @Id
    private Integer noticeId;
    private Integer hostelRegistrationId;
    private String description;
    private Date date;
}
