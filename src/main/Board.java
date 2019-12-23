package main;

import java.util.LinkedList;
import java.util.List;

public class Board {

    private List<Integer> board;

    public Board() {
        board = new LinkedList<>();
        fillWithZeros();
    }

    public List<Integer> getBoard() {
        return board;
    }

    public Integer sizeOfBoard() {
        return board.size();
    }

    // EFFECT: Fill board with 81 0s
    public void fillWithZeros() {
        for (int i = 0; i < 81; i++) {
            board.add(0);
        }
    }

    // MODIFIES: this
    // EFFECT: puts number into position index if checkValidInsertion is true, else do nothing
    // Don't let user use this
    public void setNumber(Integer index, Integer number) {
        //if (checkValidInsertion(index, number)) {
        board.set(index, number);
        //}
    }

    // MODIFIES : this
    // EFFECT: increments number in index by one. if number > 9, loop back to 0
    // This is the one user will use
    public void incrementNumber(Integer index) {
        int number = board.get(index);
        number++;
        if (number > 9) {
            number = 0;
        }
        board.set(index, number);
    }

    public Integer getNumberAtIndex(Integer index) {
        return board.get(index);
    }

    public boolean containsNumber(Integer number) {
        return board.contains(number);
    }

    // MODIFIES : this
    // EFFECT: sets the value at index to 0
    public void makeIndexEmpty(Integer index) {
        board.set(index, 0);
    }

    // EFFECT: prints board
    public void printBoard() {
        for (int i = 0; i < 81; i++) {
            if (i % 9 == 0 && !(i == 0)) {
                System.out.println("");
            }
            System.out.print(board.get(i) + " ");
        }
        System.out.println("");
    }

//    // MODIFIES: this
//    // EFFECT: randomly fills board with random amount of numbers
//    public void fillBoardRandomly() {
//        int numbersToPlace = difficultyLevelValue;
//        for (int i = 0; i < numbersToPlace;) {
//            int indexToPlace = random.nextInt(81);
//            int numberToPlace = random.nextInt(9) + 1;
//            boolean validInsertion = sudokuChecker.checkValidInsertion(indexToPlace, numberToPlace);
//            if (validInsertion) {
//                board.setNumber(indexToPlace, numberToPlace);
//                i++;
//            }
//        }
//    }
}
