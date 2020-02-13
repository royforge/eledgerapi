package in.pune.royforge.eledgerapi.data.dao;

import java.util.List;

import in.pune.royforge.eledgerapi.data.model.Transaction;

public interface ITransactionDAO {

	List<Transaction> walletTransactionLog(String lenderId, String borrowerId);

}