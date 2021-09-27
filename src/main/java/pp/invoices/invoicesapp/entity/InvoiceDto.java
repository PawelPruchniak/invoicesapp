package pp.invoices.invoicesapp.entity;

public class InvoiceDto {
    private String customer;
    private String accountCountry;
    private Integer amountDue;
    private Integer amountPaid;
    private Integer amountRemaining;
    private String description;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer( String aCustomerId ) {
        customer = aCustomerId;
    }

    public String getAccountCountry() {
        return accountCountry;
    }

    public void setAccountCountry( String aAccountCountry ) {
        accountCountry = aAccountCountry;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public void setAmountDue( Integer aAmountDue ) {
        amountDue = aAmountDue;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid( Integer aAmountPaid ) {
        amountPaid = aAmountPaid;
    }

    public Integer getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining( Integer aAmountRemaining ) {
        amountRemaining = aAmountRemaining;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }
}
