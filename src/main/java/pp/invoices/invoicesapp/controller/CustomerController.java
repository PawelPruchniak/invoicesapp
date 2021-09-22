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

import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER_FORM;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER_POST_RESULT;
import static pp.invoices.invoicesapp.enums.CustomerStrings.DESCRIPTION;
import static pp.invoices.invoicesapp.enums.CustomerStrings.EMAIL;
import static pp.invoices.invoicesapp.enums.CustomerStrings.ID;
import static pp.invoices.invoicesapp.enums.CustomerStrings.NAME;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public String createCustomer( @ModelAttribute(value = CUSTOMER) CustomerDto aCustomerDto,
                                  Model aModel ) throws StripeException {
        Customer customer = customerService.create( aCustomerDto );

        aModel.addAttribute( ID, customer.getId() );
        aModel.addAttribute( NAME, customer.getName() );
        aModel.addAttribute( DESCRIPTION, customer.getDescription() );
        aModel.addAttribute( EMAIL, customer.getEmail() );

        return CUSTOMER_POST_RESULT;
    }

    @GetMapping("/customer")
    public String get( Model aModel ) {
        CustomerDto customerDto = new CustomerDto();

        aModel.addAttribute( CUSTOMER, customerDto );

        return CUSTOMER_FORM;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model aModel, StripeException aStripeException ) {
        aModel.addAttribute( "error", aStripeException.getMessage() );

        return CUSTOMER_POST_RESULT;
    }
}
