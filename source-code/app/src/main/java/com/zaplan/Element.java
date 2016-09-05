package com.zaplan;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Element {
    private String title, start, end, imageLink;

    public Element() {
    }

    public Element(String title, String start, String end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Element(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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