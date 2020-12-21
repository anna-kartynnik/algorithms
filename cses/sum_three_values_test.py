import sys, os
import time
import bisect
import collections

#tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = 5000 #int(tokens[0])
x = 4 #int(tokens[1])

a = []
a_finder = dict()
for i in range(n):
	value = 1 #2 if i in (714, 3518, 4240) else 1
	a.append((value, i + 1)) #int(tokens[i + 2]), i + 1)) # we need 1-based indexes
	#a.append((int(tokens[i + 2]), i + 1)) # we need 1-based indexes
	a_finder[a[i][0]] = i + 1

time1 = time.time()

a.sort()
items = [item[0] for item in a]
original_indexes = [item[1] for item in a]

def find_third(value, left):
	# if value in a_finder:
	# 	found = a_finder[value]
	# 	return found if found >= left else -1
	# else:
	# 	return -1

	return bisect.bisect_left(items, value, left)

	# right = len(items)
	# while right - left > 1:
	# 	middle = left + (right - left) // 2
	# 	if items[middle] == value:
	# 		return middle
	# 	elif items[middle] < value:
	# 		left = middle
	# 	else:
	# 		right = middle
	# if items[left] == value:
	# 	return left
	# elif left == n - 1:
	# 	return n
	# else:
	# 	return -1

result = None
for i in range(n - 2):
	for j in range(i + 1, n - 1):
		if items[i] + items[j] >= x:
			break
		third = x - items[i] - items[j]
		if third < items[j]:
			break 
		third_index = find_third(third, j + 1)
		#print(third_index)
		if third_index > -1 and third_index < n and items[third_index] == third:
			result = str(original_indexes[i]) + " " + str(original_indexes[j]) + " " + str(original_indexes[third_index])
			break
		# Without this `if`, it will take much longer!
		if third_index == n:
			break
	if result:
		break

sys.stdout.write(str(result) if result else "IMPOSSIBLE")
sys.stdout.write("\n")
sys.stdout.write(str(time.time() - time1))
sys.stdout.write("\n")