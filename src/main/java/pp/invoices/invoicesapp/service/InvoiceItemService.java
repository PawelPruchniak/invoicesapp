package pp.invoices.invoicesapp.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceItemCollection;
import org.springframework.beans.factory.annotation.Value;
import pp.invoices.invoicesapp.entity.InvoiceItemDto;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.AMOUNT_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.CURRENCY_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.CUSTOMER_ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.LIMIT_ATTRIBUTE;

public class InvoiceItemService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Value("${INVOICE_ITEMS_LIST_LIMIT}")
    private Integer invoiceItemsLimit;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public InvoiceItem create( InvoiceItemDto aInvoiceItemDto ) throws StripeException {
        Map<String, Object> invoiceItemParams = new HashMap<>();

        invoiceItemParams.put( CUSTOMER_ID_ATTRIBUTE, aInvoiceItemDto.getCustomer() );
        invoiceItemParams.put( AMOUNT_ATTRIBUTE, aInvoiceItemDto.getAmount() );
        invoiceItemParams.put( CURRENCY_ATTRIBUTE, aInvoiceItemDto.getCurrency() );
        invoiceItemParams.put( DESCRIPTION_ATTRIBUTE, aInvoiceItemDto.getDescription() );

        return InvoiceItem.create( invoiceItemParams );
    }

    public InvoiceItemCollection listAll() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put( LIMIT_ATTRIBUTE, invoiceItemsLimit );

        return InvoiceItem.list( params );
    }


}
