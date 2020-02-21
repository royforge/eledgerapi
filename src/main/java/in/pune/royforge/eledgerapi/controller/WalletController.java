package in.pune.royforge.eledgerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import in.pune.royforge.eledgerapi.data.model.WalletData;
import in.pune.royforge.eledgerapi.data.model.WalletTransaction;
import in.pune.royforge.eledgerapi.data.service.WalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {

	@Autowired
	WalletService walletEntityService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Boolean> createOrUpdateWallet(@RequestBody WalletTransaction walletTransaction) {
		return new ResponseEntity<Boolean>(walletEntityService.save(walletTransaction), HttpStatus.OK);
	}

	@RequestMapping(value = "/wallets", method = RequestMethod.GET)
	public List<WalletData> getWallets() {
		return walletEntityService.getWallets();
	}

	@RequestMapping(value = "/walletId/{walletId}", method = RequestMethod.GET)
	public ResponseEntity<WalletData> getWallet(@PathVariable(value = "walletId") Long walletId) {
		return new ResponseEntity<WalletData>(walletEntityService.getWallet(walletId), HttpStatus.OK);
	}

	@RequestMapping(value = "/walletId/{walletId}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable(value = "walletId") Long walletId) {
		return walletEntityService.delete(walletId);
	}

	@RequestMapping(value = "/lenderId/{lenderid}", method = RequestMethod.GET)
	public List<WalletData> findWalletsListByLenderId(@PathVariable(value = "lenderid") String lenderId) {
		return walletEntityService.findWalletsListByLenderId(lenderId);
	}

	@RequestMapping(value = "/lenderId/{lenderId}/borrowId/{borrowId}", method = RequestMethod.GET)
	public ResponseEntity<WalletData> getListOfWalletById(@PathVariable(value = "lenderId") String lenderId,
			@PathVariable(value = "borrowId") String borrowId) {
		return new ResponseEntity<WalletData>(walletEntityService.getWalletDataByIds(lenderId, borrowId),
				HttpStatus.OK);
	}
}