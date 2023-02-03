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

import com.example.shop_accounts_system.dto.AddVendorRequest;
import com.example.shop_accounts_system.dto.GetVendorResponse;
import com.example.shop_accounts_system.dto.UpdateVendorRequest;
import com.example.shop_accounts_system.dto.VendorDTO;
import com.example.shop_accounts_system.service.VendorService;

@RestController
@RequestMapping("/admin")
public class VendorController {

    @Autowired
    VendorService vendorService;
    
    @PostMapping("/add/vendor")
    public ResponseEntity<VendorDTO> addVendor(@RequestBody AddVendorRequest addVendorRequest) throws Exception{
        VendorDTO vendorDTO = vendorService.addVendor(addVendorRequest);
        return new ResponseEntity<>(vendorDTO, HttpStatus.CREATED);
    }

    @PutMapping("/vendor/update/{id}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable String id, @RequestBody UpdateVendorRequest request) throws Exception {
        System.out.println("updating vendor");
        VendorDTO vendorDTO = vendorService.updateVendor(id, request);
        return new ResponseEntity<> (vendorDTO, HttpStatus.OK);
    }

    @GetMapping("/get/vendor/{id}")
    public GetVendorResponse getVendorById(@PathVariable String id) throws Exception{
        GetVendorResponse vendor = vendorService.getVendorById(id);
        return vendor;
    }

    @GetMapping("/get/vendors")
    public List<GetVendorResponse> findAllVendor(){
        List <GetVendorResponse> vendors = vendorService.findAllVendor();
        return vendors;
    }

    @DeleteMapping("/delete/vendor/{id}")
    public void vendorDeleteById(@PathVariable String id)throws Exception{
        vendorService.vendorDeleteById(id);
    }
}
