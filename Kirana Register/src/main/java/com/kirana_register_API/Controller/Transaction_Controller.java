package com.kirana_register_API.Controller;

import com.kirana_register_API.Entity.Transaction_Register;
import com.kirana_register_API.Repository.transation_Repository;
import com.kirana_register_API.Service.Transaction_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/KiranaRegisterAPI")
public class Transaction_Controller {
    /*
    * Refer API_Documentation file path :
    *  resources/static/API_Documentation
    * */

    @Autowired
    private transation_Repository customerRepository;
    @Autowired
    private Transaction_Service transactionService;

    @PostMapping("/save")
    public void insertCustomer(@RequestBody Transaction_Register customerRegister, @RequestParam(name = "currency", required = true) String currency) {

        transactionService.SaveTransaction( customerRegister, currency );
    }
    @GetMapping("/getAllTransactions")
    public List<Transaction_Register> getAllTransactions() {
        return customerRepository.findAll();
    }
    @GetMapping("/getTransactionByDate")
    public List<Transaction_Register> getTransactionByDate( @RequestParam("date") String date ) {
        System.out.println("Date given" + date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println("Date given" + localDate);
        return transactionService.getAllTransactionsByDate(localDate);
    }
    @GetMapping("/getDailyReports")
    public Map<LocalDate, List<Transaction_Register>> getDailyReports() {
        return transactionService.getDailyReports();
    }
    @PutMapping("/update/{id}")
    public void updateTransaction(@PathVariable Long id, @RequestBody Transaction_Register customerRegister) {
        transactionService.updateTransaction(id, customerRegister);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        transactionService.deleteCustomer(id);
    }
}



