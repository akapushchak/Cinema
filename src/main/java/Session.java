import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private int sessionId;
    private java.sql.Time time;
    private java.sql.Date data;
    private int filmsId;
    private int hallsId;
    private static List<Session> sessionList = new ArrayList<Session>();

    public Session() {
        sessionId = -1;
        time = -1;
        data = -1;
        filmsId = -1;
        hallsId = -1;
    }
    public Session( int sId, java.sql.Time time, java.sql.Date data, int fId, int hId) {
        sessionId = sId;
        time = time;
        data = data;
        filmsId = fId;
        hallsId = hId;
    }
    private static void addSession(Session s) {
        sessionList.add(s);
    }
    private static void getSessionFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM session");
            while(rs.next()) {
                Session nSes = new Session();
                nSes.sessionId = rs.getInt(1);
                nSes.time = rs.(2);
                nSes.data=rs.(3);
                nSes.filmsId=rs.getInt(4);
                nSes.hallsId=rs.getInt(5);
                if(!sessionList.contains(nSes)) {
                    addSession(nSes);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with getting session");
        } finally {
            Connect.closeConnection(con);
        }
    }
    public static Session getSession(int ID) {
        getSessionFromDB();
        for(Session s: sessionList) {
            if(s.sessionId == ID) {
                return  s;
            }
        }
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (sessionId != session.sessionId) return false;
        if (time != session.time) return false;
        if (data != session.data) return false;
        if (filmsId != session.filmsId) return false;
        return hallsId == session.hallsId;
    }

}
