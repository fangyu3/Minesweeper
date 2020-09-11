# Minesweeper

## The game has the following rules:

1. The user can mark (flag) some cells as cells that potentially have a mine. 
This is done by typing the word "mine" after entering the coordinates. i.e 5 5 mine

2. The user can also remove flags from cells. Simply type "mine" again after entering the coordinates of the flagged cell.

3. The user can explore a cell by typing "free" after entering the coordinates. i.e 5 5 free

4. Win condition:
    a. user marks all the cells with potential mines (note that the user must mark all the mines, but no empty cells).
    b. user explores all safe cells, leaving only the cells with mines unexplored.

5. If user explores a cell with a mine, then he/she loses.
