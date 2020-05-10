package com.termos.scooterrental.repository;

import com.termos.scooterrental.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Transactional
    List<UserAccount> findByOwnerEmail(String ownerEmail);

    void deleteByOwnerEmail(String ownerEmail);


}
