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

    public FerrySchedule() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String toString() {
        return "Name of Ferry: " + name + "\nPrice: " + price + "\nTime: " + time + "\nRoute: " + route + "\n\n";
    }
}
