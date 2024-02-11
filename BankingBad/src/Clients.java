import java.util.Scanner;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.HashSet;
import java.util.Random;

public class Clients {
    private static HashMap<String, Client> clientMap = new HashMap<>();
    static HashSet<Integer> usedKeys = new HashSet<>();
    static int iteratedId = 1;

    public static void dataBase() {
        Scanner scanner = new Scanner(System.in);

        // FIX first letter soll uppercase sein
        while (true) {
            System.out.println(
                    "Insert your credentials or do an operation (EXIT | SHOWUSERS | REGISTER)");
            String userInput = scanner.nextLine();
            String userInputFiltered = userInput.toLowerCase().replace(" ", "");

            if (userInputFiltered.contains("register")) {

                String name = "";
                int nummern = 0;
                int lustigeNummern = 0;
                int alter = 0;

                while (true) {
                    System.out.println("Please enter your first name");
                    name = scanner.nextLine();
                    break;
                }
                while (true) {
                    System.out.println("Please enter your last name");
                    name += " " + scanner.nextLine();
                    break;
                }
                while (true) {
                    nummern = new Random().nextInt(10000, 99999);
                    generatePasswordStyle();

                    if (usedKeys.contains(nummern) && usedKeys != null) {
                        print.printR(
                                "The key " + nummern + " has already been assigned. A new number is being generated");
                        continue;
                    } else {
                        usedKeys.add(nummern);
                        print.printG("SUCCESS: Your secret number is: " + nummern);
                        break;
                    }
                }
                while (true) {
                    System.out.println("Your Keynumber is being generated...");
                    lustigeNummern = new Random().nextInt(10000000, 99999999);
                    generateLustigeNummernStyle();

                    if (usedKeys.contains(lustigeNummern)) {
                        print.printR("The key" + lustigeNummern
                                + "has already been assigned. A new number is being generated");
                        continue;
                    } else {
                        print.printG("SUCCESS: Your Lustignummern are: " + lustigeNummern);
                        usedKeys.add(lustigeNummern);
                        break;
                    }
                }
                // FIX no input protection
                while (true) {
                    System.out.println("Please enter your age");
                    alter = scanner.nextInt();
                    break;
                }

                Client client = new Client(name, nummern, 0, lustigeNummern, alter, iteratedId);
                iteratedId++;
                clientMap.put(name, client);
                client.printStatistics();
                continue;

            } else if (userInputFiltered.contains("exit"))
                break;
            else if (userInputFiltered.contains("showusers")) {
                // clientMap.forEach((name, clientValues) -> clientValues.name += "ficker");
                clientMap.values().stream()
                        .filter(currentClient -> !currentClient.name.toLowerCase().contains("kleinen")
                                && !currentClient.name.toLowerCase().contains("epstein"))
                // .forEach(filteredClient -> filteredClient.name += " ist der coolste")
                ;

                for (Client c : clientMap.values()) {
                    if (c.name.contains("epstein")) {
                        c.name += " can go fuck himself";
                    }
                    c.printStatistics();
                }

                clientMap.values().stream()
                        .filter(currentClient -> !currentClient.name.toLowerCase().contains("kleinen")
                                || !currentClient.name.toLowerCase().contains("epstein"))
                        .forEach(filteredClient -> filteredClient.name.replace(" ist der coolste", ""));

                // clientMap.forEach((bankguyname, bankguythingys) ->
                // bankguythingys.name.replace("ficker", ""));
            }

            // GOOD FOR FAST REGISTERING AND DEBUGGING BUT CREATES AN EMPTY ACC ON EVERY
            // REGISTERED USER
            /*
             * else {
             * Client client = new Client(userInput, 42069, 9000, 12348765, 69, 1);
             * clientMap.put(userInputFiltered, client);
             * }
             */
        }

        // scanner.close();
    }

    public static HashMap<String, Client> getClientList() {
        return clientMap;
    }

    public Client getClientByName(String key) {
        return clientMap.get(key);
    }

    public static void changeClientBalance(String key, int money) {
        clientMap.get(key).balance += money;
    }

    public static void generatePasswordStyle() {
        System.out.print("Generating a password.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".");
        System.out.print("Validating 5 Digit Number.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".");

        System.out.println("");
    }

    public static void generateLustigeNummernStyle() {
        System.out.print("Generating a lustige Number.");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".");
        System.out.print("Validating 8 Digit Number.");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(".");

        System.out.println("");
    }

    public static void convertBalanceOfUser(String targetClient, String targetCurrency) {

        double conversionRateToUSD = 1;
        double conversionRateToGBP = 0.79;
        double conversionRateToEUR = 0.93;

        switch (clientMap.get(targetClient).currency) {
            case "USD":
                conversionRateToUSD = 1;
                conversionRateToGBP = 0.79;
                conversionRateToEUR = 0.93;
                break;
            case "GBP":
                conversionRateToUSD = 1.26;
                conversionRateToGBP = 1;
                conversionRateToEUR = 1.17;
                break;
            case "EUR":
                conversionRateToUSD = 1;
                conversionRateToGBP = 0.86;
                conversionRateToEUR = 1;
                break;
            default:
                break;
        }

        double result = (double) clientMap.get(targetClient).balance;

        switch (targetCurrency) {
            case "USD":
                result *= conversionRateToUSD;
                break;
            case "GBP":
                result *= conversionRateToGBP;
                break;
            case "EUR":
                result *= conversionRateToEUR;
                break;
            default:
                print.printR("This currency isn't registered and has been ignored");
                break;
        }

        clientMap.get(targetClient).balance = (int) result;
        clientMap.get(targetClient).currency = targetCurrency;
    }

    public double getInverse(double factor) {
        return factor = 1 - factor + 1;
    }
}
