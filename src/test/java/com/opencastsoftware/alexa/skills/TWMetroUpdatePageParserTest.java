package com.opencastsoftware.alexa.skills;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TWMetroUpdatePageParserTest {

    private Document document;

    @Before
    public void setup() {
        document = null;
        try {
            document = Jsoup.parse(new File("src/test/resources/updates.html"), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDisruptionCount() {
        TWMetroUpdatePageParser parser = new TWMetroUpdatePageParser(document);
        assertEquals(parser.getDisruptionsCount(), "No disruptions");
    }

    @Test
    public void testPlannedWorksCount() {
        TWMetroUpdatePageParser parser = new TWMetroUpdatePageParser(document);
        assertEquals(parser.getPlannedWorksCount(), "(5) planned works");
    }

    @Test
    public void testTimetableChangesCount() {
        TWMetroUpdatePageParser parser = new TWMetroUpdatePageParser(document);
        assertEquals(parser.getTimetableChangesCount(), "(1) timetable changes");
    }

    @Test
    public void testToString() {
        TWMetroUpdatePageParser parser = new TWMetroUpdatePageParser(document);
        assertEquals(parser.toString(), "There are currently No disruptions, (5) planned works and (1) timetable changes");
    }
}
