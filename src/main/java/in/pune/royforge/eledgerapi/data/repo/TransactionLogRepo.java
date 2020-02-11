package in.pune.royforge.eledgerapi.data.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import in.pune.royforge.eledgerapi.data.entity.TransactionStringEntity;


public interface TransactionLogRepo extends PagingAndSortingRepository<TransactionStringEntity, String> {
	


	public Iterable<TransactionStringEntity> findBywalletId(String walletId);


}