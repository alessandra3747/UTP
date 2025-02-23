package zad1;

import java.util.function.Function;


public class InputConverter <T>{

    private T inputData;

    public InputConverter(T inputData) {
        this.inputData = inputData;
    }

    public <R> R convertBy(Function<?,?>... functions) {

        Object currentResult = inputData;

        for(Function<?,?> function : functions) {
           currentResult = ((Function<Object, Object>) function).apply(currentResult);
        }

        return (R) currentResult;
    }


}
