package com.zaplan;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Element {
    private String title, start, end;

    public Element() {
    }

    public Element(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}