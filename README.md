I created a basic service to create and display customers, invoice items and invoices.

The app is created using spring, lombok and with Stripe integration.

Page Index:
localhost:8080/customer - form to create customer
localhost:8080/customers - list of all customers

localhost:8080/invoiceItem - form to create invoice item
localhost:8080/invoiceItems - list of all invoice items

localhost:8080/invoice - form to create invoice
localhost:8080/invoices - list of all invoices

Please remember that you need to always have a customer, next create some invoice items for invoice and at the end from created invoice items you can create invoice itself.

I also created basic .gitattributes, and .editorconfig files more on: https://editorconfig.org.

The project does not include unit tests because the functionality is simple, and it would be hard to create UNIT tests for it.
