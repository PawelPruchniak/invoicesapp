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

        invoicesParams.put( CUSTOMER_ID_ATTRIBUTE, aInvoiceDto.getCustomer() );
        invoicesParams.put( DESCRIPTION_ATTRIBUTE, aInvoiceDto.getDescription() );

        return Invoice.create( invoicesParams );
    }

    public InvoiceCollection listAll() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put( LIMIT_ATTRIBUTE, invoicesLimit );

        return Invoice.list( params );
    }
}
