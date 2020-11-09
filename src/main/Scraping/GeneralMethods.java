import okhttp3.Route;

public class GeneralMethods {

    public GeneralMethods() {
    }

    public static String findValueLombokFb(String departure) {

        if (departure.equals(RoutesConstant.NC.getRoutes())) {
            return RoutesConstant.NC.getRoutes();
        } else if (departure.equals(RoutesConstant.NP.getRoutes())) {
            return RoutesConstant.NP.getRoutes();
        } else if (departure.equals(RoutesConstant.GM.getRoutes())) {
            return RoutesConstant.GM.getRoutes();
        } else if (departure.equals(RoutesConstant.NL.getRoutes())) {
            return RoutesConstant.NL.getRoutes();
        } else if (departure.equals(RoutesConstant.LM.getRoutes())) {
            return RoutesConstant.LM.getRoutes();
        } else if (departure.equals(RoutesConstant.BL.getRoutes())) {
            return RoutesConstant.BL.getRoutes();
        } else if (departure.equals(RoutesConstant.GA.getRoutes())) {
            return RoutesConstant.GA.getRoutes();
        } else if (departure.equals(RoutesConstant.GT.getRoutes())) {
            return RoutesConstant.GT.getRoutes();
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

    public static String routesTo(String)
//    public static RoutesToFrom routeFrom(String from) {
//        RoutesToFrom routesToFrom = new RoutesToFrom();
//        routesToFrom.setFrom(BL);
//
//       if (from.contains(GA)) {
//           routesToFrom.setTo(GA);
//       } else if (from.contains(NC)) {
//           routesToFrom.setTo(NC);
//       } else if (from.contains(GM)) {
//           routesToFrom.setTo(GM);
//       } else if (from.contains(NL)) {
//           routesToFrom.setTo(NL);
//       } else if (from.contains(LM)) {
//           routesToFrom.setTo(LM);
//       } else if (from.contains(GT)) {
//           routesToFrom.setTo(GT);
//       }
//
//       return routesToFrom;
//    }


}

