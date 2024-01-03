package com.kirana_register_API.Service;

import com.kirana_register_API.Entity.Transaction_Register;
import com.kirana_register_API.Repository.transation_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Transaction_Service {
    @Autowired
    private transation_Repository customerRepository;
    /*
     ********************
    * If an exception occurs within @Transactional methods,
    * Spring will automatically roll back the transaction
    ********************
             */

    @Transactional
    public Transaction_Register SaveTransaction(Transaction_Register transactionRegister, String currency) {
    System.out.println(ExcahangeRateConversion_Service.RateConversion());
    System.out.println(currency);
        /*
         ********************
         * Initially saving all data in table
         * as INR, If user enters USD amount
         * it'll be converted into INR then
         * saved into DB.
         ********************
         * */
        try {
            if (("\"USD\"").equals(currency)) {
                System.out.println(ExcahangeRateConversion_Service.RateConversion());
                convertAmounts(transactionRegister);
            }
            return customerRepository.save(transactionRegister);
        } catch (Exception e) {
            e.printStackTrace();

            throw new RuntimeException("Failed to save transaction",e);
        }

    }
    private void convertAmounts(Transaction_Register transactionRegister) {
    BigDecimal usdRate = ExcahangeRateConversion_Service.RateConversion();
        /*
         ********************
         * handling null values
         * for credit and debit amounts
         * to ensure consistency
         ********************
         * */


    if (transactionRegister.getCredit_amount()==null || transactionRegister.getCredit_amount().isEmpty())
        transactionRegister.setCredit_amount("0.00");
    BigDecimal creditAmount = new BigDecimal(transactionRegister.getCredit_amount()).multiply(usdRate);
    transactionRegister.setCredit_amount(creditAmount.toString());

    if(transactionRegister.getDebit_amount()==null || transactionRegister.getDebit_amount().isEmpty())
        transactionRegister.setDebit_amount("0.00");

    BigDecimal debitAmount = new BigDecimal(transactionRegister.getDebit_amount()).multiply(usdRate);
    transactionRegister.setDebit_amount(debitAmount.toString());
    }
    /*
     ********************
     * finding all transaction
     * records by date
     ********************
     * */
    @Transactional(readOnly = true)
    public List<Transaction_Register> getAllTransactionsByDate(LocalDate date){

        return customerRepository.findByDate(date);

    }
    /*
     ********************
     * to fetch daily(today's)
     * transaction
     * report
     ********************
     * */
    @Transactional(readOnly = true)
    public Map<LocalDate, List<Transaction_Register>> getDailyReports() {
        List<Transaction_Register> allTransactions = customerRepository.findAll();
        return allTransactions.stream()
                .collect(Collectors.groupingBy(Transaction_Register::getDate));
    }

    /*
     ********************
     * to update
     * daily
     * transaction
     * report by id
     ********************
     * */
    @Transactional
    public void updateTransaction(Long id, Transaction_Register transactionRegister) {
        Transaction_Register existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            customerRepository.save(transactionRegister);

        }

    }
    /*
     ********************
     * to delete
     * daily
     * transaction
     * report by id
     ********************
     * */
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
