import sys, os

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])

items = list(map(int, tokens[1:n + 1]))
result = []
result.append(0)
for i in range(1, n):
	if items[i] == items[i - 1]:
		result.append(result[i - 1])
	elif items[i] > items[i - 1]:
		result.append(i) # i - 1 + 1, 1-based
	else:
		index = i - 1
		while index >= 0 and items[index] >= items[i]:
			index = result[index] - 1
		result.append(index + 1)


sys.stdout.write(" ".join(map(str, result)))
sys.stdout.write("\n")