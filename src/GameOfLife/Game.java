package GameOfLife;

import java.util.Scanner;

public class Game {
    public enum cellState {
        LIVING, DEAD, NOTHING
    };

    public static cellState currentGen[][] = new cellState[100][100];
    public static cellState nextGen[][] = new cellState[100][100];
    public static int livingCells = 0;
    public static int genNum = 0;
    public static int time = 0;
    public static Scanner in = new Scanner(System.in);

    public static void main(String args[]) {
        System.out.print("Please enter the number of generations: ");
        int wantedGenNum = in.nextInt();
        createEnvironment();
        createAcorn();
        createNextGen();
        displayEnvironment();

        do {
            genNum++;
            for (int i = 0; i < 99; i++) {
                for (int j = 0; j < 99; j++) {
                    currentGen[i][j] = cellCheck(i, j);
                }
            }
            createNextGen();
            displayEnvironment();
        } while (genNum < wantedGenNum); // while(livingCells > 0);
    }

    public static void createNextGen() {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                nextGen[i][j] = currentGen[i][j];
            }
        }
    }

    public static void createEnvironment() {
        System.out.print("Creating Enviroment...");
        System.out.println();
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                currentGen[i][j] = cellState.DEAD;
                if (currentGen[i][j] == cellState.LIVING) {
                    System.out.print("# ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.print("Living Cells: " + livingCells + " ----- Generation: " + genNum + " ----- Time: " + time);
        System.out.println();
        System.out.println();
    }

    public static void createAcorn() {
        System.out.print("Creating Acorn...");
        System.out.println();
        currentGen[50][50] = cellState.LIVING;
        currentGen[51][51] = cellState.LIVING;
        currentGen[51][52] = cellState.LIVING;
        currentGen[51][53] = cellState.LIVING;
        currentGen[49][48] = cellState.LIVING;
        currentGen[51][48] = cellState.LIVING;
        currentGen[51][47] = cellState.LIVING;
        livingCells = 7;
    }

    public static void displayEnvironment() {
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (nextGen[i][j] == cellState.LIVING) {
                    System.out.print("# ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.print("Living Cells: " + livingCells + " ----- Generation: " + genNum + " ----- Time: " + time);
        System.out.println();
        System.out.println();
    }

    public static cellState cellCheck(int i, int j) {
        int neighborCount = 0;
        if (checkTop(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkTopRight(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkRight(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkBottomRight(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkBottom(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkBottomLeft(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkLeft(i, j) == cellState.LIVING) {
            neighborCount++;
        }
        if (checkTopLeft(i, j) == cellState.LIVING) {
            neighborCount++;
        }

        if (nextGen[i][j] == cellState.LIVING && neighborCount < 2) {
            livingCells--;
            return cellState.DEAD;
        } else if (nextGen[i][j] == cellState.LIVING && (neighborCount == 2 || neighborCount == 3)) {
            return cellState.LIVING;
        } else if (nextGen[i][j] == cellState.LIVING && neighborCount > 3) {
            livingCells--;
            return cellState.DEAD;

        } else if (nextGen[i][j] == cellState.DEAD && neighborCount == 3) {
            livingCells++;
            return cellState.LIVING;
        } else {
            return cellState.DEAD;
        }

    }

    public static cellState checkTop(int i, int j) {
        if (i == 0 || i == 99)
            return cellState.NOTHING;
        else if (nextGen[i - 1][j] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkTopRight(int i, int j) {
        if ((i == 0 || i == 99) || (j == 0 || j == 99))
            return cellState.NOTHING;
        else if (nextGen[i - 1][j + 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkRight(int i, int j) {
        if (j == 0 || j == 99)
            return cellState.NOTHING;
        else if (nextGen[i][j + 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkBottomRight(int i, int j) {
        if ((i == 0 || i == 99) || (j == 0 || j == 99))
            return cellState.NOTHING;
        else if (nextGen[i + 1][j + 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkBottom(int i, int j) {
        if (i == 0 || i == 99)
            return cellState.NOTHING;
        else if (nextGen[i + 1][j] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkBottomLeft(int i, int j) {
        if ((i == 0 || i == 99) || (j == 0 || j == 99))
            return cellState.NOTHING;
        else if (nextGen[i + 1][j - 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkLeft(int i, int j) {
        if (j == 0 || j == 99)
            return cellState.NOTHING;
        else if (nextGen[i][j - 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }

    public static cellState checkTopLeft(int i, int j) {
        if ((i == 0 || i == 99) || (j == 0 || j == 99))
            return cellState.NOTHING;
        else if (nextGen[i - 1][j - 1] == cellState.LIVING)
            return cellState.LIVING;
        else {
            return cellState.DEAD;
        }
    }
}
