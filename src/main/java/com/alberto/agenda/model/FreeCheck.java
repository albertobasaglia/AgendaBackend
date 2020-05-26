package com.alberto.agenda.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FreeCheck {
    List<Long> who = new ArrayList<>();
    Date from;
    Date to;

    public List<Long> getWho() {
        return who;
    }

    public void setWho(List<Long> who) {
        this.who = who;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
