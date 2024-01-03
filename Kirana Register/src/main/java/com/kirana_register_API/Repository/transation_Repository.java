package com.kirana_register_API.Repository;

import com.kirana_register_API.Entity.Transaction_Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface transation_Repository extends JpaRepository<Transaction_Register, Long> {

    List<Transaction_Register> findByDate(LocalDate date);
}
