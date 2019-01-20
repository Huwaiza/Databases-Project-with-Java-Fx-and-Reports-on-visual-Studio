package ProjectDatabases;

public class GasBillList {
    private String BillId;
    private String BillFine;
    private String BillDate;
    private String Amount;

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }
    public String getAmount() {
        return Amount;
    }
    public void setAmount(String amount) {
        Amount = amount;
    }
    public String getBillFine() {
        return BillFine;
    }

    public void setBillFine(String billFine) {
        BillFine = billFine;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public GasBillList(String billId, String billFine, String billDate, String Amount) {
        this.BillId = billId;
        this.BillFine = billFine;
        this.BillDate = billDate;
        this.Amount = Amount;
    }
}
