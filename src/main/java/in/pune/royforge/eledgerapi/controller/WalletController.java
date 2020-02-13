package in.pune.royforge.eledgerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void createWallet(@RequestBody WalletTransaction walletTransaction) {
		walletEntityService.save(walletTransaction);
	}

	@RequestMapping(value = "/wallets", method = RequestMethod.GET)
	public List<WalletData> getWallets() {
		return walletEntityService.getWallets();
	}

	@RequestMapping(value = "/{walletId}", method = RequestMethod.GET)
	public WalletData getWallet(@PathVariable(value = "walletId") Long walletId) {
		return walletEntityService.getWallet(walletId);
	}
	
	@RequestMapping(value = "/delete/{walletId}", method = RequestMethod.GET)
	public void delete(@PathVariable(value = "walletId")Long walletId) {
		walletEntityService.delete(walletId);
		 
	}
}
