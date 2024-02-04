package com.agnibank.agniBankAccount.repo;

import com.agnibank.agniBankAccount.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<Accounts, Integer> {

    @Modifying
    @Query(value = "SELECT * FROM accounts WHERE user_id = :id", nativeQuery = true)
    List<Accounts> findByUserId(Integer id);
}
