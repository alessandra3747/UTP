/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;


import java.util.*;

public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(  e -> e.startsWith("WAW")
                        )
                       .mapEvery( e -> {
                          String[] parts = e.split(" ");
                          double fullPrice = Double.parseDouble(parts[2]) * xrate;
                          return "to " + parts[1] + " - price in PLN:\t" + (int)fullPrice;
                        }
                        );
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR 
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
