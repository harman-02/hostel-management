package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.AllMessCancellations;
import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.model.MessCancellations;

import java.util.Date;
import java.util.List;

public interface MessCancellationsService {
    List<AllMessCancellations> getAll();

    public void createMessCancellation(MessCancellations messCancellations, HostelRegistration hostelRegistration);

    void updateMessCancellation(MessCancellations messCancellations);

    MessCancellations getById(Integer id);

    void deleteMessCancellations(Integer id);

    List<MessCancellations> filterbyId(Integer hostelRegistrationid);

    List<MessCancellations> filterById(Integer hostelRegistrationid);

    List<MessCancellations> filterBySession(int year);

    List<MessCancellations> filterBySessionAndHostel(Integer hostelRegistrationid, int year);
    List<AllMessCancellations> filterByRollNo(Integer rollNo);
    List<MessCancellations> filterByDate(Date start, Date end);
    List<MessCancellations> filterByRollNoAndSession(Integer rollNo, Integer year);
    List<AllMessCancellations> findByKeyword(String keyword);
    List<AllMessCancellations> findByKeywordAndRollNo(String keyword, Integer rollNo);
     List<MessCancellations> balanceByRollNoAndSession(Integer rollNo, Integer year);


}
