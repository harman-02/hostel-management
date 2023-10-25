package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.HostelRegistration;
import com.hms.HostelManagement.model.MessCancellations;
import com.hms.HostelManagement.repository.MessCancellationsRepository;
import com.hms.HostelManagement.service.MessCancellationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessCancellationsServiceImpl implements MessCancellationsService {
    @Autowired
    MessCancellationsRepository messCancellationsRepository;


    public MessCancellationsServiceImpl(MessCancellationsRepository messCancellationsRepository) {
        super();
        this.messCancellationsRepository = messCancellationsRepository;
    }

    @Override
    public List<MessCancellations> getAll() {
        return messCancellationsRepository.getAll();
    }

    @Override
    public void createMessCancellation(MessCancellations messCancellations,HostelRegistration hostelRegistration){
          messCancellationsRepository.createMessCancellation(messCancellations,hostelRegistration);
    }
    @Override
    public void updateMessCancellation(MessCancellations messCancellations) {
        messCancellationsRepository.updateMessCancellations(messCancellations);
    }
    @Override
    public MessCancellations getById(Integer id) {
        return messCancellationsRepository.getById(id);
    }

    @Override
    public void deleteMessCancellations(Integer id) {
        messCancellationsRepository.deleteMessCancellations(id);
    }

    @Override
    public List<MessCancellations> filterbyId(Integer hostelRegistrationid) {
        return null;
    }


    @Override
    public List<MessCancellations> filterById(Integer hostelRegistrationid) {
        return messCancellationsRepository.filterById(hostelRegistrationid);
    }

    @Override
    public List<MessCancellations> filterBySession(int year) {
        return messCancellationsRepository.filterBySession(year);
    }

    @Override
    public List<MessCancellations> filterBySessionAndHostel(Integer hostelRegistrationid, int year) {
        return messCancellationsRepository.filterBySessionAndhostel(hostelRegistrationid, year);
    }

    @Override
    public List<MessCancellations> filterByRollNo(Integer rollNo) {
        return messCancellationsRepository.filterByRollNo(rollNo);
    }

    @Override
    public List<MessCancellations> filterByDate(Date start, Date end) {
        return messCancellationsRepository.filterByDate(start, end);
    }

    @Override
    public List<MessCancellations> filterByRollNoAndSession(Integer rollNo, Integer year) {
        return messCancellationsRepository.filterByRollNoAndSession(rollNo, year);
    }

    @Override
    public List<MessCancellations> balanceByRollNoAndSession(Integer rollNo,Integer year){
         return messCancellationsRepository.balanceByRollNoAndSession(rollNo,year);
    }

}