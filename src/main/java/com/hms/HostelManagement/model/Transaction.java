package com.hms.HostelManagement.model;
import lombok.Data;

import java.util.Date;
@Data
public class Transaction {
    private Integer transaction_id;
    private Integer roll;
    private Integer amount;
    private Date  Date;
}
