def trailingZeros(n):
    res = 0
    while n > 1:
        n = n // 5
        res += n
    return res
        
    
def main():
    n = int(input())
    print(trailingZeros(n))

if __name__ == "__main__":
    main()