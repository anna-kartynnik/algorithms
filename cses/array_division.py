import sys, os


tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
k = int(tokens[1])
a = list(map(int, tokens[2:n + 2]))


total_sum = 0
for item in a:
	total_sum += item

def is_sum_ok(sub_sum):
	current_sum = 0
	number_of_subarrays = 0
	for i in range(n):
		item = a[i]
		#print(f"item {item}")
		if item > sub_sum:
			return False
		current_sum += item

		while current_sum >= sub_sum:
			number_of_subarrays += 1
			if current_sum == sub_sum:
				current_sum = 0
			else:
				current_sum = item
		if i == n - 1 and current_sum > 0:
			number_of_subarrays += 1 
		#print(f"current_sum: {current_sum}, number_of_subarrays: {number_of_subarrays}")
	#print(f"sub_sum {sub_sum}, number_of_subarrays {number_of_subarrays}")
	return number_of_subarrays <= k


left = 0
right = total_sum

while right - left > 1:
	middle = left + (right - left) // 2
	if is_sum_ok(middle):
		right = middle
	else:
		left = middle

result = right


sys.stdout.write(str(result))
sys.stdout.write("\n")