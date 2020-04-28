package in.pune.royforge.eledgerapi.data.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.pune.royforge.eledgerapi.data.entity.TransactionEntity;
import in.pune.royforge.eledgerapi.data.model.Transaction;
import in.pune.royforge.eledgerapi.data.repo.TransactionLogRepository;

@Repository
public class TransactionDAOImpl implements ITransactionDAO {

	@Autowired(required = true)
	TransactionLogRepository transactionLogRepository;

	/*
	 * Method to get the details of a transaction by lenderId and Date
	 */
	@Override
	public List<Transaction> transactionListByLenderIdAndDate(String lenderId, Date date) {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionsList = transactionLogRepository
				.transactionListByLenderIdAndStratDate(lenderId, date);
		if (!transactionsList.isEmpty()) {
			for (TransactionEntity transactionEntity : transactionsList) {
				Transaction transaction = new Transaction();
				setTransactionData(transactionEntity, transaction);
				transactions.add(transaction);
			}
		}
		return transactions;
	}

	/*
	 * Method to return a list List contains the details of transaction done between
	 * a particular lender ID and borrower ID Lender Id and Borrower ID is passed as
	 * argument to this method
	 * 
	 */
	@Override
	public List<Transaction> getTransactionsUsingLenderIdAndBorrowerId(String lenderId, String borrowerId) {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionsList = transactionLogRepository.transactionsList(lenderId, borrowerId);
		if (!transactionsList.isEmpty()) {
			for (TransactionEntity transaction : transactionsList) {
				Transaction transactionInfo = new Transaction();
				setTransactionData(transaction, transactionInfo);
				transactions.add(transactionInfo);
			}
		}
		return transactions;
	}

	// Method is used to display the list of transaction logs
	@Override
	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		Iterable<TransactionEntity> transactionlogs = transactionLogRepository.findAll();
		if (null != transactionlogs) {
			for (TransactionEntity transactionEntity : transactionlogs) {
				Transaction transactionData = new Transaction();
				setTransactionData(transactionEntity, transactionData);
				transactions.add(transactionData);
			}
		}
		return transactions;
	}

	// Method is used to fetch the data from the transaction table in the object
	// transaction();
	private void setTransactionData(TransactionEntity transactionEntity, Transaction transactionData) {
		transactionData.setTransactionId(transactionEntity.getTransactionId());
		transactionData.setWalletId(transactionEntity.getWalletId());
		transactionData.setlenderId(transactionEntity.getlenderId());
		transactionData.setBorrowerId(transactionEntity.getBorrowerId());
		transactionData.setTxnType(transactionEntity.getTxnType());
		transactionData.setAmount(transactionEntity.getAmount());
		transactionData.setComment(transactionEntity.getComment());
		transactionData.setDate(transactionEntity.getDate());
	}

	/*
	 * Method to return a list of transactions for a specific lender between
	 * startDate and endDate. Input: lenderId, startDate, endDate. Output: list of
	 * transaction between two dates.
	 */
	@Override
	public List<Transaction> getListOfTransactionBetweenTwoDates(String lenderId, Date startDate, Date endDate) {
		List<Transaction> transactions = new ArrayList<>();
		List<TransactionEntity> transactionsList = transactionLogRepository.transactionListBetweenTwoDates(lenderId,
				startDate, endDate);
		for (TransactionEntity transactionEntity : transactionsList) {
			Transaction transaction = new Transaction();
			setTransactionData(transactionEntity, transaction);
			transactions.add(transaction);
		}
		return transactions;
	}

	// Method is used to fetch the transaction by taking lender id;
	@Override
	public List<Transaction> transactionsByLenderId(String lenderId) {
		List<Transaction> transactionList = new ArrayList<>();
		List<TransactionEntity> transactionLogs = transactionLogRepository.findByLenderId(lenderId);
		if (!transactionLogs.isEmpty()) {
			for (TransactionEntity transactionEntity : transactionLogs) {
				Transaction transactionData = new Transaction();
				setTransactionData(transactionEntity, transactionData);
				transactionList.add(transactionData);
			}
		}
		return transactionList;
	}
}