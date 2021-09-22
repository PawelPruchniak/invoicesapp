package pp.invoices.invoicesapp.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pp.invoices.invoicesapp.entity.CustomerDto;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static pp.invoices.invoicesapp.enums.CustomerStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.EMAIL_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.LIMIT_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.NAME_ATTRIBUTE;

@Service
public class CustomerService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Value("${CUSTOMERS_LIST_LIMIT}")
    private Integer customersLimit;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Customer create( CustomerDto aCustomerDto ) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();

        customerParams.put( NAME_ATTRIBUTE, aCustomerDto.getName() );
        customerParams.put( EMAIL_ATTRIBUTE, aCustomerDto.getEmail() );
        customerParams.put( DESCRIPTION_ATTRIBUTE, aCustomerDto.getDescription() );

        return Customer.create( customerParams );
    }

    public CustomerCollection listAll() throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put( LIMIT_ATTRIBUTE, customersLimit );

        return Customer.list( params );
    }
}
