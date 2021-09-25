package pp.invoices.invoicesapp.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pp.invoices.invoicesapp.entity.InvoiceItemDto;
import pp.invoices.invoicesapp.service.InvoiceItemService;

import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.AMOUNT_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.CURRENCY_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.CUSTOMER_ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.DESCRIPTION_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.ID_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.INVOICE_ITEMS_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.INVOICE_ITEMS_LIST;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.INVOICE_ITEM_ATTRIBUTE;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.INVOICE_ITEM_FORM;
import static pp.invoices.invoicesapp.enums.InvoiceItemStrings.INVOICE_ITEM_POST_RESULT;

@Controller
public class InvoiceItemController {

    @Autowired
    private InvoiceItemService invoiceItemService;

    /**
     * @param aInvoiceItemDto - creating InvoiceItem
     * @param aModel          - Model for created Invoice
     * @return - #invoice_form.html with POST request results
     * @throws StripeException - Stripe Exception
     */
    @PostMapping("/invoiceItem")
    public String createInvoiceItem( @ModelAttribute(value = INVOICE_ITEM_ATTRIBUTE) InvoiceItemDto aInvoiceItemDto,
                                     Model aModel ) throws StripeException {
        InvoiceItem invoiceItem = invoiceItemService.create( aInvoiceItemDto );

        aModel.addAttribute( ID_ATTRIBUTE, invoiceItem.getId() );
        aModel.addAttribute( CUSTOMER_ID_ATTRIBUTE, invoiceItem.getCustomer() );
        aModel.addAttribute( AMOUNT_ATTRIBUTE, invoiceItem.getAmount() );
        aModel.addAttribute( CURRENCY_ATTRIBUTE, invoiceItem.getCurrency() );
        aModel.addAttribute( DESCRIPTION_ATTRIBUTE, invoiceItem.getDescription() );

        return INVOICE_ITEM_POST_RESULT;
    }

    /**
     * @param aModel - Model taken from form
     * @return Form to fill and create InvoiceItem
     */
    @GetMapping("/invoiceItem")
    public String getInvoiceItemForm( Model aModel ) {
        InvoiceItemDto invoiceItemDto = new InvoiceItemDto();

        aModel.addAttribute( INVOICE_ITEM_ATTRIBUTE, invoiceItemDto );

        return INVOICE_ITEM_FORM;
    }

    /**
     * @return list of All InvoiceItems
     */
    @GetMapping(value = "/invoiceItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllInvoiceItems( Model aModel ) throws StripeException {

        aModel.addAttribute( INVOICE_ITEMS_ATTRIBUTE, invoiceItemService.listAll() );

        return INVOICE_ITEMS_LIST;
    }

    @ExceptionHandler(StripeException.class)
    public String handleError( Model aModel, StripeException aStripeException ) {
        aModel.addAttribute( "error", aStripeException.getMessage() );

        return INVOICE_ITEM_POST_RESULT;
    }
}
