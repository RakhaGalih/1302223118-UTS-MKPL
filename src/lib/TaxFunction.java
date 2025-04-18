package lib;

public class TaxFunction {

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
	 * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
	 * dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
	private static final int MARRIED_DEDUCTION = 4500000;
	private static final int CHILD_DEDUCTION_PER_CHILD = 1500000;
	private static final int MAX_CHILDREN = 3;

	public static int calculateTax(
			int monthlySalary,
			int otherMonthlyIncome,
			int numberOfMonthWorking,
			int deductible,
			boolean isMarried,
			int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			throw new IllegalArgumentException("Months worked cannot exceed 12.");
		}

		int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		int taxableIncome = totalIncome - deductible - getNonTaxableIncome(isMarried, numberOfChildren);
		int tax = (int) Math.round(0.05 * Math.max(taxableIncome, 0));

		return tax;
	}

	private static int getNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int nonTaxableIncome = BASIC_NON_TAXABLE_INCOME;

		if (isMarried) {
			nonTaxableIncome += MARRIED_DEDUCTION;
			int childrenCount = Math.min(numberOfChildren, MAX_CHILDREN);
			nonTaxableIncome += childrenCount * CHILD_DEDUCTION_PER_CHILD;
		}

		return nonTaxableIncome;
	}

}
