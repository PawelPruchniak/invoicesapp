package pp.invoices.invoicesapp.enums;

public enum Currency {
    PLN( "pln" );

    private final String currency;

    Currency( final String aCurrency ) {
        this.currency = aCurrency;
    }


    @Override
    public String toString() {
        return currency;
    }
}
