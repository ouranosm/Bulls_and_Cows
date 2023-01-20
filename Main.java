package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] symbols = new char[36];
        char a = 'a';
        char one = '0';
        for (int i = 0; i < 10; i++) {
            symbols[i] = one;
            one++;
        }
        for (int i = 10; i < 36; i++) {
            symbols[i] = a;
            a++;
        }
        //String length = scanner.nextLine();
        int n = 0;
        String s = new String();
        System.out.println("Input the length of the secret code:");
        try {
            s = scanner.nextLine();
            n = Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println("Error: \"" + s + "\" isn't a valid number.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int k = Integer.parseInt(scanner.nextLine());
        //int k = scanner.nextInt();


        if (n > 36 || n == 0) {
            System.out.println("Error: can't generate a secret number with a length of 37 because there aren't enough unique digits.");
            System.exit(0);
        } else if ( k > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else if ( k < n) {
            System.out.println("Error: it's not possible to generate a code with a length of " + n + " with " + k  + " unique symbols.");
            System.exit(0);
        }
        String secretCode = createCode(n, k, symbols);
        System.out.println(secretCode);
        //System.out.println(secretCode.charAt(0));
        String displayCode = "";
        for (int i = 0; i < secretCode.length(); i++) {
            displayCode += '*';
        }
        if (k > 10) {
            displayCode += " (0-9, a-" + symbols[k - 1] + ").";
        } else {
            displayCode += " (0-" + symbols[k - 1] + ").";
        }

        System.out.println("The secret is prepared: " + displayCode);
        System.out.println("Okay, let's start a game!");
        boolean gameOver = false;
        int turn = 1;


        while (gameOver == false) {


            int cows = 0;
            int bulls = 0;

            System.out.println("Turn " + turn + ":");
            turn++;
            String code = scanner.nextLine();

            for (int i = 0; i < n; i++) {
                if (code.charAt(i) == secretCode.charAt(i)) {
                    bulls++;
                } else if (code.charAt(i) != secretCode.charAt(i)){
                    for (int j = 0; j < n; j++) {
                        if (j != i && code.charAt(i) == secretCode.charAt(j) && code.charAt(j) != secretCode.charAt(j)) {
                            cows++;
                        }
                    }
                }
            }
            if (bulls == n) {
                System.out.println("Grade: " + bulls + " bulls.");
                System.out.println("Congratulations! You guessed the secret code.");
                gameOver = true;
            } else if (cows > 1 && bulls > 1) {
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cows.");
            } else if (cows == 1 && bulls == 0 && cows < 2) {
                System.out.println("Grade: " + cows + " cow.");
            } else if (cows == 0 && bulls == 1 && bulls < 2) {
                System.out.println("Grade: " + bulls + " bull.");
            } else if (cows > 1 && bulls == 0) {
                System.out.println("Grade: " + cows + " cows.");
            } else if (cows == 0 && bulls > 1) {
                System.out.println("Grade: " + bulls + " bulls.");
            } else if (cows == 0 && bulls == 0) {
                System.out.println("Grade: None.");
            } else if (cows == 1 && bulls == 1) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cow.");
            } else if (cows > 1 && bulls == 1) {
                System.out.println("Grade: " + bulls + " bull and " + cows + " cows.");
            } else if (cows == 1 && bulls > 1) {
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cow.");
            }
        }

    }
    public static String createCode(int n, int k, char[] symbols) {
        Random r = new Random();
        boolean trueCode = false;
        String secretCode = new String();

        while (trueCode == false) {
            String code = new String();
            for (int i = 0; i < n; i++) {
                code += symbols[r.nextInt(k)];
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (code.charAt(i) == code.charAt(j) && i != j) {
                        count++;
                    }
                }
            }
            if (count == 0) {
                trueCode = true;
                secretCode = code;
            }

        }

        return secretCode;

    }
}
