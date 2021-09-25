package pp.invoices.invoicesapp.entity;

public class InvoiceItemDto {
    private String customer;
    private Integer amount;
    private String currency;
    private String description;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer( String aCustomer ) {
        customer = aCustomer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount( Integer aAmount ) {
        amount = aAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency( String aCurrency ) {
        currency = aCurrency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }
}
