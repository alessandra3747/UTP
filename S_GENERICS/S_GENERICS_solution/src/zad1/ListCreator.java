/**
 *
 *  @author Fus Aleksandra S30395
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;


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


    public ListCreator<T> when(Selector<T> sel){

        for (T element : this.list) {
            if (sel.select(element)) {
                filteredList.add(element);
            }
        }

        return this;
    }


    public <R> List<R> mapEvery (Mapper <T, R> map) {

        List<R> resultList = new ArrayList<>();

        for (T element : this.filteredList) {
            resultList.add(map.map(element));
        }

        return resultList;
    }

}  
