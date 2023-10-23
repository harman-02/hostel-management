package com.hms.HostelManagement.service.Impl;

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
    public void createMessCancellation(MessCancellations messCancellations) {
        messCancellationsRepository.createMessCancellation(messCancellations);
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
    public  List<MessCancellations> filterById(Integer hostelRegistrationid){
       return messCancellationsRepository.filterById(hostelRegistrationid);
    }
    @Override
    public List<MessCancellations> filterBysession(int year){
        return messCancellationsRepository.filterBysession(year);
    }

    @Override
    public List<MessCancellations> filterBysessionandhostel(Integer hostelRegistrationid,int year){
        return messCancellationsRepository.filterBysessionandhostel(hostelRegistrationid,year);
    }

}