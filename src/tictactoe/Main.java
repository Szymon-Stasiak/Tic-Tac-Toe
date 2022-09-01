package tictactoe;
import java.util.Scanner;

public class Main {

    public static void nextMove(int[] number, char[] pools, int sum, boolean itsX) {
        Scanner scanner = new Scanner(System.in);

        boolean great = false;
        int coordinate = 0;
        while (!great) {
            char input1 = scanner.next().charAt(0);
            char input2 = scanner.next().charAt(0);
            if (!Character.isDigit(input1) || !Character.isDigit(input2)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int input1int = input1 - '0';
            int input2int = input2 - '0';
            if (input1int > 3 || input2int > 3 || input1int < 1 || input2int < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            coordinate = (input1int - 1) * 3 + input2int - 1;
            if (number[coordinate] != 69) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            great = true;
        }
        if (itsX) {
            number[coordinate] = 1;
            pools[coordinate] = 'X';
            sum -= 68;
            itsX = false;
        } else {
            number[coordinate] = 0;
            pools[coordinate] = 'O';
            sum -= 69;
            itsX = true;
        }
        writing(pools);
        check(number, sum, pools, itsX);
    }

    public static void check(int[] number, int sum, char[] pools, boolean itsX) {
        boolean oWins = false;
        boolean xWins = false;
        for (int k = 0; k < 7; k += 3) {
            if (number[k] + number[k + 1] + number[k + 2] == 3) xWins = true;
            else if (number[k] + number[k + 1] + number[k + 2] == 0) oWins = true;
        }
        for (int k = 0; k < 3; k++) {
            if (number[k] + number[k + 3] + number[k + 6] == 3) xWins = true;
            else if (number[k] + number[k + 3] + number[k + 6] == 0) oWins = true;
        }
        if (number[0] + number[4] + number[8] == 3) xWins = true;
        if (number[0] + number[4] + number[8] == 0) oWins = true;
        if (number[2] + number[4] + number[6] == 3) xWins = true;
        if (number[2] + number[4] + number[6] == 0) oWins = true;
        if (oWins) {
            System.out.println("O wins");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (sum < 10) {
            System.out.println("Draw");
        } else nextMove(number, pools, sum, itsX);
    }

    public static void writing(char[] pools) {
        System.out.println("---------");
        int s = 0;
        while (s < 7) {
            System.out.println("| " + pools[s] + " " + pools[s + 1] + " " + pools[s + 2] + " |");
            s += 3;
        }
        System.out.println("---------");
    }


    public static void main(String[] args) {
        boolean itsX = true;
        char[] pools = new char[9];
        int[] number = new int[9];
        int sum = 0;
        for (int k = 0; k < 9; k++) {
            pools[k] = ' ';
            number[k] = 69;
            sum += number[k];
        }
        writing(pools);
        nextMove(number, pools, sum, itsX);
    }
}
