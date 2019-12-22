package main;

import java.util.Random;

public class main {

    public static void main(String[] args) {
//        SudokuBoard sb = new SudokuBoard();
//        sb.fillBoardRandomly(35);
//        sb.printBoard();
//        SudokuSolver ss = new SudokuSolver(sb);
//        System.out.println(ss.fillSudokuBoard());
//        ss.printBoard();

        SolvableSudokuBoard ssb = new SolvableSudokuBoard(Difficulty.HARD);
    }
}
