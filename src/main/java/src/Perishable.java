package src;

public class Perishable extends Product {
    private String expirationDate;
    private String sellDate;

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Perishable(int id, String name, double price, int quantity, String location,
                      String expirationDate, String sellDate) {
        super(id, name, price, quantity, location);
        this.expirationDate = expirationDate;
        this.sellDate = sellDate;
    }
}

