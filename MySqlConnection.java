import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public void Usertypes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("write a name");
        String name = scanner.nextLine();
        System.out.println("wirte a adresse");
        String adresse = scanner.nextLine();
        System.out.println("write a postnummer");
        int postnummer = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("write a city");
        String city = scanner.nextLine();
        System.out.println("write a countrycode (like +45, but without the +)");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("write a telephone number");
        int telefon = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("write an email");
        String email = scanner.nextLine();
        System.out.println("kørekortnummer");
        int kørekortnummer = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("kørekortdato (format dd-mm-yyyy)");
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        addLejer(new Lejer(name,adresse,postnummer,city,code,telefon,email,kørekortnummer,date));
    }

    public void createLejer(){
        Usertypes();

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
        ShowLejer();
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
    public void MenuForLejer(){
        Scanner scanner = new Scanner(System.in);
        boolean option = true;
        int svar;
        while (option){
            System.out.println("1. delete a lejer");
            System.out.println("2. update a lejer");
            System.out.println("3. add a lejer");
            System.out.println("4 show lejers");
            System.out.println("5. Quit");
            svar = scanner.nextInt();
            switch (svar){
                case 1 -> deleteLejer();
                case 2 -> updateLejer();
                case 3 -> Usertypes();
                case 4 -> ShowLejer();
                case 5 -> option = false;
                default -> option =true;
            }

        }

    }
    public void updateLejerkontrakt() {
        Scanner scanner = new Scanner(System.in);
        ShowLejer();
        try {
            System.out.println("What is the id of the kontrakt you want to update?");
            int id = scanner.nextInt();

            scanner.nextLine();
            System.out.println("What category do you want to change?");
            String kategori = scanner.nextLine();

            int parameterIndex = 0;
            switch (kategori) {
                case "Rigistreingsnummer":
                    parameterIndex = 1;
                    break;
                case "kørekortnummer":
                case "max_km":
                    parameterIndex = 2;
                    break;
                case "til_date":
                case "fra_date":
                    parameterIndex = 3;
                    break;
                default:
                    System.out.println("Invalid category.");
                    return;
            }

            System.out.println("What should it be changed to? if its dato (dd-MM-yyyy HH:mm:ss)");
            String svar = scanner.nextLine();

            String query = "UPDATE Lejekontrakt SET " + kategori + " = ? WHERE lejer_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, svar);
            pstmt.setInt(2, id);

            if (parameterIndex == 2) {
                pstmt.setInt(1, Integer.parseInt(svar));
            } else if (parameterIndex == 3) {
                LocalDateTime dateTime = LocalDateTime.parse(svar, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                pstmt.setTimestamp(1, Timestamp.valueOf(dateTime));
            }

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    public ArrayList<Lejekontrakt> ShowAllLejekontrakt() {

        ArrayList<Lejekontrakt> lejekontrakts = new ArrayList<>();
        String query = "SELECT * FROM Lejekontrakt;";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int lejerId = rs.getInt("lejer_id");
                int kørekortnummer = rs.getInt("kørekortnummer");
                LocalDateTime fraDate = rs.getTimestamp("fra_date").toLocalDateTime();
                LocalDateTime tilDate = rs.getTimestamp("til_date").toLocalDateTime();
                int maxKm = rs.getInt("max_km");
                String rigistreingsnummer = rs.getString("Rigistreingsnummer");

                Lejekontrakt adding = new Lejekontrakt(lejerId,kørekortnummer,fraDate,tilDate,maxKm,rigistreingsnummer);
                lejekontrakts.add(adding);
            }


        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        return lejekontrakts;
    }

    public void ShowLejerkontrakt(){
        for (int i = 0; i < ShowAllLejekontrakt().size(); i++) {
            System.out.println(ShowAllLejekontrakt().get(i));
        }
    }

}




