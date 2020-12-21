# Maximum subarray sum
n = int(input())
a = list(map(int, input().split()))

sum_ = a[0]
min_interim_sum = 0
max_sum = a[0]
for i in range(1, n):
    if min_interim_sum > sum_:
        min_interim_sum = sum_
    sum_ += a[i]

    if sum_ - min_interim_sum > max_sum:
        max_sum = sum_ - min_interim_sum
    
print(max_sum)