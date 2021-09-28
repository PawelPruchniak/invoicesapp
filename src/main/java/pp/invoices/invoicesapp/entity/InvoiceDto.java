package pp.invoices.invoicesapp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceDto {
    private String customer;
    private String accountCountry;
    private Integer amountDue;
    private Integer amountPaid;
    private Integer amountRemaining;
    private String description;

}
