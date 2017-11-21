import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Statement;

public class Price {
    private int priceId;
    private int amount;
    private String currency;
    private static List<Price> Price = new ArrayList<>();

    public Price() {
        priceId = -1;
        amount = -1;
        currency = "Unknown";
    }
    public Price(int id, int amount, String currency) {
        priceId = id;
        amount = amount;
        currency = currency;
    }
    private static void addPrice(Price p) { Price.add(p); }
    private static void getPriceFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM price");
            while(rs.next()) {
                Price nPrice = new Price();
                nPrice.priceId = rs.getInt(1);
                nPrice.amount =rs.getInt(2);
                nPrice.currency = rs.getString(3);
                if(!Price.contains(nPrice)){
                    addPrice(nPrice);
                }
            }
            rs.close();
            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting price");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static Price getPrice(int ID) {
        getPriceFromDB();
        for(Price p : Price) {
            if(p.priceId == ID) {
                return p;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price that = (Price) o;

        if (priceId != that.priceId) return false;
        if (amount != that.amount) return false;
        return currency != null ? currency.equals(that.currency) : that.currency == null;
    }

}