public class Main {
        public static  void main(String args []) {
            Films f = Films.getFilms(1);
            Halls h = Halls.getHalls(2);
            Price p = Price.getPrice(1);
            Session s = Session.getSession(2);
            Tickets t = Tickets.getTickets(1);
        }
    }

