package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class ListCreator <T>{

    private List<T> list;

    private List<T> filteredList;


    private ListCreator(List<T> srcList){
        this.list = srcList;
        this.filteredList = new ArrayList<T>();
    }


    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<T>(list);
    }


    public ListCreator<T> when(Predicate<T> predicate){

        for (T element : this.list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }

        return this;
    }


    public <R> List<R> mapEvery (Function<T, R> function) {

        List<R> resultList = new ArrayList<>();

        for (T element : this.filteredList) {
            resultList.add(function.apply(element));
        }

        return resultList;
    }

}
