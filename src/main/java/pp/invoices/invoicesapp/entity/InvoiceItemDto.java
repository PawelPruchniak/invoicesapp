package pp.invoices.invoicesapp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItemDto {
    private String customer;
    private Integer amount;
    private String currency;
    private String description;

}
