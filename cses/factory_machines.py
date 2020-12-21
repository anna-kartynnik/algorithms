import sys, os

# Factory machines
tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
t = int(tokens[1])
times = list(map(int, tokens[2:n + 2]))

def canProduceForTime(x):
	total_products = 0
	for i in range(n):
		total_products += x // times[i]
		if total_products >= t:
			break
	return total_products >= t

left = 0 # the time for which we CAN'T produce t products in any conditions
right = min(times) * t # the time for which we can produce t products for sure

while right - left > 1:
	middle = left + (right - left) // 2
	if canProduceForTime(middle):
		right = middle
	else:
		left = middle

sys.stdout.write(str(right))
sys.stdout.write("\n")