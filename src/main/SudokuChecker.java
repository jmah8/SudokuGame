package main;

public class SudokuChecker {
    private final SudokuBoard unsolvedSudokuBoard;

    public SudokuChecker(UnsolvedSudokuBoard unsolvedSudokuBoard) {
        this.unsolvedSudokuBoard = unsolvedSudokuBoard;
    }

    // EFFECT: returns true if index is empty, and if column and row don't have same number,
    //         and if grid doesn't contain same number, else false otherwise
    public boolean checkValidInsertion(Integer index, Integer number) {
        return (checkValidColumn(index, number) && checkValidRow(index, number)
                && checkValidIndex(index) && checkValidGrid(index, number));
    }

    // EFFECT: returns true if row doesn't have same number, false otherwise
    public boolean checkValidRow(Integer index, Integer number) {
        int rowNumber = (index / 9) * 9;
        for (int i = 0; i < 9; i++) {
            if (number == unsolvedSudokuBoard.getBoard().get(rowNumber)) {
                return false;
            }
            rowNumber++;
        }
        return true;
    }

    // EFFECT: returns true if all rows doesn't have same number, false otherwise
    public boolean checkValidRow() {
        int rowNumber = 0;
        for (int x = 0; x < 81; x++) {
            rowNumber = (x / 9) * 9;
            for (int y = 0; y < 9; y++) {
                if (x == rowNumber + y) {

                } else if (unsolvedSudokuBoard.getBoard().get(x) == unsolvedSudokuBoard.getBoard().get(rowNumber)) {
                    return false;
                }
                rowNumber++;
            }
        }
        return true;
    }

    // EFFECT: returns true if column doesn't have same number, false otherwise
    public boolean checkValidColumn(Integer index, Integer number) {
        int columnNumber = index % 9;
        for (int i = 0; i < 9; i++) {
            if (number == unsolvedSudokuBoard.getBoard().get(columnNumber)) {
                return false;
            }
            columnNumber = columnNumber + 9;
        }
        return true;
    }

    // EFFECT: returns true if all column doesn't have same number, false otherwise
    public boolean checkValidColumn() {
        int columnNumber = 0;
        for (int x = 0; x < 81; x++) {
            columnNumber = x % 9;
            for (int y = 0; y < 9; y++) {
                if (x == columnNumber + y) {

                } else if (unsolvedSudokuBoard.getBoard().get(x) == unsolvedSudokuBoard.getBoard().get(columnNumber)) {
                    return false;
                }
                columnNumber = columnNumber + 9;
            }
        }
        return true;
    }

    // EFFECT: returns true if number is not in grid (3x3), return false
    public boolean checkValidGrid(Integer index, Integer number) {
        // Finds which part of the grid is it on (top = 0, middle = 1, bottom = 2)
        int columnNumber = (index / 9) % 3;
        // Makes number always on the top of grid
        int indexNumberTopPartOfGrid = index - (9 * columnNumber);
        // Makes number always on the top and to the left of grid
        int indexNumberTopLeftGrid = (indexNumberTopPartOfGrid / 3) * 3;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (number == unsolvedSudokuBoard.getBoard().get(indexNumberTopLeftGrid)) {
                    return false;
                }
                indexNumberTopLeftGrid++;
            }
            indexNumberTopLeftGrid += 6;
        }
        return true;
    }

    // EFFECT: returns true if index is 0 (signifies empty)
    public boolean checkValidIndex(Integer index) {
        return 0 == unsolvedSudokuBoard.getBoard().get(index);
    }

    // EFFECT: returns true if board is complete and valid, false otherwise
    // TODO: fix bug where since i need the numbers to psuedo insert,
    //  it will always return false since the same number already exist
    public boolean checkIfValidAndCompleteBoard() {
        boolean solved = true;
        for (int i = 0; i < 81; i++) {
            Integer numberAtIndex = unsolvedSudokuBoard.getBoard().get(i);
            Integer indexNumber = i;
            if (checkValidColumn(indexNumber, numberAtIndex) && checkValidRow(indexNumber,
                    numberAtIndex) && checkValidGrid(indexNumber, numberAtIndex) && !checkValidIndex(indexNumber)) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }
}