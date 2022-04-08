package com.example.jumiatask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PhoneValidatorService {


    @Autowired
    private CustomerRepository customerRepository;

    public final  String CAMEROON_VALIDATOR = "\\(237\\) ?[2368]\\d{7,8}$";
    public final  String CAMEROON_CODE = "(237)";
    public final  String COUNTRY_CAMEROON = "CAMEROON";

    public final  String ETHIOPIA_VALIDATOR = "\\(251\\) ?[1-59]\\d{8}$";
    public final  String ETHIOPIA_CODE = "(251)";
    public final  String COUNTRY_ETHIOPIA = "ETHIOPIA";

    public final String MOROCCO_VALIDATOR = "\\(212\\) ?[5-9]\\d{8}$";
    public final String MOROCCO_CODE = "(212)";
    public final  String COUNTRY_MOROCCO = "MOROCCO";

    private final String MOZAMBIQUE_VALIDATOR = "\\(258\\) ?[28]\\d{7,8}$";
    public final String MOZAMBIQUE_CODE = "(258)";
    public final  String COUNTRY_MOZAMBIQUE = "MOZAMBIQUE";

    private final String UGANDA_VALIDATOR = "\\(256\\) ?\\d{9}$";
    public final String UGANDA_CODE = "(256)";
    public final  String COUNTRY_UGANDA = "UGANDA";


    Customer validateCountry(Customer customer) {
        String phoneCode = customer.getPhone().substring(1,4);
        String restPhone = customer.getPhone().substring(6);
        customer.setWholePhoneNumber("+" + phoneCode + restPhone);
        int code = Integer.parseInt(phoneCode);
        System.out.println(code);
        switch (code) {
            case 237 -> {
                customer.setCountry(COUNTRY_CAMEROON);
                customer.setCountryCode(CAMEROON_CODE);
                if ((Pattern.compile(CAMEROON_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                    customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
            }
            case 251 -> {
                customer.setCountry(COUNTRY_ETHIOPIA);
                customer.setCountryCode(ETHIOPIA_CODE);
                if ((Pattern.compile(ETHIOPIA_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                    customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
            }
            case 212 -> {
                customer.setCountry(COUNTRY_CAMEROON);
                customer.setCountryCode(MOROCCO_CODE);
                if ((Pattern.compile(MOROCCO_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                    customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
            }
            case 258 -> {
                customer.setCountry(COUNTRY_MOZAMBIQUE);
                customer.setCountryCode(MOZAMBIQUE_CODE);
                if ((Pattern.compile(MOZAMBIQUE_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                    customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
            }
            case 256 -> {
                customer.setCountry(COUNTRY_UGANDA);
                customer.setCountryCode(UGANDA_CODE);
                if ((Pattern.compile(UGANDA_VALIDATOR)).matcher(customer.getPhone()).matches()) {
                    customer.setState("VALID");
                } else {
                    customer.setState("INVALID");
                }
            }
            default -> customer.setState("Country does not have Jumia");
        }



        return customer;
    }



    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

//    Validate function

    public void validate(Customer customer){
        validateCountry(customer);
    }

    String validateCountries(Customer customer){
        switch (customer.getCountry())
        {
            case COUNTRY_CAMEROON -> {return COUNTRY_CAMEROON;}
            case COUNTRY_ETHIOPIA -> {return COUNTRY_ETHIOPIA;}
            case COUNTRY_MOROCCO -> {return COUNTRY_MOROCCO;}
            case COUNTRY_MOZAMBIQUE -> {return COUNTRY_MOZAMBIQUE;}
            case COUNTRY_UGANDA -> {return COUNTRY_UGANDA;}
        }
        return  null;
    }

    private List<Customer> PhoneNumbersWithCountry(Customer customer) {

        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .collect(Collectors.toList());
    }

    public List<Customer> getPhoneNumbersWithCountry(String country){

        String cntry  = country.toUpperCase(Locale.ROOT);
        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getCountry()
                        .equals(cntry))
                .collect(Collectors.toList());
    }


    public List<Customer> getPhoneNumbersWithState(String state){

        String States  = state.toUpperCase(Locale.ROOT);
        return getAllCustomers()
                .stream()
                .map(this::validateCountry)
                .filter(customer -> customer
                        .getState()
                        .toUpperCase(Locale.ROOT)
                        .equals(States))
                .collect(Collectors.toList());
    }


    public List<Customer> getPhoneNumbersWithCountryCode(String countryCode){

        String cCode = countryCode.substring(0,4);
        return getAllCustomers()
                .stream()
                .filter(customer -> customer
                        .getCountryCode()
                        .substring(0,4)
                        .equals(cCode))
                .collect(Collectors.toList());
    }

    public List<Customer> getPhoneNumbersWithPhoneNumber(String phoneNumber){

        return getAllCustomers()
                .stream()
                .filter(customer -> customer
                        .getWholePhoneNumber()
                        .equals(phoneNumber))
                .collect(Collectors.toList());
    }
}




