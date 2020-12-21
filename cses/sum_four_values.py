import sys, os

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
x = int(tokens[1])

a = list(map(int, tokens[2:n + 2]))

two_sum_finder = {}
for i in range(n):
	for j in range(i + 1, n):
		two_sum_finder[a[i] + a[j]] = (i, j)

result = None
for i in range(n):
	for j in range(i + 1, n):
		rest_sum = x - a[i] - a[j]
		if rest_sum in two_sum_finder:
			sum_left, sum_right = two_sum_finder[rest_sum]
			if sum_left > j:
				result = str(i + 1) + " " + str(j + 1) + " " + str(sum_left + 1) + " " + str(sum_right + 1) # answer is 1-based
				break
	if result is not None:
		break

sys.stdout.write(str(result) if result else "IMPOSSIBLE")
sys.stdout.write("\n")
