public enum RoutesConstant {
    NC("Nusa Ceningan"), NP("Nusa Penida"), GM("Gili Meno"), NL("Nusa Lembongan"), BL("Bali"),
    LM("Lombok"), GA("Gili Air"), GT("Gili Trawangan"), PB("Padang Bai");

    private String routes;

    RoutesConstant(String routes) {
        this.routes = routes;
    }

    public String getRoutes() {
        return routes;
    }
}
