package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Hostel;
import com.hms.HostelManagement.model.Session;

import java.util.List;

public interface HostelService {

    List<Hostel> getAllHostel();

    public void createHostel(Hostel h);

}
