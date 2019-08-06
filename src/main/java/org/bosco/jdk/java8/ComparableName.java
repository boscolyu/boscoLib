package org.bosco.jdk.java8;

public class ComparableName implements Comparable<ComparableName> {

    String comparableName = "";

    public ComparableName(String name) {
        this.comparableName = name;
    }

    public int compareTo(ComparableName other) {
        if (comparableName == null || other == null) {
            throw new NullPointerException("object to compare is null");
        }
        if (comparableName.length() > other.comparableName.length())
            return 1;
        else if (comparableName.length() == other.comparableName.length())
            return 0;
        else
            return -1;
    }

    public String toString() {
        return comparableName;
    }

}
