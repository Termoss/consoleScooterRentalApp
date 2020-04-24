package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface UserAccountRepository extends JpaRepository < UserAccount, Long> {
    @Transactional
    List<UserAccount> findByOwnerEmail(String ownerEmail);
    @Transactional
    void deleteByOwnerEmail(String ownerEmail);

}
