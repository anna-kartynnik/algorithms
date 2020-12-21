def twoKnights(n):
    prev_k_knight_positions = 0
    if n >= 1:
        print(0)
    if n >= 2:
        print(6)
        prev_k_knight_positions = 6
    if n >= 3:
        print(28)
        prev_k_knight_positions = 28
    for k in range(4, n + 1):
        knight_positions = (2 * k - 1) * (k * k - k) - 8 * (k - 4) - 16
        prev_k_knight_positions += knight_positions
        print(f"{prev_k_knight_positions}")
    
def main():
    n = int(input())
    twoKnights(n)

if __name__ == "__main__":
    main()