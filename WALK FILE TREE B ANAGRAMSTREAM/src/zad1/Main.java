/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {

      try {

          new BufferedReader(new InputStreamReader(new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt").openStream()))
                  .lines()
                  .collect(Collectors.groupingBy(
                          word -> word.chars().sorted()
                                  .mapToObj(c -> String.valueOf((char) c))
                                  .collect(Collectors.joining())))
                  .values().stream()
                  .collect(Collectors.groupingBy(List::size))
                  .entrySet().stream()
                  .max(Map.Entry.comparingByKey())
                  .ifPresent(e -> e.getValue().stream()
                          .map(list -> list.stream().sorted().collect(Collectors.joining(" ")))
                          .sorted()
                          .forEach(System.out::println));

      } catch(IOException e) {
          e.printStackTrace();
      }


  }
}
