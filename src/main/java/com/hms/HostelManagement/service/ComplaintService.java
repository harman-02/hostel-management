package com.hms.HostelManagement.service;

import com.hms.HostelManagement.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAllComplaint();
    List<Complaint> getParticularComplaint(int studentID);
    public void createComplaint(Complaint complaint);

    public void updateComplaintStatus(int complaintID);

    public List<Complaint> getComplaintByRollNo(int rollNo);
}
