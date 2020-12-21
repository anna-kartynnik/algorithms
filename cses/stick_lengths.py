# Stick lengths
n = int(input())
lengths = list(map(int, input().split()))
lengths.sort()
average = lengths[n // 2]
number_of_changes = 0
for i in range(n):
    number_of_changes += abs(lengths[i] - average)
print(number_of_changes)