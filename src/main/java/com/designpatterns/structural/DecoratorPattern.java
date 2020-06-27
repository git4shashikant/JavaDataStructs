package com.designpatterns.structural;

/*
* In Decorator pattern, a wrapper class extends the functionality of the existing class.
* */
public class DecoratorPattern {

    public static void main (String [] args) {
        DecoratorClass decoratorClass = new DecoratorClass(new InnerClass());
        decoratorClass.decorate();
    }

    static class DecoratorClass {
        private InnerInterface innerInterface;

        public DecoratorClass(InnerInterface innerInterface) {
            this.innerInterface = innerInterface;
        }

        public void decorate() {
            decorate(new BulbDecoration());
            innerInterface.decorate();
        }

        private void decorate(Decoration decoration) {
            decoration.purpose();
        }
    }

    interface Decoration {
        void purpose();
    }

    static class BulbDecoration implements Decoration {
        public void purpose() {
            System.out.println("Colourful bulbs");
        }
    }

    interface  InnerInterface {
        void decorate();
    }

    static class InnerClass implements InnerInterface{

        @Override
        public void decorate() {
            System.out.println("Inner class decorator.");
        }
    }
}
