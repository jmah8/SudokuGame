package main;

public class SudokuSolver {
    UnsolvedSudokuBoard sb;

    public SudokuSolver(UnsolvedSudokuBoard sudokuBoard) {
        sb = sudokuBoard;
    }

    public UnsolvedSudokuBoard getSudokuBoard() {
        return sb;
    }

    public void changeSudokuBoard(UnsolvedSudokuBoard sudokuBoard) {
        sb = sudokuBoard;
    }

    // EFFECT: returns true if board is completely filled, false otherwise
    public boolean isComplete() {
        boolean complete = true;
        for (int i = 0; i < sb.getBoard().size(); i++) {
            if (sb.getBoard().get(i) == 0) {
                complete = false;
            }
        }
        return complete;
    }

    // EFFECT: returns the next empty index
    public int findNextEmptyIndex() {
        int emptyIndex = 0;
        for (int i = 0; i < sb.getBoard().size(); i++) {
            int number = sb.getBoard().get(i);
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
        if (isComplete()) {
            return true;
        }
        int index = findNextEmptyIndex();
        for (int num = 1; num <= 9; num++) {
            if (sb.checkValidInsertion(index, num)) {
                sb.setNumber(index, num);
                if (fillSudokuBoard()) {
                    return true;
                } else {
                    sb.setNumber(index, 0);
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
