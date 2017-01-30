package com.opencastsoftware.alexa.skills;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TWMetroUpdatePageParser {

    private String disruptionsCount;
    private String plannedWorksCount;
    private String timetableChangesCount;

    public TWMetroUpdatePageParser(Document doc) {
        Elements elements = doc.select("#service-status > div > ul > li > ul").first().children();
        disruptionsCount = elements.get(0).text();
        plannedWorksCount = elements.get(1).text();
        timetableChangesCount = elements.get(2).text();
    }

    public String getDisruptionsCount() {
        return disruptionsCount;
    }

    public String getTimetableChangesCount() {
        return timetableChangesCount;
    }

    public String getPlannedWorksCount() {
        return plannedWorksCount;
    }

    public String toString() {
        return "There are currently " + this. disruptionsCount + ", " + this.plannedWorksCount + " and " + this.timetableChangesCount;
    }
}
