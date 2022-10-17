package cinema;

import java.util.Scanner;

public class Cinema {

    public static final int EXPENSIVE = 10;
    public static final int CHEAP = 8;

    public static final int THRESHOLD = 60;

    private static char[][] room;

    public static int profit() {
        int nRows = room.length;
        int seatsPerRow = room[0].length;
        int totalSeats = nRows * seatsPerRow;
        if (totalSeats <= THRESHOLD) {
            return EXPENSIVE * totalSeats;
        }
        //System.out.println(EXPENSIVE * (nRows / 2) * seatsPerRow);
        //System.out.println(CHEAP * (nRows - nRows / 2) * seatsPerRow);
        return EXPENSIVE * (nRows / 2) * seatsPerRow + CHEAP * (nRows - nRows / 2) * seatsPerRow;

    }

    public static int getPrice(int r, int s) {
        int nRows = room.length;
        int seatsPerRow = room[0].length;
        int totalSeats = nRows * seatsPerRow;
        if (totalSeats <= THRESHOLD || r <= nRows / 2) {
            return EXPENSIVE;
        }
        return CHEAP;
    }

    public static void buyTicket() {
        int nRows = room.length;
        int seatsPerRow = room[0].length;
        boolean validSeat = false;
        int r = -1;
        int s = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter a row number:");
            r = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            s = scanner.nextInt();
            if (r <= 0 || s <= 0 || r > nRows || s > seatsPerRow) {
                System.out.println("Wrong input!");
            } else if (room[r - 1][s - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
            } else {
                validSeat = true;
            }
        } while (!validSeat);

        System.out.println("Ticket price: $" + getPrice(r, s));
        room[r - 1][s - 1] = 'B';
    }

    public static void createRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int nRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        room = new char[nRows][seatsPerRow];
        for (int i = 0; i < nRows; ++i) {
            for (int j = 0; j < seatsPerRow; ++j) {
                room[i][j] = 'S';
            }
        }
    }

    public static void printRoom() {
        int nRows = room.length;
        int seatsPerRow = room[0].length;

        System.out.println("Cinema:");
        System.out.print(" ");
        for (int s = 1; s <= seatsPerRow; ++s) {
            System.out.print(" " + s);
        }
        System.out.println();

        for (int r = 1; r <= nRows; ++r) {
            System.out.print(r);
            for (int s = 1; s <= seatsPerRow; ++s) {
                System.out.print(" " + room[r - 1][s - 1]);
            }
            System.out.println();
        }
    }

    public static void printStatistics() {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int nRows = room.length;
        int seatsPerRow = room[0].length;
        for (int r = 1; r <= nRows; ++r) {
            for (int s = 1; s <= seatsPerRow; ++s) {
                if (room[r - 1][s - 1] == 'B') {
                    purchasedTickets += 1;
                    currentIncome += getPrice(r, s);
                }
                totalIncome += getPrice(r, s);
            }
        }
        double percentage = ((double) purchasedTickets) * 100 / (nRows * seatsPerRow);
        System.out.println("Number of purchased tickets: %d".formatted(purchasedTickets));
        System.out.println("Percentage: %.2f%%".formatted(percentage));
        System.out.println("Current income: $%d".formatted(currentIncome));
        System.out.println("Total income: $%d".formatted(totalIncome));
    }

    public static int chooseAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        int action = scanner.nextInt();
        switch (action) {
            case 1:
                printRoom();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                printStatistics();
                break;
            case 0:
                break;
        }
        return action;
    }

    public static void main(String[] args) {
        createRoom();
        printRoom();
        int action = -1;
        while (action != 0) {
            action = chooseAction();
        }

    }
}