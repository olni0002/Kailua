import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        System.out.print("username: ");
        String username = console.nextLine();
        System.out.print("password: ");
        String password = console.nextLine();
        
        String table = "";

        while (!(table.equals("0"))) {
            System.out.println("Which table do you want to configure (0 to exit program)?");
            table = console.nextLine();

            if (table.equals("lejer")) {
                new MySqlConnection().MenuForLejer();
                continue;
            }
            
            System.out.println("Your options are:");
            System.out.println();
            System.out.println("    0) Exit program");
            System.out.println("    1) Insert data into the table");
            System.out.println("    2) Show data in the table");
            System.out.println("    3) Update data in the table");
            System.out.println("    4) Delete data in the table");

            System.out.print("Choose option [1-4]: ");
            String option = console.nextLine();

            switch (option) {
                case "1":
                    TestConnector.insertInto(table);
                    TestConnector.executeQuery(username, password);
                    break;
                case "2":
                    TestConnector.select(table);
                    break;
                case "3":
                    TestConnector.update(table);
                    TestConnector.executeQuery(username, password);
                    break;
                case "4":
                    TestConnector.delete(table);
                    TestConnector.executeQuery(username, password);
                    break;
            }

            
        }

    }
}