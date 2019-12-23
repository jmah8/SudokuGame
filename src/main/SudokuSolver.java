package main;

public class SudokuSolver {
    SudokuBoard sb;

    public SudokuSolver(SudokuBoard sudokuBoard) {
        sb = sudokuBoard;
    }

    public SudokuBoard getSudokuBoard() {
        return sb;
    }

    public void changeSudokuBoard(SudokuBoard sudokuBoard) {
        sb = sudokuBoard;
    }

    // EFFECT: returns true if board is completely filled, false otherwise
    public boolean isFilled() {
        boolean complete = true;
        for (int i = 0; i < sb.getBoard().sizeOfBoard(); i++) {
            if (sb.getBoard().getNumberAtIndex(i) == 0) {
                complete = false;
            }
        }
        return complete;
    }

    // EFFECT: returns the next empty index
    public int findNextEmptyIndex() {
        int emptyIndex = 0;
        for (int i = 0; i < sb.getBoard().sizeOfBoard(); i++) {
            int number = sb.getBoard().getNumberAtIndex(i);
            if (number == 0) {
                emptyIndex = i;
                break;
            }
        }
        return emptyIndex;
    }

//    public boolean generateValidNumber(Integer index) {
//        for (int i = 1; i < 10; i++) {
//            boolean valid = sb.checkValidInsertion(index, i);
//            if (valid) {
//                sb.addNumber(index, i);
//                return true;
//            }
//        }
//        return false;
//    }

//    public boolean solveOneIndex() {
//        int emptyIndex = findNextEmptyIndex();
//        boolean validNumberPossible = generateValidNumber(emptyIndex);
//        return validNumberPossible;
//    }

    // MODIFIES: this
    // EFFECT: fills board recursively and uses backtracking to find solution
    //         returning true if board is solvable, false otherwise
    public boolean fillSudokuBoard() {
        if (isFilled()) {
            return true;
        }
        int index = findNextEmptyIndex();
        for (int num = 1; num <= 9; num++) {
            if (sb.checkValidInsertion(index, num)) {
                sb.addNumber(index, num);
                if (fillSudokuBoard()) {
                    return true;
                } else {
                    sb.addNumber(index, 0);
                }
            }
        }
        return false;
    }

    // EFFECT: prints board
    public void printBoard() {
        sb.printBoard();
    }
}
