def coinPiles(a, b):
    # 2i + j = a, 2j + i = b => j = a - 2i => 3i = 2a - b
    if (2 * a - b) % 3 == 0:
        i = (2 * a - b) // 3
        j = a - 2 * i
        if i >= 0 and j >= 0:
            return "YES"
    return "NO"
    
def main():
    t = int(input())
    tests = []
    for i in range(t):
        a, b = input().split(" ")
        print(coinPiles(int(a), int(b)))

if __name__ == "__main__":
    main()