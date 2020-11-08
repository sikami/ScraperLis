
public class LombokFbMethods {

    public LombokFbMethods() {
    }

    public static String findValueLombokFb(String departure) {
        switch (departure) {
            case "Nusa Ceningan":
                return "8";
            case "Nusa Penida":
                return "7";
            case "Gili Meno":
                return "6";
            case "Nusa Lembongan":
                return "5";
            case "Lombok":
                return "4";
            case "Bali" :
              case "Padang Bai":
                return "3";
            case "Gili Air":
                return "2";
            case "Gili Trawangan":
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
        routesToFrom.setFrom("Bali");

       if (from.contains("Gili Air")) {
           routesToFrom.setTo("Gili Air");
       } else if (from.contains("Nusa Ceningan")) {
           routesToFrom.setTo("Nusa Ceningan");
       } else if (from.contains("Gili Meno")) {
           routesToFrom.setTo("Gili Meno");
       } else if (from.contains("Nusa Lembongan")) {
           routesToFrom.setTo("Nusa Lembongan");
       } else if (from.contains("Lombok")) {
           routesToFrom.setTo("Lombok");
       } else if (from.contains("Gili Trawangan")) {
           routesToFrom.setTo("Gili Trawangan");
       }

       return routesToFrom;
    }


}

