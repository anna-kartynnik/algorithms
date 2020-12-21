def weirdAlgorithm(n):
    k = n
    yield k
    while k > 1:
        if k % 2 == 0:
            k //= 2
        else:
            k = k * 3 + 1
        yield k
        

def main():
    n = int(input())
    for number in weirdAlgorithm(n):
        print(number, end=" ")

if __name__ == "__main__":
    main()