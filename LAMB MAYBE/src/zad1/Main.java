/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
  public static void main(String[] args) {

    Function <String, List<String>> flines = fname -> {

        List<String> lines;

        try( Stream<String> fileStream = Files.lines(Paths.get(fname))){
          lines = fileStream.collect(Collectors.toList());
          return lines;
        }
        catch (IOException e) {
          e.printStackTrace();
          return null;
        }
    };


    Function <List<String>, String> join = lines -> {
        return lines.stream().collect(Collectors.joining(" "));
    };


    Function <String, List<Integer>> collectInts = text -> {

        List<Integer> numberList = new ArrayList<>();

        for(String part : text.split("\\D+"))
            if (!part.isEmpty())
                numberList.add(Integer.parseInt(part));

        return numberList;
    };


    Function <List<Integer>, Integer> sum = numberList -> {
        return numberList.stream()
                .mapToInt(Integer::intValue)
                .sum();
    };


    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);  
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
