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

    public static void buyTicket(int r, int s) {
        room[r - 1][s - 1] = 'B';
    }

    public static void createRoom(int nRows, int seatsPerRow) {
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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int nRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        createRoom(nRows, seatsPerRow);
        printRoom();

        System.out.println("Enter a row number:");
        int r = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int s = scanner.nextInt();
        System.out.println("Ticket price: $" + getPrice(r, s));
        buyTicket(r, s);
        printRoom();

        // System.out.println("Total income:");
        // System.out.println("$" + profit(nRows, seatsPerRow));


        // Write your code here
        /*
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        System.out.println("1 S S S S S S S S");
        System.out.println("2 S S S S S S S S");
        System.out.println("3 S S S S S S S S");
        System.out.println("4 S S S S S S S S");
        System.out.println("5 S S S S S S S S");
        System.out.println("6 S S S S S S S S");
        System.out.println("7 S S S S S S S S");
        */

    }
}