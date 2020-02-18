package in.pune.royforge.eledgerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.pune.royforge.eledgerapi.data.model.Transaction;
import in.pune.royforge.eledgerapi.data.service.TransactionService;

@RestController
@RequestMapping("/tansaction")

public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@RequestMapping(value = "/transactionlog", method = RequestMethod.GET)
	public List<Transaction> getTransactions() {
		return transactionService.getTransactions();
	}
	
	@RequestMapping(value = "/lenderid/{lenderid}", method = RequestMethod.GET)
	public List<Transaction> findByLenderId(@PathVariable(value = "lenderid")String lenderId) {
		return transactionService.transactionsByLenderId(lenderId);
		 
	}
	
	
}
