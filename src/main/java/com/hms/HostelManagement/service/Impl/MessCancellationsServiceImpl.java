package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.MessCancellations;
import com.hms.HostelManagement.repository.MessCancellationsRepository;
import com.hms.HostelManagement.service.MessCancellationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addMessCancellation(MessCancellations messCancellations) {
        messCancellationsRepository.createMessCancellation(messCancellations);
    }
}



//@Service
//public class HostelServiceImpl implements HostelService {
//    @Autowired
//    HostelRepository hostelRepository;
//
//    public HostelServiceImpl(HostelRepository hostelRepository) {
//        super();
//        this.hostelRepository = hostelRepository;
//    }
//
//    @Override
//    public List<Hostel> getAllHostel() {
//        return hostelRepository.getAllHostels();
//    }
//
//    @Override
//    public void createHostel(Hostel h) {
//        hostelRepository.createHostel(h);
//    }
//
//
//}

