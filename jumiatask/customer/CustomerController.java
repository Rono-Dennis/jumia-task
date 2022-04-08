package com.example.jumiatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@CrossOrigin(origins="*")
@RestController
public class CustomerController {

    @Autowired
    private PhoneValidatorService phoneValidatorService;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = phoneValidatorService.getAllCustomers();
        customers.forEach(customer -> {
            phoneValidatorService.validate(customer);
        });

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/phoneNumbers/{country}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountry(@PathVariable(name = "country") String country){

        List<Customer> customersPhoneNumbersWithCountry = phoneValidatorService.getPhoneNumbersWithCountry(country);
        return ResponseEntity.ok(customersPhoneNumbersWithCountry);
    }

//    @RequestMapping(value = "/customers/phoneNumbers/{state}")
//    public ResponseEntity<List<Customer>> getPhoneNumbersWithState(@PathVariable String state){
//
//        List<Customer> customersPhoneNumbersWithState = phoneValidatorService.getPhoneNumbersWithState(state);
//        return ResponseEntity.ok(customersPhoneNumbersWithState);
//    }


    @RequestMapping(value = "/customers/phoneNumbers/{state}")
    public ResponseEntity<List<Customer>> getCustomersPhoneNumbersWithCountry(@PathVariable(name = "state") String state){

        String States  = state.toUpperCase(Locale.ROOT);
        List<Customer> customersPhoneNumbersWithCountry = getAllCustomers().getBody();
        Predicate<Customer> byState = customer -> customer.getState().equals(States);
        List<Customer> customersByCountry = customersPhoneNumbersWithCountry.stream().filter(byState).collect(Collectors.toList());
        System.out.println(customersByCountry);
        return ResponseEntity.ok(customersByCountry);
    }



    @RequestMapping("/customers/phoneNumbers/{countryCode}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithCountryCode(@PathVariable(name = "countryCode") String countryCode)
                                                                        {
        List<Customer> customersPhoneNumbersWithCountryCode = phoneValidatorService.getPhoneNumbersWithCountryCode(countryCode);

        return ResponseEntity.ok(customersPhoneNumbersWithCountryCode);
    }

    @RequestMapping("/customers/phoneNumbers/{phoneNumber}")
    public ResponseEntity<List<Customer>> getPhoneNumbersWithPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber){

        List<Customer> customersPhoneNumbersWithPhoneNumber = phoneValidatorService.getPhoneNumbersWithPhoneNumber(phoneNumber);
        return ResponseEntity.ok(customersPhoneNumbersWithPhoneNumber);
    }

}
