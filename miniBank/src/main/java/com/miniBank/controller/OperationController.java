package com.miniBank.controller;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.miniBank.model.AccountDetails;
import com.miniBank.model.PaginatorData;
import com.miniBank.model.Transactions;
import com.miniBank.model.UsersAccounts;
import com.miniBank.repos.TransactionRepository;
import com.miniBank.repos.UserAccountRepository;
import com.miniBank.service.TransactionService;
import com.miniBank.service.PaginatorService;
import com.miniBank.service.TransactionData;

@Controller
public class OperationController {
 
	@Autowired
	private UserAccountRepository userAccountRepo;
	@Autowired
    private TransactionRepository transactionsRepo;
	
	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> transactionData(@RequestBody TransactionData transaction) {
		 UsersAccounts wUserAccount = userAccountRepo.findByUserId(transaction.getUserId());
		 UsersAccounts dUserAccount = userAccountRepo.findByAccountNo(transaction.getDoUserAccountNo());
		 if (wUserAccount != null && dUserAccount != null) {
			 wUserAccount.setBalance(TransactionService.balanceCounter(wUserAccount.getBalance(), 0, transaction.getWithdrawal(), wUserAccount.getCurrency(), wUserAccount.getCurrency()));
			 dUserAccount.setBalance(TransactionService.balanceCounter(dUserAccount.getBalance(), transaction.getDeposit(), 0, dUserAccount.getCurrency(), wUserAccount.getCurrency()));
			 userAccountRepo.save(wUserAccount);
			 userAccountRepo.save(dUserAccount);
			 transactionsRepo.save(TransactionService.transactionDataDeposit(transaction, dUserAccount, wUserAccount));
			 transactionsRepo.save(TransactionService.transactionDataWithdraval(transaction, dUserAccount));
			 return TransactionService.userData("balance", Double.toString(wUserAccount.getBalance()));
		 }
		return null;
	}
	
	
	@RequestMapping(value = "/operation", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userData(@RequestBody Transactions transaction) {
		 UsersAccounts ac = userAccountRepo.findByUserId(transaction.getUserId());
		 if (ac != null) {
			 ac.setBalance(TransactionService.balanceCounter(ac.getBalance(), transaction.getDeposit(), transaction.getWithdrawal(), transaction.getCurUser(), transaction.getCurDoUser()));
			 userAccountRepo.save(ac);
			 transactionsRepo.save(TransactionService.operatoinData(transaction));
			 return TransactionService.userData("balance", Double.toString(ac.getBalance()));
		 }
		return TransactionService.userDataClear();
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public @ResponseBody List<AccountDetails> accountDetails(@RequestBody PaginatorData paginator) {
		System.out.println("Paginator controller");
		return PaginatorService.accountDetails(transactionsRepo.findByUserId(paginator.getUserId()), paginator);
	}
	
	//test for find all users accounts
	@GetMapping(path = "/alltransactions")
	public @ResponseBody Iterable<Transactions> getAllTransactions() {
		return transactionsRepo.findAll();
	}
	
}
