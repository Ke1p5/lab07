package it.unibo.inner.test.impl;

import it.unibo.inner.api.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private T[] array;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(T[] array) {
        this(array, new Predicate<T>() {
            public boolean test(T t) {
                return true;
            }
        });   
    }
    
    public IterableWithPolicyImpl(T[] array, Predicate<T> filter) {
        this.array = array;
        this.filter = filter;
    }

    private class Iter implements Iterator<T> {
        private int current;

        public Iter() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            T temp;
            for (; this.current < array.length; this.current++) {
                temp = array[this.current];
                if (filter.test(temp)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            if (this.hasNext()){
                return IterableWithPolicyImpl.this.array[this.current++];
            }
            throw new NoSuchElementException();
        }
    }
    
    public Iterator<T> iterator() {
        return new Iter();
    }

    public T[] getArray() {
        return this.array;
    }

    public Predicate<T> getFilter() {
        return this.filter;
    }

    public String toString() {
        String line = "[";
        for (int i = 0; i < this.array.length; i++) {
            if ( i + 1 == this.array.length) {
                line = line + this.array[i];
            }
            else {
                line = line + this.array[i] + ", ";
            }
        }
        line = line + "]";
        return line;    
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }
}
