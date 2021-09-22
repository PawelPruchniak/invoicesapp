package pp.invoices.invoicesapp.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pp.invoices.invoicesapp.entity.InvoiceDto;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static pp.invoices.invoicesapp.enums.InvoiceStrings.ACCOUNT_COUNTRY_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.ACCOUNT_NAME_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_DUE_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_PAID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_REMAINING_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.CUSTOMER_ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.LIMIT_ATTRIBUTE;

@Service
public class InvoiceService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Value("${INVOICES_LIST_LIMIT}")
    private Integer invoicesLimit;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Invoice create( InvoiceDto aInvoiceDto ) throws StripeException {
        Map<String, Object> invoicesParams = new HashMap<>();

        invoicesParams.put( CUSTOMER_ID_ATTRIBUTE, aInvoiceDto.getCustomerId() );
        invoicesParams.put( ACCOUNT_COUNTRY_ATTRIBUTE, aInvoiceDto.getAccount_country() );
        invoicesParams.put( ACCOUNT_NAME_ATTRIBUTE, aInvoiceDto.getAccount_name() );
        invoicesParams.put( AMOUNT_DUE_ATTRIBUTE, aInvoiceDto.getAmount_due() );
        invoicesParams.put( AMOUNT_PAID_ATTRIBUTE, aInvoiceDto.getAmount_paid() );
        invoicesParams.put( AMOUNT_REMAINING_ATTRIBUTE, aInvoiceDto.getAmount_remaining() );
        invoicesParams.put( DESCRIPTION_ATTRIBUTE, aInvoiceDto.getDescription() );

        return Invoice.create( invoicesParams );
    }

    public InvoiceCollection listAll() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put( LIMIT_ATTRIBUTE, invoicesLimit );

        return Invoice.list( params );
    }
}
