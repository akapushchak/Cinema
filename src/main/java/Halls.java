import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.SQLException;

public class Halls {
    private int hallsId;
    private String hallsName;
    private int hallsValue;
    private static List<Halls> hallsList = new ArrayList<>();

    public Halls() {
        hallsId = -1;
        hallsName = "Unknown";
        hallsValue = -1;
    }
    public Halls(int Id, String name, int value) {
        hallsId = Id;
        hallsName = name;
        hallsValue = value;
    }
    private static void hallsAdd(Halls h) {
        hallsList.add(h);
    }
    private static void getHallsFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM halls");
            while(rs.next()) {
                Halls nHalls = new Halls();
                nHalls.hallsId  = rs.getInt(1);
                nHalls.hallsName = rs.getString(2);
                nHalls.hallsValue = rs.getInt(3);
                if(!hallsList.contains(nHalls)) {
                    hallsAdd(nHalls);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting halls");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static Halls getHalls(int ID) {
        getHallsFromDB();
        for(Halls h : hallsList) {
            if(h.hallsId == ID) {
                return h;
            }
            return null;
        }
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Halls halls = (Halls) o;

        if (hallsId != halls.hallsId) return false;
        if (hallsValue != halls.hallsValue) return false;
        return hallsName != null ? hallsName.equals(halls.hallsName) : halls.hallsName == null;
    }

}
