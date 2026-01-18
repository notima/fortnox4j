package org.notima.api.fortnox.junit;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.notima.api.fortnox.FortnoxConstants;
import org.notima.api.fortnox.FortnoxException;
import org.notima.api.fortnox.FortnoxUtil;
import org.notima.api.fortnox.entities3.PreDefinedAccount;
import org.notima.api.fortnox.entities3.Voucher;
import org.notima.api.fortnox.entities3.VoucherRow;
import org.notima.api.fortnox.entities3.VoucherRows;

public class TestCreateVoucher extends FortnoxTest {
	
	@Test
	public void testCreateVoucher() throws Exception {
		
		Date acctDate = Calendar.getInstance().getTime();
		
		// Get default accounts
		PreDefinedAccount pda = client.getPreDefinedAccount(FortnoxConstants.PREDEFACCT_ROUNDOFF);

		try {
			
			Voucher voucher = FortnoxUtil.createSingleTransactionVoucher(
					"A", 
					acctDate,
					String.valueOf(pda.getAccount()),
					String.valueOf(pda.getAccount()),
					1, 
					"Test");
			
			voucher = client.setVoucher(voucher);
			VoucherRows ir = voucher.getVoucherRows();
			List<VoucherRow> rows = ir.getVoucherRow();
			System.out.println(voucher.getTransactionDate());
			System.out.println(voucher.getComments());
			for (VoucherRow r : rows) {
				System.out.println("  " + r.getAccount() + " " + r.getDescription() + " " + (r.getDebit() - r.getCredit()));
			}
		} catch (FortnoxException e) {
			System.out.println(e.toString());
		}
	}
	

}
