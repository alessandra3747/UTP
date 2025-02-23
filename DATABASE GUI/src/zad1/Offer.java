package zad1;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Offer {
    private String locale;
    private String country;
    private String dateFrom;
    private String dateTo;
    private String place;
    private String price;
    private String currency;

    public Offer(String inLocale, String inCountry, String inDateFrom, String inDateTo, String inPlace, String inPrice, String inCurrency) {
        this.locale = inLocale;
        this.country = inCountry;
        this.dateFrom = inDateFrom;
        this.dateTo = inDateTo;
        this.place = inPlace;
        this.price = inPrice;
        this.currency = inCurrency;
    }


    public String getDescription(String tmpLocale, String tmpDateFormat) {
        Locale userLocale = getLocale(tmpLocale);

        return String.format("%s %s %s %s %s %s",
                translateCountry(userLocale),
                translateDateFrom(userLocale, tmpDateFormat),
                translateDateTo(userLocale, tmpDateFormat),
                translatePlace(userLocale),
                translatePrice(userLocale),
                currency );

    }


    public String translatePlace(Locale userLocale) {
        return translatePlaceOrCountry(this.place, userLocale);
    }


    public String translateCountry(Locale userLocale) {
        return translatePlaceOrCountry(this.country, userLocale);
    }


    public String translatePrice(Locale userLocale) {

        NumberFormat inNumberFormat = NumberFormat.getInstance(getLocale(this.locale));
        NumberFormat outNumberFormat = NumberFormat.getInstance(userLocale);

        String formattedPrice = price;

        try {
            BigDecimal parsedPrice = new BigDecimal(inNumberFormat.parse(price).toString());
            formattedPrice = outNumberFormat.format(parsedPrice);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return formattedPrice;
    }


    public String translateDateFrom(Locale userLocale, String dateFormat) {
        return translateDate(dateFrom, userLocale, dateFormat);
    }


    public String translateDateTo(Locale userLocale, String dateFormat) {
        return translateDate(dateTo, userLocale, dateFormat);
    }


    public String translateCurrency() {
        return this.currency;
    }



    private Locale getLocale(String tmpLocale) {
        String[] partsOfLocale = tmpLocale.split("_");

        if (partsOfLocale.length == 2) {
            return new Locale(partsOfLocale[0], partsOfLocale[1]);

        } else if (partsOfLocale.length == 1) {
            return new Locale(partsOfLocale[0]);

        } else {
            throw new IllegalArgumentException("Invalid locale: " + tmpLocale);
        }
    }

    private String translatePlaceOrCountry (String val, Locale userLocale) {

        if (userLocale.getLanguage().equals(getLocale(this.locale).getLanguage())) {
            return val;
        }

        String result = val;


        ResourceBundle resourceBundle = ResourceBundle.getBundle("offer", getLocale(this.locale));

        for (String key : resourceBundle.keySet()) {
            if (resourceBundle.getString(key).equals(val)) {
                result = key;
            }
        }

        resourceBundle = ResourceBundle.getBundle("offer", userLocale);

        for (String key : resourceBundle.keySet()) {
            if (key.equals(result)) {
                result = resourceBundle.getString(key);
            }
        }

        return result;
    }

    private String translateDate (String date, Locale userLocale, String dateFormat) {
        DateFormat inDateFormat = new SimpleDateFormat("yyyy-MM-dd", getLocale(this.locale));
        DateFormat outDateFormat = new SimpleDateFormat(dateFormat, userLocale);

        String result = date;

        try {
            result = outDateFormat.format(inDateFormat.parse(dateFrom));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}


