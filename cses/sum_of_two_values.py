# Sum of two values
def sumOfTwoValues(a, n, x):
    finder = {}
    for i in range(n):
        finder[a[i]] = i
    for i in range(n):
        if x - a[i] in finder and finder[x - a[i]] != i:
            return f"{i + 1} {finder[x - a[i]] + 1}" # indexes are 1-based
    return "IMPOSSIBLE"
    
n, x = map(int, input().split())
a = list(map(int, input().split()))
print(sumOfTwoValues(a, n, x))