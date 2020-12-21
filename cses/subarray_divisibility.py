import sys, os
from collections import defaultdict


tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
a = list(map(int, tokens[1:n + 1]))
remainders = defaultdict(list)

sum_ = 0
for i in range(n):
	sum_ += a[i]
	remainders[sum_ % n].append(i)


def find_remainder_count(remainder, index):
	left = 0
	right = len(remainders[remainder])
	if right == 0:
		return 0
	while right - left > 1:
		middle = left + (right - left) // 2
		if remainders[remainder][middle] >= index:
			right = middle
		else:
			left = middle

	result = left + (1 if remainders[remainder][left] < index else 0)
	return result

number_of_subarrays = 0
sum_ = 0
for i in range(n):
	# Si - Sj = 0 mod n => Sj should have the same remainder as Si
	sum_ += a[i]
	if sum_ % n == 0:
		number_of_subarrays += 1

	number_of_subarrays += find_remainder_count(sum_ % n, i)


sys.stdout.write(str(number_of_subarrays))
sys.stdout.write("\n")