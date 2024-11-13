package com.example.sudoku.service;

import org.springframework.stereotype.Service;

@Service
public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public boolean solve(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {  // Find an empty cell
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;  // Reset on backtrack
                            }
                        }
                    }
                    return false;  // No valid number found
                }
            }
        }
        return true;  // Solved
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
