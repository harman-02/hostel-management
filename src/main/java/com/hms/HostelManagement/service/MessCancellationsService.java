package com.hms.HostelManagement.service;
import com.hms.HostelManagement.model.MessCancellations;

import java.util.Date;
import java.util.List;
public interface MessCancellationsService {
    List<MessCancellations> getAll();
    void createMessCancellation(MessCancellations m);
    void updateMessCancellation(MessCancellations messCancellations);
    MessCancellations getById(Integer id);
    void deleteMessCancellations(Integer id);
    List<MessCancellations> filterbyId(Integer hostelRegistrationid);
    List<MessCancellations> filterById(Integer hostelRegistrationid);
    public List<MessCancellations> filterBysession(int year);
    public List<MessCancellations> filterBysessionandhostel(Integer hostelRegistrationid,int year);
}
