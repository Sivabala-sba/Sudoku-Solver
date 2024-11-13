package com.example.sudoku.controller;

import com.example.sudoku.service.SudokuSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sudoku")
@CrossOrigin(origins = "*")
public class SudokuController {

    @Autowired
    private SudokuSolver sudokuSolver;

    @PostMapping("/solve")
    public int[][] solveSudoku(@RequestBody int[][] grid) {
        if (sudokuSolver.solve(grid)) {
            return grid;
        } else {
            throw new IllegalArgumentException("Invalid Sudoku grid provided.");
        }
    }
}

