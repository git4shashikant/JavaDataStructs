package com.practice;
import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();
        int index = 0;
        while(index < 10) {
            list.add(index);
            index++;
        }

        System.out.println(getMiddleElement(list));
    }

    static int getMiddleElement(LinkedList<Integer> list) {
        int i = list.indexOf(list.getLast());

        int index = 0;
        Integer found = 0;
        for (Integer integer : list) {
            if (index == i/2) {
                found = integer;
                break;
            }

            index++;
        }

        return found;
    }
}
