package com.hms.HostelManagement.model;

import lombok.Data;

import java.util.Date;
@Data
public class Cancellations {

    private Integer hostelRegistrationId;
    private Integer roomNo;
    private Date date;
}
