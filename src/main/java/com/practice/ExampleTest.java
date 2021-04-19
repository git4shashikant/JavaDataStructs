package com.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleTest {

    private static final String CLOTHING = "Clothing";
    private static final String SPORTS = "Sports";
    private static final String ACCESSORIES = "Accessories";
    private static final String ELECTRONICS = "Electronics";
    private static final Map<String, List<String>> categoryMap = new HashMap<>();

    static void prepareCategoryMap () {
        categoryMap.put("shirt", Arrays.asList(CLOTHING));
        categoryMap.put("sweater", Arrays.asList(CLOTHING));
        categoryMap.put("jacket", Arrays.asList(CLOTHING));
        categoryMap.put("shorts", Arrays.asList(CLOTHING, SPORTS));
        categoryMap.put("shoes", Arrays.asList(CLOTHING, SPORTS));

        categoryMap.put("socks", Arrays.asList(SPORTS));
        categoryMap.put("football", Arrays.asList(SPORTS));
        categoryMap.put("helmet", Arrays.asList(SPORTS));
        categoryMap.put("bag", Arrays.asList(SPORTS, ACCESSORIES));

        categoryMap.put("belt", Arrays.asList(ACCESSORIES));
        categoryMap.put("hat", Arrays.asList(ACCESSORIES));
        categoryMap.put("sunglasses", Arrays.asList(ACCESSORIES));
        categoryMap.put("watch", Arrays.asList(ACCESSORIES));

        categoryMap.put("tv", Arrays.asList(ELECTRONICS));
        categoryMap.put("camera", Arrays.asList(ELECTRONICS));
        categoryMap.put("headphones", Arrays.asList(ELECTRONICS));
        categoryMap.put("laptop", Arrays.asList(ELECTRONICS));
    }

    public static void main(String [] args) {

        Map<String, List<String>> usersPurchases = new HashMap<>();
        usersPurchases.put("Michael", Arrays.asList("Football", "Shirt", "Shoes", "headphones"));
        usersPurchases.put("Sara", Arrays.asList("TV", "football"));
        usersPurchases.put("Daniel", Arrays.asList("shirt", "sweater", "", "belt", "bag"));

        System.out.println("popularShoppingCategories result: " + popularShoppingCategories(usersPurchases));

        System.out.println("verify result: " + verify(new Float[]{0.3f, 0.6f, .09f}, new Integer[] {3200, 200, 4000}));
    }

    /*
    * INPUT:
    * Map<String, List<String>> usersPurchases: user as key and list of items purchased as values
    *
    * PROBLEM STATEMENT:
    * Return list of most popular Category of products purchased
    * item == "" then ignore
    *
    * OUTPUT:
    * Case-1: Return most popular category
    * Case-2: item == null then throw IllegalArgumentException
    * Case-3: item not found in categories then throw IllegalArgumentException
    * Case-4: 2 categories have same number of items then return list in alphabetical order
    * */
    static List<String> popularShoppingCategories(Map<String, List<String>> usersPurchases) {
        if (usersPurchases == null) {
            throw new IllegalArgumentException();
        }

        prepareCategoryMap();
        Map<String, Integer> popularCategoryMap = new HashMap<>();
        for (List<String> userPurchases : usersPurchases.values()) {
            for (String purchase : userPurchases) {
                if (purchase == "") continue;

                if (categoryMap.containsKey(purchase.toLowerCase())) {
                    List<String> itemCategories = categoryMap.get(purchase.toLowerCase());
                    for (String category : itemCategories) {
                        popularCategoryMap.put(category, popularCategoryMap.getOrDefault(category, 0) + 1);
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
        }

        int maxItems = 0;
        Set<String> popularCategory = new HashSet<>();
        for (String category : popularCategoryMap.keySet()) {
            if (popularCategoryMap.get(category).intValue() > maxItems) {
                maxItems = popularCategoryMap.get(category).intValue();
                popularCategory.clear();
                popularCategory.add(category);
            } else if (popularCategoryMap.get(category).intValue() == maxItems) {
                popularCategory.add(category);
            }
        }

        return new ArrayList<>(popularCategory);
    }

    /*
    * INPUT:
    * cpuUsage: {0.3, 0.6, .09} % use of CPU
    * useMemory: {3200, 2000, 4000, 7000} in MB, max memory available is 32 MB

    * PROBLEM STATEMENT:
    * User needs to send status for production ready code, based on RULES:
    * RULE#1 breached: CPU usage more than 90%
    * RULE#2 Breached: if max use of CPU and memory occurs at the same time when entries more than 1
    * RULE#3 breached: memory usage more than 60%
    * RULE-4 breached: is memory usage increases in last 3 consecutive entries
    * RULE#5 breached: if non readable data points are more than 30% hence RULE#1 and RULE#2 are also rejected
    * RULE#6 breached: if non readable data points are more than 25% hence RULE#3 and RULE#4 are also rejected

    * OUTPUT:
    * if no rule breached return GREEN
    * if one rule breached return YELLOW
    * if more than one rule breached return RED
    */

    public static String verify(Float[] cpuUsage, Integer[] usedMemory) {

        int totalBreaches = 0;
        int cpuMaxIndex = 0;
        int memoryMaxIndex = 0;
        int nonReadableDataPointsCPU = 0;
        int nonReadableDataPointsMemory = 0;

        for (int index = 0; index < cpuUsage.length; index++) {
            if (cpuUsage[index] == null) {
                nonReadableDataPointsCPU++;
                continue;
            }

            if (cpuUsage[cpuMaxIndex] != null && cpuUsage[index] > cpuUsage[cpuMaxIndex]) {
                cpuMaxIndex = index;
            }

            //RULE#1 breached: CPU usage more than 90%
            if (cpuUsage[index] >= 0.9f) {
                totalBreaches++;
            }
        }

        //RULE#5: RULE#1 and RULE#2 are rejected as non readable data points are more than 30%
        if ((float) nonReadableDataPointsCPU/cpuUsage.length > 0.3f) {
            return "RED";
        }

        for (int index = 0; index < usedMemory.length; index++) {
            if (usedMemory[index] == null) {
                nonReadableDataPointsMemory++;
                continue;
            }

            if (usedMemory[index] >= 32000*0.6f) {
                //RULE#3 breached: memory usage more than 60%
                totalBreaches++;
                if (totalBreaches > 1) {
                    return "RED";
                }
            }

            //RULE-4 breached last 3 consecutive increase in memory
            if (index >=2 && usedMemory[index-1] != null && usedMemory[index-1] != null
                    && usedMemory[index-1] < usedMemory[index] && usedMemory[index-2] < usedMemory[index-1] ) {
                totalBreaches++;
                if (totalBreaches > 1) {
                    return "RED";
                }
            }

            if (usedMemory[memoryMaxIndex] != null && usedMemory[index] > usedMemory[memoryMaxIndex]) {
                memoryMaxIndex = index;
            }
        }

        //RULE#6: RULE#3 and RULE#4 are rejected as non readable data points are more than 30%
        if (nonReadableDataPointsMemory/usedMemory.length > 0.25f) {
            return "RED";
        }

        //Rule #7 Breached: If there's no readable data points in both arrays, Rules #1, #2, #3 and #4 are rejected
        if (nonReadableDataPointsMemory == usedMemory.length && nonReadableDataPointsCPU == cpuUsage.length) {
            return "RED";
        }

        //RULE#2 Breached
        if (cpuUsage.length > 1 && usedMemory.length > 1 && cpuMaxIndex == memoryMaxIndex) {
            totalBreaches++;
        }

        return totalBreaches > 1 ? "RED" : (totalBreaches == 1 ? "YELLOW" : "GREEN");
    }
}