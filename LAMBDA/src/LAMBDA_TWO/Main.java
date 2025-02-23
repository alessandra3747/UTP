/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad2;


import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

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
    List<String> result = dest.stream()
            .filter(e -> e.startsWith("WAW"))
            .map(e -> {
              String[] parts = e.split(" ");
              double fullPrice = Double.parseDouble(parts[2]) * ratePLNvsEUR;
              return "to " + parts[1] + " - price in PLN:\t" + (int)fullPrice;
            })
            .collect(Collectors.toList());


    for (String r : result) System.out.println(r);
  }
}
