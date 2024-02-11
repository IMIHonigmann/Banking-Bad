import java.util.HashMap;

public class Client {

    String name;
    int nummern;
    int balance;
    int lustigeNummern;
    int alter;
    int id;
    HashMap<String, Integer> transactionHistory;
    boolean accountBlocked = false;
    String currency = "USD";

    public Client(String name, int nummern, int balance, int lustigeNummern, int alter, int id) {
        this.name = name;
        this.nummern = nummern;
        this.balance = balance;
        this.lustigeNummern = lustigeNummern;
        this.alter = alter;
        this.id = id;
    }

    public void printStatistics() {
        print.printY("\n");
        print.printY("Name: " + name);
        print.printY("Nummern: " + nummern);
        print.printY("LustigeNummern: " + lustigeNummern);
        print.printY("Alter: " + alter);
        print.printY("ID: " + id);

        if (balance > 0)
            print.printG("Balance: " + balance + " " + currency, 0);
        else
            print.printR("Balance: " + balance + " " + currency, 0);

        if (accountBlocked) {
            print.printR("THIS ACCOUNT IS BLOCKED", 0);
        }
    }

    public void sendMoney(int moneyToSend, Client targetClient) {
        this.balance -= Math.abs(moneyToSend);
        targetClient.balance += Math.abs(moneyToSend);
        System.out.println("You have successfully sent the money to " + targetClient.name);
    }

    // man kann basically von jedem geld klauen, implementier ein system, welches
    // schaut ob man exakt dieser person geld nehmen darf oder sie die transaktion
    // erlaubt
    public void chargeMoney(int moneyToReceive, Client targetClient) {
        this.balance += Math.abs(moneyToReceive);
        targetClient.balance -= Math.abs(moneyToReceive);
    }

    public void blockAccount(boolean blocked) {
        accountBlocked = true;
    }
}