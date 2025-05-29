package src;

public class Description extends Product{

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Description(int id, String name, double price, int quantity, String location, String description ) {
        super(id, name, price, quantity, location);
        this.description = description;
    }
}
