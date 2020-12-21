import sys, os

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
x = int(tokens[1])

a = list(map(int, tokens[2:n + 2]))

sums = [0] * n
sums[0] = a[0]
for i in range(1, n):
	sums[i] = sums[i - 1] + a[i]

def find_sum(value, right):
	left = 0
	while right - left > 1:
		middle = left + (right - left) // 2
		if sums[middle] > value:
			right = middle
		else:
			left = middle
	return left if sums[left] == value else -1


number_of_subarrays = 0
for i in range(n):
	if sums[i] == x:
		number_of_subarrays += 1
		continue
	# sums[i] - sums[j] = x => sums[j] = sums[i] - x
	j = find_sum(sums[i] - x, i)
	if j != -1:
		number_of_subarrays += 1

sys.stdout.write(str(number_of_subarrays))
sys.stdout.write("\n")
