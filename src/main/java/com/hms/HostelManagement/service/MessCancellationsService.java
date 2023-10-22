package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.MessCancellations;

import java.util.List;

public interface MessCancellationsService {


    List<MessCancellations> getAll();



    public void createMessCancellation(MessCancellations m);
}
