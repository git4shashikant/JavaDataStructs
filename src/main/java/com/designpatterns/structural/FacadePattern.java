package com.designpatterns.structural;

/*
* Main purpose of facade is to create a wrapper API which abstract all the complex apis and provide a
* cleaner comprehensive solution.
*
* */
public class FacadePattern {

    public static void main(String [] args) {
        OuterInterface outerFacade = new OuterFacade(new InnerClass());
        outerFacade.facadeMethod();
    }

    interface OuterInterface {
        void facadeMethod();
    }

    static class OuterFacade implements OuterInterface {
        private InnerInterface innerInterface;

        public OuterFacade(InnerInterface innerInterface) {
            this.innerInterface = innerInterface;
        }

        @Override
        public void facadeMethod() {
            innerInterface.complexMethod1();
            innerInterface.complexMethod2();
        }
    }

    interface InnerInterface {
        void complexMethod1();
        void complexMethod2();
    }

    static class InnerClass implements InnerInterface {
        @Override
        public void complexMethod1() {
            System.out.println("complexMethod1");
        }

        @Override
        public void complexMethod2() {
            System.out.println("complexMethod2");
        }
    }
}
