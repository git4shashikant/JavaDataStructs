package com.threads;

public class StackOverFlow {

    public static void main (String [] args){
        Person robert = new Person("Robert");
        Person martin = new Person("Luis");

        robert.setFriend(martin);
        martin.setFriend(robert);

        Thread robertThoughts = new Thread(robert);
        Thread martinThoughts = new Thread(martin);

        meetingAfterDisputes(robertThoughts, martinThoughts);
    }

    private static void meetingAfterDisputes(Thread robertThoughts, Thread martinThoughts) {
        robertThoughts.start();
        martinThoughts.start();
    }

    static class Person implements Runnable{

        protected String name;
        protected Person friend;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setFriend(Person friend) {
            this.friend = friend;
        }

        private boolean shouldOfferGreeting(){
            synchronized(friend) {
                if (true == friend.shouldOfferGreeting()){
                    return true;
                } else {
                    return false;
                }
            }
        }

        private void sayHi(){
            System.out.println(String.format("%s says: Hi %s.", this.getName(), friend.getName()));
        }

        public void run() {
            System.out.println(String.format("%s thinks should I offer greeting to %s.", getName(), friend.getName()));
            boolean shouldOfferGreeting = shouldOfferGreeting();
            if (shouldOfferGreeting) {
                sayHi();
                System.out.println("Greetings Exchanged");
            }
        }
    }
}
