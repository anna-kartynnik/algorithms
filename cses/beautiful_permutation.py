def beautifulPermutation(n):
    if n == 1:
        return "1"
    if n < 4:
        return "NO SOLUTION"
    k = n // 2
    permutation = [str(x * 2) for x in range(1, k + 1)]
    permutation.extend([str(x * 2 + 1) for x in range(k + 1 if n % 2 == 1 else k)])
    return " ".join(permutation)
        

def main():
    n = int(input())
    print(beautifulPermutation(n))

if __name__ == "__main__":
    main()