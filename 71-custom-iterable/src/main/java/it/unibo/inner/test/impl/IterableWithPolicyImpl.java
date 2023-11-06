package it.unibo.inner.test.impl;

import it.unibo.inner.api.IterableWithPolicy;
import java.util.function.Predicate;
import java.util.Iterator;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
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
            return (this.current < IterableWithPolicyImpl.this.array.length) 
                && (IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.array[this.current + 1]));
        }

        @Override
        public T next() {
            return IterableWithPolicyImpl.this.filter.test(IterableWithPolicyImpl.this.array[this.current + 1])
                ? IterableWithPolicyImpl.this.array[this.current++] : null;
        }
        
    }
    
     public Iterator<T> iterator() {
        return this.new Iter();
    }

    public T[] getArray() {
        return this.array;
    }

    public Predicate<T> getFilter() {
        return this.filter;
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
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
}