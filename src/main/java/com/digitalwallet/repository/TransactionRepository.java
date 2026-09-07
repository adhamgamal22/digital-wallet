package com.digitalwallet.repository;

import com.digitalwallet.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query("SELECT t FROM Transaction t WHERE t.senderWalletId = :walletId OR t.recipientWalletId = :walletId")
    Page<Transaction> findByWalletId(String walletId, Pageable pageable);

    List<Transaction> findBySenderWalletIdOrderByCreatedAtDesc(String senderWalletId);
}