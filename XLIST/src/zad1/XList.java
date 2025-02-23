package zad1;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class XList <T> extends ArrayList<T> {


    public XList(T ... args) {

        super();

        for (T arg : args)
            this.add(arg);

    }

    public XList(Collection<T> col) {
        super(col);
    }



    public static <T> XList of(T ... args) {
        return new XList(args);
    }

    public static <T> XList of(Collection<T> col) {
        return new XList(col);
    }



    public static XList charsOf(String s) {

        XList<Character> result = new XList<>();

        for (char c : s.toCharArray())
            result.add(c);

        return result;
    }


    public static XList tokensOf(String s, String sep) {
        return new XList(s.split(sep));
    }

    public static XList tokensOf(String s) {
        return tokensOf(s,"\\s+");
    }



    public XList union(Collection<T> col) {
        return new XList(
                Stream.concat(this.stream(), col.stream())
                        .collect(Collectors.toList())
        );
    }
    public XList union(T[] arr) {
        return union(Arrays.asList(arr));
    }


    public XList diff(Collection<T> col) {
        return new XList(
                this.stream()
                        .filter(e -> !col.contains(e))
                        .collect(Collectors.toList())
        );
    }

    public XList diff(T[] arr) {
        return diff(Arrays.asList(arr));
    }


    public XList unique() {

        return new XList(
                this.stream()
                        .distinct()
                        .collect(Collectors.toList())
        );

    }

    public XList<T> combine() {

        XList<T> result = new XList<>();

        XList<List<T>> inputLists = new XList<>();

        for(T listFromXList : this) {
            List<T> subList = (List<T>) listFromXList;
            inputLists.add(subList);
        }

        for (int k = 0; k < inputLists.get(2).size(); k++) {
            for (int j = 0; j < inputLists.get(1).size(); j++) {
                for (int i = 0; i < inputLists.get(0).size(); i++) {
                    XList<T> combination = new XList<>();
                    combination.add(inputLists.get(0).get(i));
                    combination.add(inputLists.get(1).get(j));
                    combination.add(inputLists.get(2).get(k));
                    result.add((T) combination);
                }
            }
        }


        return result;
    }


    public <R> XList<R> collect(Function<T,R> function) {
        return new XList<>(this.stream()
                .map(function)
                .collect(Collectors.toCollection(XList::new))
        );
    }


    public String join (String sep) {
        return this.stream()
                .map(e -> e.toString())
                .collect(Collectors.joining(sep));
    }

    public String join () {
        return join("");
    }


    public void forEachWithIndex(BiConsumer<T, Integer> action) {
        for (int i = 0; i < this.size(); i++) {
            action.accept(this.get(i), i);
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(this.toArray());
    }

}
