package main;

public class SudokuSolver {
    SudokuBoard sudokuBoard;
    SudokuChecker sudokuChecker;

    public SudokuSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        sudokuChecker = new SudokuChecker(this.sudokuBoard);
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void changeSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    // EFFECT: returns true if board is completely filled, false otherwise
    public boolean isFilled() {
        boolean complete = true;
        for (int i = 0; i < sudokuBoard.getBoard().sizeOfBoard(); i++) {
            if (sudokuBoard.getBoard().getNumberAtIndex(i) == 0) {
                complete = false;
            }
        }
        return complete;
    }

    // EFFECT: returns the next empty index
    public int findNextEmptyIndex() {
        int emptyIndex = 0;
        for (int i = 0; i < sudokuBoard.getBoard().sizeOfBoard(); i++) {
            int number = sudokuBoard.getBoard().getNumberAtIndex(i);
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
        // Don't need to check if valid board at this point, because we are using backtracking
        // to find the correct solution using checkValidInsertion
        if (sudokuChecker.checkValidBoard() && isFilled()) {
            return true;
        }
        int index = findNextEmptyIndex();
        for (int num = 1; num <= 9; num++) {
            if (sudokuBoard.checkValidInsertion(index, num)) {
                sudokuBoard.addNumber(index, num);
                if (fillSudokuBoard()) {
                    return true;
                } else {
                    sudokuBoard.addNumber(index, 0);
                }
            }
        }
        return false;
    }

    // EFFECT: prints board
    public void printBoard() {
        sudokuBoard.printBoard();
    }
}
