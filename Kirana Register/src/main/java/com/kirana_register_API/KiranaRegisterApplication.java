package com.kirana_register_API;

import com.kirana_register_API.Service.ExcahangeRateConversion_Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KiranaRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiranaRegisterApplication.class, args);
    }


}

/*
 ********************
Functional Requirement:-
1. Need an API for recording transactions.
   Transactions can be recorded in both INR and USD.
   Refer to this API for currency conversion - https://api.fxratesapi.com/latest.
2. An API for displaying transactions with the capability
   to group them for daily reports.
3. You can use a database of your choice SQL or NoSQL.
4. Tech Stack - JAVA and SpringBoot.
 ********************
 * Refer API_Documentation file path :  resources/static/API_Documentation
 * */

