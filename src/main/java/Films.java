import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Films {
    private int filmsId;
    private String filmsName;
    private String genreOfFilms;
    private int durationOfFilms;
    private static List<Films> filmsList = new ArrayList<Films>();

    public Films() {
        filmsId = -1;
        filmsName = "Unknown";
        genreOfFilms = "Unknown";
        durationOfFilms = -1;
    }

    public Films(int id,String name,String genre, int duration) {
        filmsId = id;
        filmsName = name;
        genreOfFilms = genre;
        durationOfFilms = duration;
    }

    private static void getFilmsFromDB() {
        Connection con = Connect.connect();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM films");
            while(rs.next()) {
                Films nFilms = new Films();
                nFilms.filmsId=rs.getInt(1);
                nFilms.filmsName=rs.getString(2);
                nFilms.genreOfFilms = rs.getString(3);
                nFilms.durationOfFilms=rs.getInt(4);
                if(!filmsList.contains(nFilms)){
                    addFilms(nFilms);
                }
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Problem with films getting");
        } finally {
            Connect.closeConnection(con);
        }
    }
    private static void addFilms(Films f) {
        filmsList.add(f);
    }

    public static Films getFilms(int ID) {
        getFilmsFromDB();
        for(Films f : filmsList) {
            if(f.filmsId == ID) {
                return f;
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Films films = (Films) o;

        if (filmsId != films.filmsId) return false;
        if (genreOfFilms != films.genreOfFilms) return false;
        if (durationOfFilms != films.durationOfFilms) return false;
        return filmsName != null ? filmsName.equals(films.filmsName) : films.filmsName == null;
    }
}

