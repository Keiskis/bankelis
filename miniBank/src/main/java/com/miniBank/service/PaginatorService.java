package com.miniBank.service;

import java.util.ArrayList;
import java.util.List;
import com.miniBank.model.AccountDetails;
import com.miniBank.model.PaginatorData;
import com.miniBank.model.Transactions;

public class PaginatorService {
 
	
	public static List<AccountDetails> accountDetails(List<Transactions> tansactionsList, PaginatorData paginator) {
		
		List<AccountDetails> acd = new ArrayList<>();
		int totalRecords = tansactionsList.size();
		int pageNumber = paginator.getPageNumber();
		int recordsPerPage = paginator.getRecordsPerPage();
		double totalPagesTmp = (Double.valueOf(totalRecords) / Double.valueOf(recordsPerPage));
		int totalPages = (totalRecords / recordsPerPage);
		if (totalPagesTmp - totalPages > 0) totalPages ++;

		if (pageNumber > totalPages) return acd;
		
		int endRecord = pageNumber * recordsPerPage;
		if (endRecord > totalRecords) endRecord = totalRecords;
		
		int firstRecord = endRecord - recordsPerPage;
		if (firstRecord < 0) firstRecord = 0;
		
		if (totalRecords > 0) {
			for (int i = firstRecord; i < endRecord; i++) {
				AccountDetails ad = new AccountDetails();
				ad.setDate(tansactionsList.get(i).getDate());
				ad.setDescription(tansactionsList.get(i).getDescription());
				ad.setSum(""+(tansactionsList.get(i).getDeposit() - tansactionsList.get(i).getWithdrawal()));
				acd.add(ad);
			}
		}
		return acd;
	}
}
