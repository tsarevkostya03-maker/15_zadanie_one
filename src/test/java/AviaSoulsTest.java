import org.example.AviaSouls;
import org.example.Ticket;
import org.example.TicketTimeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    private AviaSouls manager;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;
    private Ticket ticket4;
    private Ticket ticket5;

    @BeforeEach
    public void setUp() {
        manager = new AviaSouls();
        ticket1 = new Ticket("DME", "LED", 5000, 10, 12);
        ticket2 = new Ticket("DME", "LED", 3000, 14, 16);
        ticket3 = new Ticket("DME", "LED", 7000, 9, 12);
        ticket4 = new Ticket("SVO", "LED", 4000, 11, 13);
        ticket5 = new Ticket("DME", "LED", 2000, 8, 12);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
    }

    @Test
    public void testCompareTo() {
        Assertions.assertTrue(ticket2.compareTo(ticket1) < 0);
        Assertions.assertTrue(ticket1.compareTo(ticket2) > 0);
        Assertions.assertEquals(0, ticket1.compareTo(ticket1));
    }

    @Test
    public void testSearchWithSortByPrice() {
        Ticket[] expected = {ticket5, ticket2, ticket1, ticket3};
        Ticket[] actual = manager.search("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNoTicketsFound() {
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.search("VKO", "KUF");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSingleTicket() {
        AviaSouls emptyManager = new AviaSouls();
        Ticket singleTicket = new Ticket("DME", "LED", 5000, 10, 12);
        emptyManager.add(singleTicket);

        Ticket[] expected = {singleTicket};
        Ticket[] actual = emptyManager.search("DME", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        // ticket2 и ticket1 оба по 2 часа, но ticket2 дешевле
        Assertions.assertTrue(comparator.compare(ticket2, ticket1) < 0);
        Assertions.assertTrue(comparator.compare(ticket1, ticket2) > 0);
        Assertions.assertTrue(comparator.compare(ticket5, ticket2) > 0);
        Assertions.assertTrue(comparator.compare(ticket2, ticket5) < 0);
    }

    @Test
    public void testSearchAndSortByTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        // ticket2: 2 часа, цена 3000
        // ticket1: 2 часа, цена 5000
        // ticket3: 3 часа, цена 7000
        // ticket5: 4 часа, цена 2000
        Ticket[] expected = {ticket2, ticket1, ticket3, ticket5};
        Ticket[] actual = manager.searchAndSortBy("DME", "LED", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByTimeNoTicketsFound() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchAndSortBy("VKO", "KUF", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
