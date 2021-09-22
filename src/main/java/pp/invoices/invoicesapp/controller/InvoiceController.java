package pp.invoices.invoicesapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pp.invoices.invoicesapp.entity.InvoiceDto;
import pp.invoices.invoicesapp.service.InvoiceService;

import static pp.invoices.invoicesapp.enums.InvoiceStrings.ACCOUNT_COUNTRY_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.ACCOUNT_NAME_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_DUE_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_PAID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.AMOUNT_REMAINING_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.CUSTOMER_ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.INVOICES_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.INVOICES_LIST;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.INVOICE_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.INVOICE_FORM;
import static pp.invoices.invoicesapp.enums.InvoiceStrings.INVOICE_POST_RESULT;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * @param aInvoiceDto - creating Invoice
     * @param aModel      - Model for created Invoice
     * @return - #invoice_form.html with POST request results
     * @throws StripeException - Stripe Exception
     */
    @PostMapping("/invoice")
    public String createCustomer( @ModelAttribute(value = INVOICE_ATTRIBUTE) InvoiceDto aInvoiceDto,
                                  Model aModel ) throws StripeException {
        Invoice invoice = invoiceService.create( aInvoiceDto );

        aModel.addAttribute( ID_ATTRIBUTE, invoice.getId() );
        aModel.addAttribute( CUSTOMER_ID_ATTRIBUTE, invoice.getCustomer() );
        aModel.addAttribute( ACCOUNT_COUNTRY_ATTRIBUTE, invoice.getAccountCountry() );
        aModel.addAttribute( ACCOUNT_NAME_ATTRIBUTE, invoice.getAccountName() );
        aModel.addAttribute( AMOUNT_DUE_ATTRIBUTE, invoice.getAmountDue() );
        aModel.addAttribute( AMOUNT_PAID_ATTRIBUTE, invoice.getAmountPaid() );
        aModel.addAttribute( AMOUNT_REMAINING_ATTRIBUTE, invoice.getAmountRemaining() );
        aModel.addAttribute( DESCRIPTION_ATTRIBUTE, invoice.getDescription() );

        return INVOICE_POST_RESULT;
    }

    /**
     * @param aModel - Model taken from form
     * @return Form to fill and create Invoice
     */
    @GetMapping("/invoice")
    public String getCustomerForm( Model aModel ) {
        InvoiceDto invoiceDto = new InvoiceDto();

        aModel.addAttribute( INVOICE_ATTRIBUTE, invoiceDto );

        return INVOICE_FORM;
    }

    /**
     * @return list of All Invoices
     */
    @GetMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllCustomers( Model aModel ) throws StripeException {

        aModel.addAttribute( INVOICES_ATTRIBUTE, invoiceService.listAll() );

        return INVOICES_LIST;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model aModel, StripeException aStripeException ) {
        aModel.addAttribute( "error", aStripeException.getMessage() );

        return INVOICE_POST_RESULT;
    }
}
