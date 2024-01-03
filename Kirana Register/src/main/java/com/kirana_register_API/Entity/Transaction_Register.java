package com.kirana_register_API.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_register")
public class Transaction_Register {
   /*
 ********************
    We are using SQL Database to store all transaction data
    * (PostgreSQL - pgAdmin4)
 ********************
 * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                   // key to identify transaction
    private String name;
    private String description;
    private String credit_amount;
    private String debit_amount;

    @Column(name = "date")
    private LocalDate date;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
        if(credit_amount==null){
            credit_amount="0";
        }
        if(debit_amount==null){
            debit_amount="0";
        }
        if(description==null){
            description="";
        }


    }



}
