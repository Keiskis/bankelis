package com.miniBank.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.miniBank.model.Transactions;

public interface TransactionRepository extends CrudRepository<Transactions, Integer>{
	List<Transactions> findByUserId(int userId);
}
