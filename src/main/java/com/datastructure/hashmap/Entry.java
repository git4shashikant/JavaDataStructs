package com.datastructure.hashmap;

public class Entry<k, v> {

    private k key;
    private v value;

    public Entry(k key, v value) {
        this.key = key;
        this.value = value;
    }

    public k getKey() {
        return key;
    }
    public v getValue() {
        return value;
    }
}
