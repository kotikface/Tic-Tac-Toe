package com.kotikface;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        char[] inputString = new char[9];
        Arrays.fill(inputString, ' ');
        char[][] arr = toArray(inputString);
        printTicTacToeField(arr);

        boolean winX;
        boolean winO;
        while (true){
            System.out.print("Enter the coordinates:");
            String xS=scanner.next();
            String yS = scanner.next();
            try {
                int x = Integer.parseInt(xS);
                int y = Integer.parseInt(yS);
                if((x<1 || x>3) || (y<1|| y>3)){
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }else {

                    if(y==1)
                        y=2;
                    else if(y==2)
                        y=1;
                    else
                        y=0;
                    x--;
                    if(arr[y][x]=='X' || arr[y][x]=='O'){
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }
                    else if(arr[y][x]==' '|| arr[y][x]=='_')
                        arr[y][x]='X';
                }

            }
            catch (Exception e){
                System.out.println("You should enter numbers!");
                continue;
            }
            int countX = 0;
            int countO = 0;
            printTicTacToeField(arr);
            winX = isXWin(arr);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (arr[i][j] == 'X')
                        countX++;
                    if (arr[i][j] == 'O')
                        countO++;
                }
            }
            if (!winX  && Math.abs(countX - countO) <= 1 && countO + countX == 9) {
                System.out.println("Draw");
                break;
            } else if (winX  && Math.abs(countX - countO) <= 1) {
                System.out.println("X wins");
                break;
            }

            int countXO;
            int countOO;
            while (true) {

                System.out.print("Enter the coordinates:");
                String xSO = scanner.next();
                String ySO = scanner.next();
                countXO=0;
                countOO=0;
                try {
                    int x1 = Integer.parseInt(xSO);
                    int y1 = Integer.parseInt(ySO);
                    if ((x1 < 1 || x1 > 3) || (y1 < 1 || y1 > 3)) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    } else {

                        if (y1 == 1)
                            y1 = 2;
                        else if (y1 == 2)
                            y1 = 1;
                        else
                            y1 = 0;
                        x1--;
                        if (arr[y1][x1] == 'X' || arr[y1][x1] == 'O') {
                            System.out.println("This cell is occupied! Choose another one!");
                            continue;
                        } else if (arr[y1][x1] == ' ' || arr[y1][x1] == '_')
                            arr[y1][x1] = 'O';
                    }

                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }
                printTicTacToeField(arr);
                winO = isOWin(arr);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (arr[i][j] == 'X')
                            countXO++;
                        if (arr[i][j] == 'O')
                            countOO++;
                    }
                }
                break;
            }
            if (!winO && Math.abs(countXO - countOO) <= 1 && countOO + countXO == 9) {
                System.out.println("Draw");
                break;
            } else if(winO && Math.abs(countXO - countOO) <= 1){
                System.out.println("O wins");
                break;
            }

        }
    }

    public static void printTicTacToeField(char[][] arr) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public  static  char[][]  toArray(char[] symbols){
        char[][] arr = new char[3][3];
        int placeChar=0;
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j]=symbols[placeChar];
                placeChar++;
            }
        }
        return arr;
    }
    public static  boolean isXWin(char[][] arr){
        int countCount=0;
        int countRow=0;
        int countDiag1=0;
        int countDiag2=0;
        if(arr[0][0]=='X'){
            for (int i = 0; i < 3; i++) {
                for (int j = i; j < i+1; j++) {
                    if(arr[i][j]=='X'){
                        countDiag1++;
                        if (countDiag1==3){
                            return true;}
                    }
                }

            }
        }
        if(arr[2][0]=='X') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 2-i; j < (2-i)+1; j++) {

                    if (arr[i][j] == 'X') {
                        countDiag2++;
                        if (countDiag2 == 3){
                            return true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[0][j]=='X'){
                    for (int k = 0; k <3 ; k++) {
                        if(arr[k][j]=='X'){
                            countRow++;
                            if (countRow==3){

                                return true;}
                        }
                    }
                    countRow=0;
                }

                if(arr[i][0]=='X'){
                    for (int k = 0; k <3 ; k++) {
                        if(arr[i][k]=='X'){
                            countCount++;
                            if(countCount==3){

                                return true;
                            }

                        }
                    }
                    countCount=0;
                }
            }
        }
        return false;
    }
    public static  boolean isOWin(char[][] arr){
        int countCount=0;
        int countRow=0;
        int countDiag1=0;
        int countDiag2=0;
        if(arr[0][0]=='O'){
            for (int i = 0; i < 3; i++) {
                for (int j = i; j < i+1; j++) {
                    if(arr[i][j]=='O'){
                        countDiag1++;
                        if (countDiag1==3)
                            return true;
                    }
                }

            }
        }
        if(arr[2][0]=='O') {
            for (int i = 2; i >= 0; i--) {
                for (int j = 2-i; j < 2-i+1; j++) {
                    if (arr[i][j] == 'O') {
                        countDiag2++;
                        if (countDiag2 == 3)
                            return true;
                    }
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[0][j]=='O'){
                    for (int k = 0; k <3 ; k++) {
                        if(arr[k][j]=='O'){
                            countRow++;
                            if (countRow==3)
                                return true;
                        }
                    }
                    countRow=0;
                }

                if(arr[i][0]=='O'){
                    for (int k = 0; k <3 ; k++) {
                        if(arr[i][k]=='O'){
                            countCount++;
                            if(countCount==3)
                                return true;
                        }
                    }
                    countCount=0;
                }
            }
        }
        return false;
    }

}