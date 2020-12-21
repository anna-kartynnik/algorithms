import copy

def chessBoardQueens(board):
    number_of_placements = 0

    def placeQueen(queen, board):
        nonlocal number_of_placements
        if queen == len(board):
            number_of_placements += 1
            return
        candidate_board = copy.deepcopy(board)
        for available_j in range(len(board[queen])):
            if board[queen][available_j] == 0:
                for i in range(len(board)):
                    candidate_board[i][available_j] = 1
                for j in range(len(board[queen])):
                    candidate_board[queen][j] = 1
                # diagonal
                i = 0
                while queen + i < len(board):
                    if available_j - i >= 0:
                        candidate_board[queen + i][available_j - i] = 1
                    if available_j + i < len(board[queen]):
                        candidate_board[queen + i][available_j + i] = 1
                    i += 1
                placeQueen(queen + 1, candidate_board)
                candidate_board = copy.deepcopy(board)
    
    placeQueen(0, board)
    return number_of_placements
    
def main():
    board = []
    for i in range(8):
        board.append([])
        for ch in input():
            board[i].append(0 if ch == '.' else 1)
    print(chessBoardQueens(board))

if __name__ == "__main__":
    main()