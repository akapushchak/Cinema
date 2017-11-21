import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Tickets {
    private int ticketsId;
    private int row;
    private int place;
    private int sessionId;
    private int priceId;
    public static List<Tickets> tickets = new ArrayList<Tickets>();

    public Tickets() {
        ticketsId = -1;
        row = -1;
        place = -1;
        sessionId = -1;
        priceId = -1;
    }

    public Tickets(int tId, int row, int place, int sId, int pId) {
        ticketsId = tId;
        row = row;
        place = place;
        sessionId = sId;
        priceId = pId;
    }

    private static void getTicketsFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tickets ");
            while (rs.next()) {
                Tickets nTickets = new Tickets();
                nTickets.ticketsId = rs.getInt(1);
                nTickets.row=rs.getInt(2);
                nTickets.place=rs.getInt(3);
                nTickets.sessionId=rs.getInt(4);
                nTickets.priceId=rs.getInt(5);
                if(!tickets.contains(nTickets)) {
                    ticketsAddToList(nTickets);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Problem with getting");
            e.printStackTrace();
        } finally {
            Connect.closeConnection(con);
        }
    }

    private static void ticketsAddToList(Tickets t) { tickets.add(t); }

    public static Tickets getTickets(int ID) {
        getTicketsFromDB();
        for (Tickets t : tickets) {
            if (t.ticketsId == ID) {
                return t;
            } else {
                return null;
            }

        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tickets tickets = (Tickets) o;

        if (ticketsId != tickets.ticketsId) return false;
        if (row != tickets.row) return false;
        if (place!= tickets.place) return false;
        if (sessionId!= tickets.sessionId) return false;
        return priceId == tickets.priceId;
    }

}

