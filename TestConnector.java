import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class TestConnector {
    static String query;
    static String database = "jdbc:mysql://localhost:3306/kailua";
    static String username;
    static String password;
    static Scanner console = new Scanner(System.in);

    public static void addInputToQuery() {
        query += "'" + console.nextLine() + "', ";
    }

    public static void insertInto(String table) {
        query = "INSERT INTO " + table + " VALUES (";

        System.out.println("Please input your values for:");

        switch (table) {
            case "car":
                insertIntoCar();
                break;
            case "lejer":
                insertIntoCustomer();
                break;
            case "lejekontrakt":
                insertIntoLease();
                break;
        }
    }

    private static void insertIntoCustomer() {
        System.out.print("name: ");
        addInputToQuery();

        System.out.println("address: ");
        addInputToQuery();

        System.out.println("zip code: ");
        addInputToQuery();

        System.out.println("city: ");
        addInputToQuery();

        System.out.println("phone country code: ");
        addInputToQuery();

        System.out.println("phone number: ");
        addInputToQuery();

        System.out.println("email: ");
        addInputToQuery();

        System.out.println("drivers license number: ");
        addInputToQuery();

        System.out.println("drivers license registration date: ");
        addInputToQuery();



        query = query.substring(0, query.length() - 2) + ");";


    }

    private static void insertIntoCar() {
        System.out.print("car tier: ");
        addInputToQuery();

        System.out.println("brand: ");
        addInputToQuery();

        System.out.println("model: ");
        addInputToQuery();

        System.out.println("fuel type: ");
        addInputToQuery();

        System.out.println("license plate: ");
        addInputToQuery();

        System.out.println("registration date: ");
        addInputToQuery();

        System.out.println("total kilometers: ");
        addInputToQuery();

        query = query.substring(0, query.length() - 2) + ");";

    }

    private static void insertIntoLease() {
        System.out.print("customer id: ");
        addInputToQuery();

        System.out.println("drivers license number: ");
        addInputToQuery();

        System.out.println("start of lease: ");
        addInputToQuery();

        System.out.println("end of lease: ");
        addInputToQuery();

        System.out.println("maximum kilometers: ");
        addInputToQuery();

        System.out.println("license plate: ");
        addInputToQuery();

        query = query.substring(0, query.length() - 2) + ");";
    }
    
    public static void update(String table) {
        query = "UPDATE " + table;

        switch (table) {
            case "car":
                System.out.print("Which car do you want to update? [Type the license plate of the car]: ");
                updateCar(console.nextLine());
                break;
            case "customer":
                System.out.print("Which customer do you want to update? [Type the drivers license number of the customer]: ");
                updateCustomer(console.nextLine());
                break;
            case "lease":
                System.out.print("Which lease do you want to update? [Type the id of the lease]: ");
                updateLease(console.nextLine());
                break;
        }
        
    }

    private static void updateString(String column, String value) {
        query = " SET " + column + " = '" + value + "' ";
    }

    private static void updateCar(String id) {
        System.out.println("What column do you want to update?");
        System.out.println("""
                Available options:

                    1) car tier
                    2) brand
                    3) model
                    4) fuel type
                    5) license plate
                    6) registration date
                    7) total kilometers
             
                """);
        
        String option = console.nextLine();

        String column = switch (option) {
            case "1" -> "car_tier";
            case "2" -> "brand";
            case "3" -> "model";
            case "4" -> "fuel_type";
            case "5" -> "license_plate";
            case "6" -> "registration date";
            case "7" -> "total_kilometers";
            default -> "";
        };

        if (column.equals("")) {
            System.out.println("Invalid column");
            return;
        }

        System.out.print("Please input new value for the selected column: ");
        String value = console.nextLine();

        updateString(column, value);

        query += "WHERE license_plate = " + id + ";";

    }

    private static void updateCustomer(String id) {
        System.out.println("What column do you want to update?");
        System.out.println("""
                Available options:

                    1) name
                    2) address
                    3) zip code
                    4) city
                    5) phone country code
                    6) phone number
                    7) email
                    8) drivers license number
                    9) drivers licence registation date
             
                """);
        
        String option = console.nextLine();

        String column = switch (option) {
            case "1" -> "navn";
            case "2" -> "adresse";
            case "3" -> "postnummer";
            case "4" -> "city";
            case "5" -> "coutry_code";
            case "6" -> "telefon_nummer";
            case "7" -> "email";
            case "8" -> "kørekortnummer";
            case "9" -> "kørekortsudstedelsesdato";
            default -> "";
        };

        if (column.equals("")) {
            System.out.println("Invalid column");
            return;
        }

        System.out.print("Please input new value for the selected column: ");
        String value = console.nextLine();

        updateString(column, value);

        query += "WHERE kørekortnummer = " + id + ";";

    }

    private static void updateLease(String id) {
        System.out.println("What column do you want to update?");
        System.out.println("""
                Available options:

                    1) customer id
                    2) drivers license number
                    3) start of lease
                    4) end of lease
                    5) maximum kilometers
                    6) license plate
             
                """);
        
        String option = console.nextLine();

        String column = switch (option) {
            case "1" -> "lejer_id";
            case "2" -> "kørekortnummer";
            case "3" -> "fra_date";
            case "4" -> "til_date";
            case "5" -> "max_km";
            case "6" -> "license_plate";
            default -> "";
        };

        if (column.equals("")) {
            System.out.println("Invalid column");
            return;
        }

        System.out.print("Please input new value for the selected column: ");
        String value = console.nextLine();

        updateString(column, value);

        query += "WHERE lejer_id = " + id + ";";
    }

    public static void delete(String table) {
        query = "DELETE FROM " + table + " WHERE ";
        
        switch (table) {
            case "car":
                System.out.print("Which car do you want to delete? [Type the license plate of the car]: ");
                query = "license_plate=" + console.nextLine();
                break;
            case "customer":
                System.out.print("Which customer do you want to delete? [Type the drivers license number of the customer]: ");
                query = "kørekortnummer=" + console.nextLine();
                break;
            case "lease":
                System.out.print("Which lease do you want to delete? [Type the id of the lease]: ");
                query = "lejer_id=" + console.nextLine();
                break;
        }
        
    }

    public static void executeQuery(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(database, username, password);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void select(String table) {
        query = "SELECT * FROM " + table + ";";

        switch (table) {
            case "lejer" -> showLejer();
            case "car" -> showCar();
            case "lejekontrakt" -> showLease();
        }
    }

    private static void showLejer() {
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database, username, password);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String navn = rs.getString("navn");
                String adresse = rs.getString("adresse");
                int postnummer = rs.getInt("postnummer");
                String city = rs.getString("city");
                int countrycode = rs.getInt("country_code");
                int telefonnummer = rs.getInt("telefon_nummer");
                String email = rs.getString("email");
                int kørekortnummer = rs.getInt("kørekortnummer");
                LocalDate kørekorsdato = rs.getDate("kørekortsudstedelsesdato").toLocalDate();

                System.out.print("[" + navn);
                System.out.print(", ");
                System.out.print(adresse);
                System.out.print(", ");
                System.out.print(postnummer);
                System.out.print(", ");
                System.out.print(city);
                System.out.print(", ");
                System.out.print(countrycode);
                System.out.print(", ");
                System.out.print(telefonnummer);
                System.out.print(", ");
                System.out.print(email);
                System.out.print(", ");
                System.out.print(kørekortnummer);
                System.out.print(", ");
                System.out.println(kørekorsdato + "]");
           }

           connection.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    private static void showCar() {
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database, username, password);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String carTier = rs.getString("car_tier");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                String fuelType = rs.getString("fuel_type");
                String licensePlate = rs.getString("license_plate");
                LocalDate registrationDate = rs.getDate("registration_date").toLocalDate();
                String totalKilometers = rs.getString("total_kilometers");

                System.out.print("[" + carTier);
                System.out.print(", ");
                System.out.print(brand);
                System.out.print(", ");
                System.out.print(model);
                System.out.print(", ");
                System.out.print(fuelType);
                System.out.print(", ");
                System.out.print(licensePlate);
                System.out.print(", ");
                System.out.print(registrationDate);
                System.out.print(", ");
                System.out.println(totalKilometers + "]");
           }

           connection.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

    private static void showLease() {
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(database, username, password);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int lejerId = rs.getInt("car_tier");
                int kørekortnummer = rs.getInt("brand");
                LocalDate fraDate = rs.getDate("model").toLocalDate();
                LocalDate tilDate = rs.getDate("fuel_type").toLocalDate();
                int maxKm = rs.getInt("license_plate");
                String licensePlate = rs.getString("registration_date");
                
                System.out.print("[" + lejerId);
                System.out.print(", ");
                System.out.print(kørekortnummer);
                System.out.print(", ");
                System.out.print(fraDate);
                System.out.print(", ");
                System.out.print(tilDate);
                System.out.print(", ");
                System.out.print(maxKm);
                System.out.print(", ");
                System.out.println(licensePlate + "]");
           }

           connection.close();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
    }

}