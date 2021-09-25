package pp.invoices.invoicesapp.entity;

import pp.invoices.invoicesapp.enums.Currency;

public class InvoiceItemDto {
    private String customer;
    private Integer amount;
    private Currency currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency( Currency aCurrency ) {
        currency = aCurrency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }
}
