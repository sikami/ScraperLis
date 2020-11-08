
public class LombokFbMethods {
    private static final String NC = "Nusa Ceningan";
    private static final String NP = "Nusa Penida";
    private static final String GM = "Gili Meno";
    private static final String NL = "Nusa Lembongan";
    private static final String BL = "Bali";
    private static final String LM = "Lombok";
    private static final String GA = "Gili Air";
    private static final String GT = "Gili Trawangan";

    public LombokFbMethods() {
    }

    public static String findValueLombokFb(String departure) {
        switch (departure) {
            case NC:
                return "8";
            case NP:
                return "7";
            case GM:
                return "6";
            case NL:
                return "5";
            case LM:
                return "4";
            case BL :
              case "Padang Bai":
                return "3";
            case GA:
                return "2";
            case GT:
                return "1";
        }
        return null;
    }

    public String findCurrencyLombokFb(String currency) {
        if (currency.contains("CHF")) {
            return "10";
        } else if (currency.contains("AUD")) {
            return "9";
        } else if (currency.contains("CAD")) {
            return "8";
        } else if (currency.contains("EUR")) {
            return "7";
        } else if (currency.contains("JPY")) {
            return "5";
        } else if (currency.contains("USD")) {
            return "4";
        } else if (currency.contains("GBP")) {
            return "3";
        } else if (currency.contains("SGD")) {
            return "2";
        } else if (currency.contains("IDR")) {
            return "1";
        }
        return null;
    }

    public static RoutesToFrom routeFrom(String from) {
        RoutesToFrom routesToFrom = new RoutesToFrom();
        routesToFrom.setFrom(BL);

       if (from.contains(GA)) {
           routesToFrom.setTo(GA);
       } else if (from.contains(NC)) {
           routesToFrom.setTo(NC);
       } else if (from.contains(GM)) {
           routesToFrom.setTo(GM);
       } else if (from.contains(NL)) {
           routesToFrom.setTo(NL);
       } else if (from.contains(LM)) {
           routesToFrom.setTo(LM);
       } else if (from.contains(GT)) {
           routesToFrom.setTo(GT);
       }

       return routesToFrom;
    }


}

