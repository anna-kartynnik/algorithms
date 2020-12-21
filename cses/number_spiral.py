def numberSpiral(y, x):
    k = y if y > x else x
    k_1_sq = (k - 1) * (k - 1)
    if (k_1_sq + 1) % 2 == 0:
        if x == k:
            return k_1_sq + y
        else:
            return k_1_sq + 2 * k - x
    else:
        if x == k:
            return k_1_sq + 2 * k - y
        else:
            return k_1_sq + x
    
def main():
    t = int(input())
    tests = []
    for i in range(t):
        y, x = input().split(" ")
        print(numberSpiral(int(y), int(x)))

if __name__ == "__main__":
    main()