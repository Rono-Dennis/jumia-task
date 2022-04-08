package com.example.jumiatask.controller;


import com.example.jumiatask.entity.Customer;
import com.example.jumiatask.service.PhoneValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
public  class CustomerController   {

    @Autowired
    private PhoneValidatorService phoneValidatorService;


    /**Fetch
     * all the available
     * customers**/
    @RequestMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = phoneValidatorService.getAllCustomers();
        customers.forEach(customer -> {
            phoneValidatorService.validate(customer);
        });

        return ResponseEntity.ok(customers);
    }

    /**Fetch
     * customers
     * by country**/
    @GetMapping("/customers/{country}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountry(@PathVariable(name = "country") String country){

        List<Customer> customersPhoneNumbersWithCountry = phoneValidatorService.getPhoneNumbersWithCountry(country);
        return ResponseEntity.ok(customersPhoneNumbersWithCountry);
    }


    /**Fetch
     * customers
     * by countryCode**/
    @PostMapping("/customers/{countryCode}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountryCode(@PathVariable(name = "countryCode") String countryCode)
    {
        List<Customer> customersPhoneNumbersWithCountryCode = phoneValidatorService.getPhoneNumbersWithCountryCode(countryCode);

        return ResponseEntity.ok(customersPhoneNumbersWithCountryCode);
    }

    /**Fetch
     * customers by
     * country and state **/
    @RequestMapping(value = "/customers/{country}/{state}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithState(@PathVariable String state,@PathVariable String country){

        List<Customer> customersPhoneNumbersWithState = phoneValidatorService.getPhoneNumbersWithState(country,state);
        return ResponseEntity.ok(customersPhoneNumbersWithState);
    }

}
