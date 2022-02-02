package com.epam.autotasks.collections;
//this one
import java.util.*;
class IntStringCappedMap extends AbstractMap<Integer, String> {

    private LinkedHashMap<Integer, String> map = new LinkedHashMap(); //
    private final long capacity;
    public long currentCapacity;

    public IntStringCappedMap(final long capacity) {
        this.capacity = capacity;
        currentCapacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }

@Override
public Set<Entry<Integer, String>> entrySet() {
    return new AbstractSet<>() {
        @Override
        public Iterator<Entry<Integer, String>> iterator() {
            return new Iterator<>() {
                Iterator<Entry<Integer, String>> value = map.entrySet().iterator();
                @Override
                public boolean hasNext() {
                    return value.hasNext();
                }

                @Override
                public Entry<Integer, String> next() {
                    if (value.hasNext())
                    {
                        return value.next();
                    }
                    else
                    {
                        throw new NoSuchElementException();
                    }

                }
            };
        }

        @Override
        public int size() {
            return IntStringCappedMap.this.size();
        }
    };
}

    @Override
    public String get(final Object key) {
        //implement this method
        return map.get(key);
    }

    @Override
    public String put(final Integer key, final String value) {
        //implement this method
        String oldValue = map.remove(key);
        if(oldValue != null)
        {
            currentCapacity += oldValue.length();
        }
        if(capacity == 20)
        {
            System.out.println(key + " : " + value + " // " + currentCapacity);
        }

        if (value.length() > capacity) {
            throw new IllegalArgumentException();
        } else if (value.length() > currentCapacity)
        {
                Iterator<Map.Entry<Integer, String>> iterator = entrySet().iterator();
                Map.Entry<Integer, String> entry = null;
                while (currentCapacity < value.length() && iterator.hasNext()) {
                    entry = iterator.next();
                    remove(entry.getKey());
                }
        }

        currentCapacity -= value.length();
        String newValue = map.put(key, value);
        return oldValue == null ? newValue : oldValue;//map.put(key, value);
    }

    @Override
    public String remove(final Object key) {
        //implement this method
        if(map.containsKey(key))
        {
            currentCapacity += get(key).length();
        }
        return map.remove(key);
    }

    @Override
    public int size() {
        //implement this method
        return map.size();
    }
