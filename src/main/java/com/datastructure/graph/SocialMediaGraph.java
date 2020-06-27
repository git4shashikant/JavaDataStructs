package com.datastructure.graph;

import java.util.*;

/*
* This is a non directional, non weighted graph where each person is connected to one or more persons.
* */
public class SocialMediaGraph {

    public static void main(String[] args) {
        Graph socialMediaGraph = new Graph();

        Person jacob = new Person("Jacob", "jacob@gmail.com");
        Person johny = new Person("Johny", "johny@gmail.com");
        Person amanda = new Person("Amanda", "amanda@gmail.com");
        Person peter = new Person("Peter", "peter@gmail.com");
        Person jack = new Person("Jack", "jack@gmail.com");

        socialMediaGraph.addUser(jacob);
        socialMediaGraph.addUser(johny);
        socialMediaGraph.addUser(amanda);
        socialMediaGraph.addUser(peter);
        socialMediaGraph.addUser(jack);

        socialMediaGraph.addConnection(new Connection(jack, jacob));
        socialMediaGraph.addConnection(new Connection(jack, johny));

        socialMediaGraph.addConnection(new Connection(peter, amanda));

        socialMediaGraph.addConnection(new Connection(johny, peter));

        for (Person user : socialMediaGraph.getUsers()) {
            System.out.print(user.getName() + ": ");
            socialMediaGraph.getConnections(user).stream()
                    .forEach(connection -> {
                        if (connection.getPerson1().equals(user.getEmail())){
                            System.out.print(connection.getPerson2().getName());
                        } else {
                            System.out.print(connection.getPerson1().getName());
                        }
                    });
            System.out.println("");
        }
    }


    static class Graph {
        private Map<Person, List<Connection>> graph = new HashMap();

        public void addUser(Person person) {
            getConnections(person);
        }

        public void addConnection(Connection connection) {
            getConnections(connection.getPerson1()).add(connection);
            getConnections(connection.getPerson2()).add(connection);
        }

        private List<Connection> getConnections(Person person) {
            List<Connection> connections = graph.get(person);
            if (connections == null) {
                graph.put(person, new ArrayList());
            }

            return graph.get(person);
        }

        public Set<Person> getUsers() {
            return graph.keySet();
        }
    }

    static class Person {
        private String name;
        private String email;

        public Person(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public int hashCode() {
            return name.length();
        }

        @Override
        public boolean equals(Object obj) {
            boolean isEquals = false;
            if (obj instanceof Person) {
                Person person = (Person)obj;
                if (person.getEmail().equalsIgnoreCase(this.email)) {
                    isEquals = true;
                }
            }

            return isEquals;
        }
    }

    static class Connection {
        private Person person1;
        private Person person2;

        public Connection(Person person1, Person person2) {
            this.person1 = person1;
            this.person2 = person2;
        }

        public Person getPerson1() {
            return person1;
        }

        public Person getPerson2() {
            return person2;
        }

        @Override
        public boolean equals(Object obj) {
            boolean isEquals = false;
            if (obj instanceof Connection) {
                Connection connection = (Connection)obj;
                if ((connection.getPerson1().equals(this.person1) && connection.getPerson2().equals(this.person2))
                        || (connection.getPerson2().equals(this.person1) && connection.getPerson1().equals(this.person2))) {
                    isEquals = true;
                }
            }

            return isEquals;
        }
    }
}
