package pp.invoices.invoicesapp.entity;

public class InvoiceDto {
    private String customer;
    private String account_name;
    private String account_country;
    private Integer amount_due;
    private Integer amount_paid;
    private Integer amount_remaining;
    private String description;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer( String aCustomerId ) {
        customer = aCustomerId;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name( String aAccount_name ) {
        account_name = aAccount_name;
    }

    public String getAccount_country() {
        return account_country;
    }

    public void setAccount_country( String aAccount_country ) {
        account_country = aAccount_country;
    }

    public Integer getAmount_due() {
        return amount_due;
    }

    public void setAmount_due( Integer aAmount_due ) {
        amount_due = aAmount_due;
    }

    public Integer getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid( Integer aAmount_paid ) {
        amount_paid = aAmount_paid;
    }

    public Integer getAmount_remaining() {
        return amount_remaining;
    }

    public void setAmount_remaining( Integer aAmount_remaining ) {
        amount_remaining = aAmount_remaining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }
}
