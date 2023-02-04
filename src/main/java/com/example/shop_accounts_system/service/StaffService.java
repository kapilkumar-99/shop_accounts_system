package com.example.shop_accounts_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop_accounts_system.dto.AddStaffRequest;
import com.example.shop_accounts_system.dto.GetStaffResponse;
import com.example.shop_accounts_system.dto.StaffDTO;
import com.example.shop_accounts_system.dto.UpdateStaffRequest;
import com.example.shop_accounts_system.entity.Staff;
import com.example.shop_accounts_system.exception_handling.NotFoundException;
import com.example.shop_accounts_system.exception_handling.DeleteException;
import com.example.shop_accounts_system.repository.StaffRepository;

@Service
public class StaffService {
    
    @Autowired
    StaffRepository staffRepository;

    public StaffDTO addStaff(AddStaffRequest addStaffRequest){
        Staff staff = Staff.toEntity(addStaffRequest);
        Staff newStaff = staffRepository.save(staff);
        return StaffDTO.fromEntity(newStaff);
    }

    public StaffDTO updateStaff(String staffId, UpdateStaffRequest request) throws Exception {
        Staff existingStaff = staffRepository.findById(Integer.parseInt(staffId))
                                        .orElseThrow(()->new NotFoundException("Invalid Staff id"));
        if(request.getName() != null) {
            existingStaff.setName((request.getName()));
        }
        if(request.getCnic() != null) {
            existingStaff.setCnic(request.getCnic());
        }
        if(request.getPhoneNumber() != null) {
            existingStaff.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getSalary() > 0) {
            existingStaff.setSalary(request.getSalary());
        }
        existingStaff.setLoan(request.getLoan());

        Staff updatedStaff = staffRepository.save(existingStaff);

        return StaffDTO.fromEntity(updatedStaff);
    }
    public GetStaffResponse getStaffById(String id) throws Exception{
        Staff staff = staffRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Staff was not found with id "+id));
       GetStaffResponse getStaff = new GetStaffResponse(staff.getId(), staff.getName(), staff.getCnic(), staff.getPhoneNumber(),staff.getSalary(),staff.getLoan());
       return getStaff;
    }

    public List<GetStaffResponse> findAllStaff(){
        List<Staff> staffs = (List<Staff>) staffRepository.findAll();
        List<GetStaffResponse> allStaff = new ArrayList<>();
        for(Staff staff: staffs){
            GetStaffResponse getStaffResponse = new GetStaffResponse(staff.getId(), staff.getName(), staff.getCnic(), staff.getPhoneNumber(),staff.getSalary(),staff.getLoan());
            allStaff.add(getStaffResponse);
        }
        return allStaff;
    }
    public void staffDeleteById(String id) throws Exception{
        Staff staff = staffRepository.findById(Integer.parseInt(id)).orElseThrow(()-> new Exception("Staff was not found with id "+id));
        staffRepository.deleteById(Integer.parseInt(id));
        throw new DeleteException("Staff was sucessfully delete with id "+id);
    }
}
