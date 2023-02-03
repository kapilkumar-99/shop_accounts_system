package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddStaffRequest;
import com.example.shop_accounts_system.dto.GetStaffResponse;
import com.example.shop_accounts_system.dto.StaffDTO;
import com.example.shop_accounts_system.dto.UpdateStaffRequest;
import com.example.shop_accounts_system.service.StaffService;

@RestController
@RequestMapping("/admin")
public class StaffController {
    
    @Autowired
    StaffService staffService;

    @PostMapping("/add/Staff")
    public ResponseEntity<StaffDTO> addstaff(@RequestBody AddStaffRequest addStaffRequest){
        StaffDTO staffDTO = staffService.addStaff(addStaffRequest);
        return new ResponseEntity<>(staffDTO, HttpStatus.CREATED);

    }
    @PutMapping("/staff/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable String id, @RequestBody UpdateStaffRequest request) throws Exception {
        System.out.println("updating staff");
        StaffDTO staffDTO = staffService.updateStaff(id, request);
        return new ResponseEntity<> (staffDTO, HttpStatus.OK);
    }

    @GetMapping("/get/staff/{id}")
    public GetStaffResponse getStaffById(@PathVariable String id) throws Exception{
        GetStaffResponse staff = staffService.getStaffById(id);
        return staff;
    }
    @GetMapping("/get/staff")
    public List<GetStaffResponse> findAllStaff(){
        List <GetStaffResponse> staffs = staffService.findAllStaff();
        return staffs;
    }

    @DeleteMapping("/delete/staff/{id}")
    public void deleteById(@PathVariable String id)throws Exception{
        staffService.staffDeleteById(id);
    }
}
