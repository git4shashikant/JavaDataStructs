package com.designpatterns.structural;

/*
* This allows to use a bridge interface to do some operations specific to the implementation class keeping
* the purpose of classes different.
*
* Here in this example, we have vehicle abstract class which has workshop interface as its attribute.
* Using workshop, we can separate the implementation of workshop specific to the vehicle and using
* abstract class, we implement the vehicles concrete classes, having workshop to which they belong.
 * */
public class BridgePattern {

    public static void main(String[] args) {
        Bike bike = new Bike(new BikeWorkshop());
        Car car = new Car(new CarWorkshop());

        bike.getWorkshop().manufacture();
        bike.getWorkshop().assemble();
        bike.drive();

        car.getWorkshop().manufacture();
        car.getWorkshop().assemble();
        car.drive();
    }

    interface Workshop {
        void manufacture();
        void assemble();
    }

    static class BikeWorkshop implements Workshop {
        @Override
        public void manufacture() {
            System.out.println("Manufacturing Bike.");
        }

        @Override
        public void assemble() {
            System.out.println("Assembling Bike.");
        }
    }

    static class CarWorkshop implements Workshop {
        @Override
        public void manufacture() {
            System.out.println("Manufacturing Car.");
        }

        @Override
        public void assemble() {
            System.out.println("Assembling Car.");
        }
    }

    static abstract class Vehicle {
        private Workshop workshop;

        Vehicle(Workshop workshop) {
            this.workshop = workshop;
        }

        public Workshop getWorkshop() {
            return this.workshop;
        }

        public abstract void drive();
    }

    static class Bike extends Vehicle {

        public Bike(Workshop workshop) {
            super(workshop);
        }

        @Override
        public void drive() {
            System.out.println("Driving Bike");
        }


    }

    static class Car extends Vehicle {

        public Car(Workshop workshop) {
            super(workshop);
        }

        @Override
        public void drive() {
            System.out.println("Driving Car.");
        }
    }
}
