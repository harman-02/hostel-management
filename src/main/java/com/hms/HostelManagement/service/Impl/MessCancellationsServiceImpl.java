package com.hms.HostelManagement.service.Impl;

import com.hms.HostelManagement.model.MessCancellations;
import com.hms.HostelManagement.repository.MessCancellationsRepository;
import com.hms.HostelManagement.service.MessCancellationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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
}