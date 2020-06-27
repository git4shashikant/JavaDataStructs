package com.practice;

import java.util.Arrays;

/*
* The best approach is to first combine both the calendars with meeting times.
* And then we can check in that calendar if we have any slot with in the bound.
* */
public class FindingMeetingSlot {

    public static void main(String[] args) {

    }

    public String[][] availableSlots(String[][] calendar1, String[][] calendar2, String [] bound1, String[] bound2, int slot) {
        String [] effectiveBound = findBound(bound1, bound2);
        String[][] availableSlots1 = availableSlots(calendar1, effectiveBound, slot);
        String[][] availableSlots2 = availableSlots(calendar2, effectiveBound, slot);
        String[][] availableSlots = matchAvailability(availableSlots1, availableSlots2, slot);
        return availableSlots;
    }

    public String[][] matchAvailability(String[][] s1, String[][] s2, int slot) {
        String[][] matchedAvailabilities = new String[48][2];
        for (int i=0; i<s1.length; i++) {
            String startTime1 = s1[0][0];
            String endTime1 = s1[0][1];
            for (int j = 0; j < s2.length; j++) {
                String startTime2 = s2[0][0];
                String endTime2 = s2[0][1];
                String effectiveStartTime;
                String effectiveEndTime;
                if (timeGap(startTime1, startTime2) > 0) {
                    effectiveStartTime = startTime2;
                } else {
                    effectiveStartTime = startTime1;
                }
                if (timeGap(endTime1, endTime2) > 0) {
                    effectiveEndTime= endTime1;
                } else {
                    effectiveEndTime= endTime2;
                }

                String tempEndTime = effectiveStartTime + slot;
                int index = 0;
                while(timeGap(tempEndTime, effectiveEndTime) >= 0) {
                    matchedAvailabilities[index][0] = effectiveStartTime;
                    matchedAvailabilities[index][1] = tempEndTime;
                    effectiveStartTime = tempEndTime;
                    tempEndTime += slot;
                    index++;
                }
            }
        }

        return matchedAvailabilities;
    }


    public String[][] availableSlots(String[][] calendar, String[] bound, int slot) {
        String[][] availableSlots = new String[48][2];
        String availableStartTime = null;
        int index = 0;
        for (int i =0; i < calendar.length; i++) {
            String startTime = calendar[i][0]; String endTime = calendar[i][1];
            if (availableStartTime == null && timeGap(bound[0], endTime) >= 0 && timeGap(endTime, bound[1]) >= slot) {
                availableStartTime = endTime;
                continue;
            }

            if (timeGap(startTime, bound[1]) >=0) {
                if (timeGap(availableStartTime , startTime) >= slot) {
                    availableSlots[index][0] = availableStartTime ;
                    availableSlots[index][1] = startTime;
                    index++;
                    availableStartTime = null;
                } else {
                    availableStartTime = endTime;
                }
            }
        }

        return availableSlots;
    }

    // assuming time 2 is later than time 1
    public int timeGap(String time1, String time2) {
        String[] time1Arr = time1.split(":"); String[] time2Arr = time2.split(":");
        int time1Mins = (Integer.parseInt(time1Arr[0])*60)+Integer.parseInt(time1Arr[1]);
        int time2Mins = (Integer.parseInt(time2Arr[0])*60)+Integer.parseInt(time2Arr[1]);
        return (time2Mins - time1Mins);
    }

    String [] findBound(String[] bound1, String[] bound2) {
        String[] effectiveBound = new String[2];
        if (timeGap(bound1[0], bound2[0]) > 0) {
            effectiveBound[0] = bound2[0];
        } else {
            effectiveBound[0] = bound1[0];
        }

        if (timeGap(bound1[1] , bound2[1] ) > 0) {
            effectiveBound[1] = bound1[1];
        } else {
            effectiveBound[1] = bound2[1];
        }

        return effectiveBound;
    }

    /*public String[][] availableSlots2(String[][] calendar1, String[][] calendar2, String [] bound1, String[] bound2, int slot) {

        // we need to all (endTime from meeting - startTime from next meeting) slots as available from combined data
        return combineCalendars();
    }*/

    /*public String[][] combineCalendars(String[][] calendar1, String[][] calendar2, String[] bound) {
        String[][] meetings = new String[48][2];
        meetings[0][0] = "00:00";
        meetings[0][1] = bound[0];

        int index = 0;
        while(index < calendar1.length) {
            String mst = calendar1[index][0];
            String met = calendar1[index][1];
            if (timeGap(bound[0], mst) >= 0) {
                meetings[index][0] = mst;
                meetings[index][0] = met;
            } else if (timeGap(bound[0], met) > 0) {
                meetings[index][0] = bound[0];
                meetings[index][0] = met;
            }

            index++;
        }

        calendar1[index][0] = bound[1];
        calendar1[index][1] = "23:59";

        index = 0;
        while(index < calendar2.length) {
            String mst = calendar2[index][0];
            String met = calendar2[index][1];
            //as this segment is gonna superimpose, we will find all the segments lying between mst, met
            for (int i = 0; i < meetings.length; i++) {
                String st = meetings[i][0];
                String et = meetings[i][1];

                //segment where mst lies
                if (timeGap(st, mst) > 0 && timeGap(et, mst) < 0) {

                }


            }

            index++;
        }

        return meetings;
    }*/

}
