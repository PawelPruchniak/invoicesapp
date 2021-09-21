package pp.invoices.invoicesapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pp.invoices.invoicesapp.entity.CustomerDto;
import pp.invoices.invoicesapp.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public void createCustomer( @RequestBody CustomerDto aCustomerDto, Model model ) throws StripeException {

        aCustomerDto.setDescription( "Example charge" );
        Customer customer = customerService.create( aCustomerDto );

        model.addAttribute( "id", customer.getId() );
        model.addAttribute( "status", customer.getName() );
        model.addAttribute( "chargeId", customer.getDescription() );
        model.addAttribute( "balance_transaction", customer.getEmail() );
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model model, StripeException ex ) {
        model.addAttribute( "error", ex.getMessage() );
        return "result";
    }
}
