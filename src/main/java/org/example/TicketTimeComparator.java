package org.example;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int flightTimeCompare = Integer.compare(t1.getFlightTime(), t2.getFlightTime());
        if (flightTimeCompare != 0) {
            return flightTimeCompare;
        }
        // Если время полёта одинаковое, сравниваем по цене
        return Integer.compare(t1.getPrice(), t2.getPrice());
    }
}
