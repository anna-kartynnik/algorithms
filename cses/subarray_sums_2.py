import sys, os
import collections

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
x = int(tokens[1])

a = list(map(int, tokens[2:n + 2]))

sum_founder = collections.defaultdict(list)
sum_ = 0
for i in range(n):
	sum_ += a[i]
	sum_founder[sum_].append(i)

def find_sums_j(value, right_i):
	left = 0
	right = len(sum_founder[value])
	while right - left > 1:
		middle = left + (right - left) // 2
		if sum_founder[value][middle] > right_i:
			right = middle
		else:
			left = middle
	return left + (1 if len(sum_founder[value]) > 0 and sum_founder[value][left] < right_i  else 0) # left - 0 = number of indexes # if sum_founder[value][left] == value else -1


number_of_subarrays = 0
sum_ = 0
for i in range(n):
	sum_ += a[i]
	if sum_ == x:
		number_of_subarrays += 1
	# sums[i] - sums[j] = x => sums[j] = sums[i] - x
	number_of_subarrays += find_sums_j(sum_ - x, i)

sys.stdout.write(str(number_of_subarrays))
sys.stdout.write("\n")
