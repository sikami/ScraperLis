public class FerrySchedule {
    private String name;
    private String price;
    private String time;
    private String route;

    public FerrySchedule(String name, String price, String time, String route) {
        this.name = name;
        this.price = price;
        this.time = time;
        this.route = route;
    }

    public String toString() {
        return "Name of Ferry: " + name + "\nPrice: " + price + "\nTime: " + time + "\nRoute: " + route + "\n\n";
    }
}
