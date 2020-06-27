package com.practice;

/*
* This example contains array comparison:
* 1. Unique records from 2 arrays
* 2. Common records from 2 arrays
* 3. opposite words from 2 arrays
* */
public class ComparingArrays {

    public static void main(String []args){

        String[] s1 = new String[] {"loop", "hello", "time", "smart", "world", "smart"};
        String[] s2 = new String[] {"pool", "emit", "aloha", "emit", "trams"};

        removeReverse(s1, s2);

        int i = 0;
        int j = 0;
        while (i < s1.length) {
            System.out.println(s1[i]);
            i++;
        }

        while (j < s2.length) {
            System.out.println(s2[j]);
            j++;
        }
    }

    static void removeReverse(String[] array1, String[] array2) {
        int i = 0;
        int j = 0;
        while (i < array1.length) {
            String s = array1[i];
            if (s != null) {
                String reverseS = reverse(s);
                while (j < array2.length) {
                    String s2 = array2[j];
                    if (s2!= null && s2.equals(reverseS)) {
                        array2[j] = null;
                        array1[i] = null;
                    }
                    j++;
                }

                j = 0;

                int k = i+1;
                while (k < array1.length) {
                    String s3 = array1[k];
                    if (s.equals(s3)) {
                        array1[k] = null;
                    }
                    k++;
                }
            }
            i++;
        }
    }

    static String reverse(String string) {
        char[] chars = string.toCharArray();
        int i = 0;
        int j = string.length() -1 ;
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;

            i++;
            j--;
        }

        return String.valueOf(chars);
    }
}
