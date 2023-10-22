package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.MessCancellations;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface MessCancellationsService {


    List<MessCancellations> getAll();



    public void createMessCancellation(MessCancellations m);
    public void updateMessCancellation(MessCancellations messCancellations);
    public MessCancellations getById(Integer id);
    public void deleteMessCancellations(Integer id);
}
