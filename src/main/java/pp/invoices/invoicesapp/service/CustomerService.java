package pp.invoices.invoicesapp.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pp.invoices.invoicesapp.entity.CustomerDto;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Customer create( CustomerDto aCustomerDto ) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put( "name", aCustomerDto.getName() );
        customerParams.put( "email", aCustomerDto.getEmail() );
        customerParams.put( "description", aCustomerDto.getDescription() );

        return Customer.create( customerParams );
    }
}
