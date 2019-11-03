package com.miniBank.repos;

import org.springframework.data.repository.CrudRepository;
import com.miniBank.model.UsersAccounts;

public interface UserAccountRepository extends CrudRepository<UsersAccounts, Integer>{
	UsersAccounts findByUserId(int userId);
	UsersAccounts findByAccountNo (String accountNo);
}
