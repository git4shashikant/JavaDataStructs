package com.practice;

import java.util.ArrayList;
import java.util.List;

/*
* To calculate maximum peaks in an array and divide it in maximum possible equal segment containing at least one peak.
* */
public class TotalPeaks {

    public static void main(String[] args) {
        getTotalPeaks(new int[] {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2});
        getSegments(new int[] {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2});
    }

    static List<Integer> getTotalPeaks(int[] arr) {
        int index = 0;
        List<Integer> peaks = new ArrayList<>();
        while (index < arr.length) {
            switch(index) {
                case 0:
                    if (arr[index] > arr[index+1]) {
                        System.out.println("Peak at index: " + index);
                        peaks.add(index);
                    }

                    break;
                case 1:
                    if(arr[arr.length -1] > arr[arr.length -2]) {
                        System.out.println("Peak at index: " + index);
                        peaks.add(index);
                    }

                    break;
                default:
                    if (arr[index] > arr[index-1] && arr[index] > arr[index +1]) {
                        System.out.println("Peak at index: " + index);
                        peaks.add(index);
                    }
            }

            index++;
        }

        return peaks;
    }

    /*
     * Divide an array into the maximum number of same-sized blocks, each of which should contain an index P such that A[P - 1] < A[P] > A[P + 1].
     */
    static int getSegments(int [] arr) {
        List<Integer> peaks = getTotalPeaks(arr);
        int segments = peaks.size();
        while(segments != 1) {
            if (arr.length%segments == 0) {
                int segmentLength = arr.length%segments;
                int index = 0;
                while(index < peaks.size()) {
                    if (segmentLength < peaks.get(index)*(index+1)) {
                        break;
                    }

                    index++;
                }

                break;
            }

            segments--;
        }

        System.out.println("Number of maximum possible segments containing peaks: " + segments);
        return segments;
    }
}
