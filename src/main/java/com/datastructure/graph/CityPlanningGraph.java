package com.datastructure.graph;

import java.util.*;

public class CityPlanningGraph {

    public static void main(String[] args) {
        City agra = new City("Agra");
        City mathura = new City("Mathura");
        City aligarh = new City("Aligarh");

        Road agraMathuraRoad = new Road(agra, mathura, 44, false);
        Road agraAligarhRoad = new Road(agra, aligarh, 80, true);
        Road aligarhAgraRoad = new Road(aligarh, agra,88, true);
        Road aligarhMathuraRoad = new Road(aligarh, mathura,40, false);

        RoadNetwork roadNetwork = new RoadNetwork();
        roadNetwork.addCity(agra);
        roadNetwork.addCity(aligarh);
        roadNetwork.addCity(mathura);

        roadNetwork.addRoad(agraMathuraRoad);
        roadNetwork.addRoad(agraAligarhRoad);
        roadNetwork.addRoad(aligarhAgraRoad);
        roadNetwork.addRoad(aligarhMathuraRoad);

        System.out.println("");
    }

    static class RoadNetwork {
        private Map<City, List<Road>> roadNetwork = new HashMap<>();

        public void addCity(City city) {
            getRoadNetwork(city);
        }

        public void addRoad(Road road) {
            if (!road.isOneWay()) {
                List<Road> roadNetwork = getRoadNetwork(road.getCityTo());
                if(!roadNetwork.contains(road)) {
                    roadNetwork.add(road);
                }
            }

            List<Road> roadNetwork = getRoadNetwork(road.getCityFrom());
            if(!roadNetwork.contains(road)) {
                roadNetwork.add(road);
            }
        }

        private List<Road> getRoadNetwork(City city) {
            if (roadNetwork.get(city) == null) {
                roadNetwork.put(city, new ArrayList<>());
            }

            return roadNetwork.get(city);
        }

        private Set<City> getCities() {
            return roadNetwork.keySet();
        }
    }

    static class Road {
        private City cityFrom;
        private City cityTo;
        private int length;
        private boolean oneWay;

        public Road(City cityFrom, City cityTo, int length, boolean oneWay) {
            this.cityFrom = cityFrom;
            this.cityTo = cityTo;
            this.length = length;
            this.oneWay = oneWay;
        }

        public City getCityFrom() {
            return cityFrom;
        }

        public City getCityTo() {
            return cityTo;
        }

        public boolean isOneWay() {
            return oneWay;
        }

        public int getLength() {
            return length;
        }
    }

    static class City {
        private String name;

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof City)) return false;
            City city = (City) o;
            return Objects.equals(getName(), city.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }
}
