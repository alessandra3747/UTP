/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad2;


public class Purchase {

    private String id;
    private String name;
    private String commodityName;
    private double price;
    private double quantity;

    public Purchase(String id, String name, String commodityName, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.commodityName = commodityName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public double getOverallCost() {
        return this.quantity * this.price;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return id + ";" + name + ";" + commodityName + ";" + price + ";" + quantity;
    }
}
