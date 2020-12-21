import sys, os
import time

#tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = 5000 #int(tokens[0])
x = 4 #int(tokens[1])

a = []
for i in range(n):
	value = 1 #2 if i in (714, 3518, 4240) else 1
	a.append(value) #int(tokens[i + 2]), i + 1)) # we need 1-based indexes
	#a.append((int(tokens[i + 2]), i + 1)) # we need 1-based indexes

time1 = time.time()

two_sum_finder = dict()
for i in range(n):
	for j in range(i + 1, n):
		two_sum_finder[a[i] + a[j]] = (i, j)

result = None
for i in range(n - 2):
	two_sum = x - a[i]
	if two_sum in two_sum_finder:
		two_sum_i, two_sum_j = two_sum_finder[two_sum]
		if two_sum_i > i:
			result = str(i + 1) + " " + str(two_sum_i + 1) + " " + str(two_sum_j + 1)
			break
	if result:
		break

sys.stdout.write(str(result) if result else "IMPOSSIBLE")
sys.stdout.write("\n")
sys.stdout.write(str(time.time() - time1))
sys.stdout.write("\n")