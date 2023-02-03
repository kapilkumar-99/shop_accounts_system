package com.example.shop_accounts_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop_accounts_system.dto.AddVendorDueRequest;
import com.example.shop_accounts_system.dto.GetVendorDueResponse;
import com.example.shop_accounts_system.dto.UpdateVendorDueRequest;
import com.example.shop_accounts_system.dto.VendorDueDTO;
import com.example.shop_accounts_system.service.VendorDueService;

@RestController
@RequestMapping("/admin")
public class VendorDueContoller {

    @Autowired
    VendorDueService vendorDueService;

    @PostMapping("/add/vendordue")
    public ResponseEntity<VendorDueDTO> addVendorDue(@RequestBody AddVendorDueRequest addVendorDueRequest) throws Exception{
        VendorDueDTO vendorDueDTO = vendorDueService.addVendorDue(addVendorDueRequest);
        return new ResponseEntity<VendorDueDTO>(vendorDueDTO, HttpStatus.CREATED);
    }

    @PutMapping("/vendor/due/{id}")
    public ResponseEntity<VendorDueDTO> updateVendorDue(@PathVariable String id, @RequestBody UpdateVendorDueRequest request) throws Exception {
        System.out.println("updating vendor due");
        VendorDueDTO vendorDueDTO = vendorDueService.updateVendorDue(id, request);
        return new ResponseEntity<> (vendorDueDTO, HttpStatus.OK);
    }

    @GetMapping("/get/vendor/due/{id}")
    public GetVendorDueResponse getVendorDueById(@PathVariable String id) throws Exception{
        GetVendorDueResponse vendorDue = vendorDueService.getVendorDueById(id);
        return vendorDue;
    }
    @GetMapping("/get/vendor/dues")
    public List<GetVendorDueResponse> findAllVendorDue(){
        List <GetVendorDueResponse> vendorDues = vendorDueService.findAllVendorDue();
        return vendorDues;
    }
}
