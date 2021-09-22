package pp.invoices.invoicesapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pp.invoices.invoicesapp.entity.CustomerDto;
import pp.invoices.invoicesapp.service.CustomerService;

@Controller
public class CustomerController {

    public static final String CUSTOMER_POST_RESULT = "customer_post_result";
    public static final String CUSTOMER_FORM = "customer_form";

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public String createCustomer( @ModelAttribute(value = "customer") CustomerDto aCustomerDto,
                                  Model aModel ) throws StripeException {
        Customer customer = customerService.create( aCustomerDto );

        aModel.addAttribute( "id", customer.getId() );
        aModel.addAttribute( "name", customer.getName() );
        aModel.addAttribute( "description", customer.getDescription() );
        aModel.addAttribute( "email", customer.getEmail() );

        return CUSTOMER_POST_RESULT;
    }

    @GetMapping("/customer")
    public String get( Model aModel ) {

        CustomerDto customerDto = new CustomerDto();
        aModel.addAttribute( "customer", customerDto );

        return CUSTOMER_FORM;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model model, StripeException ex ) {
        model.addAttribute( "error", ex.getMessage() );

        return CUSTOMER_POST_RESULT;
    }
}
