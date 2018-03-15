package project.java.dao.dao;

import project.java.entity.Year;

import java.util.ArrayList;
import java.util.List;

public class YearDAO {
    private static List<Year> years = new ArrayList<>();

    public List<Year> getAll() {
        if (this.years.size() > 0) {
            return this.years;
        } else {
            synchronized (this) {
                if (this.years.size() > 0) {
                    return this.years;
                } else {
                    years.add(new Year(2014));
                    years.add(new Year(2015));
                    years.add(new Year(2016));
                    years.add(new Year(2017));
                    years.add(new Year(2018));
                    return this.years;
                }
            }
        }
    }
}
