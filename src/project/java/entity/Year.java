package project.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class Year implements Serializable {
    private int year;

    public Year(int year) {
        this.year = year;
    }

    public Year() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Year{" +
                "year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year1 = (Year) o;
        return Objects.equals(year, year1.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(year);
    }
}
