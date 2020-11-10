public enum RoutesConstant {
    //NP = nusa penida, NL = nusa lembongan
    NC("Nusa Ceningan"), NP("Nusa Penida"), GM("Gili Meno"), NL("Nusa Lembongan"), BL("Bali"),
    LM("Lombok"), GA("Gili Air"), GA_GiliTransfer("GA"), GM_GiliTransfer("GM"), GT("Gili Trawangan"), GT_GiliTransfer("GT"), PB("Padang Bai"), NP_GiliTransfer("NP"), NL_GiliTransfer("NL"),
    BALI_GiliTransfer("BALI_ALL_PORT");

    private String routes;

    RoutesConstant(String routes) {
        this.routes = routes;
    }

    public String getRoutes() {
        return routes;
    }
}
