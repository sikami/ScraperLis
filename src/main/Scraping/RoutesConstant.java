public enum RoutesConstant {
    //NP = nusa penida, NL = nusa lembongan
    NC("Nusa Ceningan"), NP("Nusa Penida"), GM("Gili Meno"), NL("Nusa Lembongan"), BL("Bali"),
    LM("Lombok"), GA("Gili Air"), GT("Gili Trawangan"), GILIT("GT"), PB("Padang Bai"), NP_GiliTransfer("NP"), NL_GiliTransfer("NL"),
    BALI_GiliTransfer("BALI_ALL_PORT");

    private String routes;

    RoutesConstant(String routes) {
        this.routes = routes;
    }

    public String getRoutes() {
        return routes;
    }
}
