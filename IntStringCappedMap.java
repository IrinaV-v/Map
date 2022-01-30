package com.epam.autotasks.collections;

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

            AbstractSet<Integer> abs_set = new TreeSet<>();
            Iterator value = abs_set.iterator();

            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                return new Iterator<>() {
                    @Override
                    public boolean hasNext() {
                        //implement this method
                        return value.hasNext();
                    }
                    @Override
                    public Entry<Integer, String> next() {
                        //implement this method
                        return (Entry<Integer, String>) value.next();
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
        if (value.length() > capacity){ throw new IllegalArgumentException(); }
        else if (value.length() > currentCapacity)
        {
            while (currentCapacity < value.length())
            {
                for (Map.Entry me : map.entrySet())
                {
                    currentCapacity += (me.getValue().toString()).length();
                    map.remove(me.getKey());
                }
            }
        }
        if (map.containsKey(key))
        {
            map.remove(key);
        }
        currentCapacity -= value.length();
        return map.put(key, value);
    }

    @Override
    public String remove(final Object key) {
        //implement this method
        return map.remove(key);
    }

    @Override
    public int size() {
        //implement this method
        return map.size();
    }

    public static void main(String[] args) {
        IntStringCappedMap map = new IntStringCappedMap(25);
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(12, "Twelve");
        map.put(9, "Nine");
        map.put(1, "One");

        System.out.println(new TreeMap<>(map));
    }

}
