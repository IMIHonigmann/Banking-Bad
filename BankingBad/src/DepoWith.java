import java.util.Scanner;

public class DepoWith {
    static int totalMoney;
    static int amogus = 5;
    static String butthole = "shit";
    static String currentUser;

    static Scanner scanner = new Scanner(System.in);
    static String userChoice;
    static boolean repeatLastWhile = false;

    public static void main(String[] args) {

        initialInputs();

        // DONE passwort system reinlegen man sollte es nicht einfach accessen können
        // FIX Grosse Zahlen werden ignoriert
        // FIX Input Protection muss rein

        // While (true) == bad!!!
        // boolean if(input != null)
        while (true) {
            System.out.println(
                    "Welcome back " + userChoice
                            + " What do you wanna do? (DEPOSIT | WITHDRAW | SENDMONEY | CONVERT | EXIT)");
            String usrInpDeWi = scanner.nextLine();
            usrInpDeWi = usrInpDeWi.toLowerCase();
            usrInpDeWi = usrInpDeWi.replace(" ", "");

            // erinnerung dass es equals gibt
            if (usrInpDeWi.contains("deposit")) {
                while (true) {
                    System.out.println("How much do you want to deposit?");
                    String usrWi = scanner.nextLine();
                    int usrWiNum;
                    try {
                        usrWiNum = Integer.parseInt(usrWi);
                        usrWiNum = Math.abs(usrWiNum);
                        Clients.changeClientBalance(userChoice, usrWiNum);
                        Clients.getClientList().get(userChoice).printStatistics();
                        break;
                    } catch (NumberFormatException e) {
                        print.printR("Invalid input please try again", 1);
                        continue;
                    }
                }
            } else if (usrInpDeWi.contains("withdraw")) {
                while (true) {
                    System.out.println("How much do you want to withdraw?");
                    String usrWi = scanner.nextLine();
                    int usrWiNum;
                    try {
                        usrWiNum = Integer.parseInt(usrWi);
                        usrWiNum = Math.abs(usrWiNum);
                        Clients.changeClientBalance(userChoice, -usrWiNum);
                        Clients.getClientList().get(userChoice).printStatistics();
                        break;
                    } catch (NumberFormatException e) {
                        print.printR("Invalid input please try again", 1);
                        continue;
                    }
                }
            } else if (usrInpDeWi.contains("sendmoney")) {
                while (true) {
                    System.out.println("Who do you want to send money to?");
                    String usrTr = scanner.nextLine();
                    // usrTr = usrTr.toLowerCase();
                    if (Clients.getClientList().containsKey(usrTr)
                            && Clients.getClientList().get(usrTr).accountBlocked == false) {

                    } else {
                        print.printR("This user doesn't exist or his account is blocked");
                        continue;
                    }

                    System.out.println("How much money would you like to transfer");
                    String balanceToSend = scanner.nextLine();

                    Clients.changeClientBalance(userChoice, -Math.abs(Integer.parseInt(balanceToSend)));
                    Clients.changeClientBalance(usrTr, Math.abs(Integer.parseInt(balanceToSend)));

                    print.printG("Your Money has been successfully transfered!");
                    break;
                }

                // FIX man wird direkt rausgeworfen wenn man was falsches eintippt und darf
                // nochmal sich einloggen
            } else if (usrInpDeWi.contains("convert")) {
                System.out.println("What currency would you like to convert to (EUR | USD | GBP)");
                String targetBalance = scanner.nextLine();
                targetBalance = targetBalance.toUpperCase();
                targetBalance = targetBalance.replace(" ", "");
                Clients.convertBalanceOfUser(userChoice, targetBalance);
                print.printG("Balance has been successfully converted");
                // break;
            } else if (usrInpDeWi.contains("exit")) {
                break;
            }
        }
        initialInputs();

        scanner.close();
    }

    public static void initialInputs() {
        while (true) {
            System.out.println("Choose what you wanna do (DATABASE | OPERATIONS)");
            userChoice = scanner.nextLine();
            if (userChoice.contains("database")) {
                Clients.dataBase();
                continue;
            } else if (userChoice.contains("operations")) {
                while (true) {
                    System.out.println("Please enter your full name or (CANCEL)");
                    userChoice = scanner.nextLine();

                    if (userChoice.equals("cancel")) {
                        repeatLastWhile = true;
                        break;
                    } else if (Clients.getClientList().containsKey(userChoice)) {
                        if (Clients.getClientList().get(userChoice).accountBlocked) {
                            print.printR("This account is blocked and cannot be used");
                            continue;
                        }
                        currentUser = userChoice;
                        break;
                    } else
                        print.printR("The user " + userChoice + " doesn't exist");
                    continue;
                }
                if (repeatLastWhile == true) {
                    repeatLastWhile = false;
                    continue;
                } else {
                    repeatLastWhile = false;
                    break;
                }
            }
        }

        int attemptsLeft = 3;
        while (true) {
            System.out.println("Please enter your password " + currentUser);
            String passwordInput = scanner.nextLine();

            if (passwordInput.equals("" + Clients.getClientList().get(currentUser).nummern)) {
                print.printG("LOGIN SUCCESSFUL");
                break;
            } else {
                attemptsLeft--;
                print.printR("INVALID PASSWORD YOU HAVE " + attemptsLeft + " ATTEMPTS LEFT");

                if (attemptsLeft == 0) {
                    Clients.getClientList().get(currentUser).blockAccount(true);
                    print.printR("Your account has been blocked please contact the support");
                    initialInputs();
                    return;
                } else
                    continue;
            }
        }
    }
}