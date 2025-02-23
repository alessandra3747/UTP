package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    private T value;

    private Maybe(T value){
        this.value = value;
    }



    public static <T> Maybe<T> of (T value) {
        return new Maybe<T>(value);
    }


    public void ifPresent (Consumer cons) {
        if(isPresent())
            cons.accept(value);

    }


    public <R> Maybe<R> map(Function<T,R> func) {
        if(isPresent()) {
            return Maybe.of(func.apply(value));
        }
        return Maybe.of(null);
    }


    public T get() {
        if(isPresent())
            return value;
        else throw new NoSuchElementException("maybe is empty");
    }

    public boolean isPresent() {
        return value != null;
    }


    public T orElse(T defVal) {
        if(isPresent())
            return value;
        else return defVal;
    }


    public Maybe<T> filter(Predicate<T> pred) {
        if(!isPresent())
            return this;

        return pred.test(value) ? this : Maybe.of(null);
    }


    @Override
    public String toString() {
        return isPresent() ? "Maybe has value " + value : "Maybe is empty";
    }
}
