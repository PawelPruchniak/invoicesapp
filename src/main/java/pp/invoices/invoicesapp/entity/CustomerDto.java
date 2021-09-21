package pp.invoices.invoicesapp.entity;

public class CustomerDto {
    private String id;
    private String name;
    private String description;
    private String email;

    public String getId() {
        return id;
    }

    public void setId( String aId ) {
        id = aId;
    }

    public String getName() {
        return name;
    }

    public void setName( String aName ) {
        name = aName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String aDescription ) {
        description = aDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String aEmail ) {
        email = aEmail;
    }
}
