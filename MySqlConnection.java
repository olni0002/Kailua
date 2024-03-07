import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class MySqlConnection {
    private String database = "jdbc:mysql://localhost:3306/biludlejning";
    private String username = "bruger1";
    private String password = "123";
    private Connection connection = null;


    public MySqlConnection() {
        createConnection();
    }

    private void createConnection() {
        if (connection != null)
            return; // If connection already created, just return that to ensure singleton

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        connection = null;
    }


    public void addLejer(Lejer lejer) {

        String query = "INSERT INTO lejer (navn,adresse,postnummer,city,country_code,telefon_nummer,email,kørekortnummer,kørekortsudstedelsesdato)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lejer.getNavn());
            preparedStatement.setString(2, lejer.getAddresse());
            preparedStatement.setInt(3, lejer.getPostNummer());
            preparedStatement.setString(4, lejer.getCity());
            preparedStatement.setInt(5, lejer.getCountrycode());
            preparedStatement.setInt(6, lejer.getTelefonNummer());
            preparedStatement.setString(7, lejer.getEmail());
            preparedStatement.setInt(8, lejer.getKørekortnummer());
            preparedStatement.setDate(9, Date.valueOf(lejer.getKørekortsudstedelsesdato()));

            preparedStatement.executeUpdate();

            System.out.println("Lejer is added to the database! :)");

        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    public void deleteLejer() {

        Scanner scanner = new Scanner(System.in);


        try {
            System.out.println("hvad er navnet på personen du vil slette");
            String name = scanner.nextLine();
            String query = "DELETE FROM lejer WHERE navn = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);


        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


    }

    public void updateLejer() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("What is the name of the person you want to update?");
            String name = scanner.nextLine();

            System.out.println("What category do you want to change?");
            String kategori = scanner.nextLine();

            int parameterIndex = 0;
            switch (kategori) {
                case "navn":
                case "adresse":
                case "city":
                case "email":
                    parameterIndex = 1;
                    break;
                case "postnummer":
                case "country_code":
                case "telefon_nummer":
                case "kørekortnummer":
                    parameterIndex = 2;
                    break;
                case "kørekortsudstedelsesdato":
                    parameterIndex = 3;
                    break;
                default:
                    System.out.println("Invalid category.");
                    return;
            }

            System.out.println("What should it be changed to?");
            String svar = scanner.nextLine();

            String query = "UPDATE Lejer SET " + kategori + " = ? WHERE navn = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, svar);
            pstmt.setString(2, name);

            if (parameterIndex == 2) {
                pstmt.setInt(1, Integer.parseInt(svar));
            } else if (parameterIndex == 3) {
                pstmt.setDate(1, Date.valueOf(svar));
            }

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    public ArrayList<Lejer> ShowAllLejer() {

        ArrayList<Lejer> lejers = new ArrayList<>();
        String query = "SELECT * FROM Lejer;";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String navn = rs.getNString("navn");
                String adresse = rs.getString("adresse");
                int postnummer = rs.getInt("postnummer");
                String city = rs.getString("city");
                int countrycode = rs.getInt("country_code");
                int telefonnummer = rs.getInt("telefon_nummer");
                String email = rs.getString("email");
                int kørekortnummer = rs.getInt("kørekortnummer");
                LocalDate kørekorsdato = rs.getDate("kørekortsudstedelsesdato").toLocalDate();

                Lejer lejer = new Lejer(navn, adresse, postnummer, city, countrycode, telefonnummer, email, kørekortnummer, kørekorsdato);
                lejers.add(lejer);
            }


        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        return lejers;
    }

    public void ShowLejer(){
        for (int i = 0; i < ShowAllLejer().size(); i++) {
            System.out.println(ShowAllLejer().get(i));
        }
    }

    




}