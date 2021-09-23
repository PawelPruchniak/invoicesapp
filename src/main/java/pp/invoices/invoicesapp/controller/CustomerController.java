package pp.invoices.invoicesapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pp.invoices.invoicesapp.entity.CustomerDto;
import pp.invoices.invoicesapp.service.CustomerService;

import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMERS_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMERS_LIST;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER_FORM;
import static pp.invoices.invoicesapp.enums.CustomerStrings.CUSTOMER_POST_RESULT;
import static pp.invoices.invoicesapp.enums.CustomerStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.EMAIL_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.CustomerStrings.NAME_ATTRIBUTE;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * @param aCustomerDto - creating Customer
     * @param aModel       - Model for created Customer
     * @return - #customer_post_form.html with POST request results
     * @throws StripeException - Stripe Exception
     */
    @PostMapping("/customer")
    public String createCustomer( @ModelAttribute(value = CUSTOMER_ATTRIBUTE) CustomerDto aCustomerDto,
                                  Model aModel ) throws StripeException {
        Customer customer = customerService.create( aCustomerDto );

        aModel.addAttribute( ID_ATTRIBUTE, customer.getId() );
        aModel.addAttribute( NAME_ATTRIBUTE, customer.getName() );
        aModel.addAttribute( DESCRIPTION_ATTRIBUTE, customer.getDescription() );
        aModel.addAttribute( EMAIL_ATTRIBUTE, customer.getEmail() );

        return CUSTOMER_POST_RESULT;
    }

    /**
     * @param aModel - Model taken from form
     * @return Form to fill and create Customer
     */
    @GetMapping("/customer")
    public String getCustomerForm( Model aModel ) {
        CustomerDto customerDto = new CustomerDto();

        aModel.addAttribute( CUSTOMER_ATTRIBUTE, customerDto );

        return CUSTOMER_FORM;
    }

    /**
     * @return list of All Customers
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCustomers( Model aModel ) throws StripeException {

        aModel.addAttribute( CUSTOMERS_ATTRIBUTE, customerService.listAll() );

        return CUSTOMERS_LIST;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model aModel, StripeException aStripeException ) {
        aModel.addAttribute( "error", aStripeException.getMessage() );

        return CUSTOMER_POST_RESULT;
    }
}
