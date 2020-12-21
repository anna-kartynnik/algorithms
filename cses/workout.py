# import bisect

# def split_max_difference(session_times, additional_sessions):
# 	differences = []
# 	for i in range(1, len(session_times)):
# 		differences.append(session_times[i] - session_times[i - 1])
	
# 	max_difference = 0
# 	max_difference_prev = 0

# 	for difference in differences:
# 		if difference > max_difference:
# 			max_difference_prev = max_difference
# 			max_difference = difference

# 	half1 = max_difference // 2
# 	half2 = max_difference - half1
# 	if half2 > max_difference_prev:
# 		return half2
# 	else:
# 		return max_difference_prev


# def sum_k(differences, d_optimal):
# 	sum_ = 0
# 	for difference in differences:
# 		sum_ += difference // d_optimal

# 	return sum_


# def get_optimal_difference(session_times, additional_sessions):
# 	differences = []
# 	for i in range(1, len(session_times)):
# 		differences.append(session_times[i] - session_times[i - 1])
	
# 	max_difference = 0

# 	for difference in differences:
# 		if difference > max_difference:
# 			max_difference = difference

# 	left = 1
# 	right = max_difference + 1

# 	while right - left > 1:
# 		middle = left + (right - left) // 2
# 		sum_ = sum_k(differences, middle)
# 		if sum_ < additional_sessions:
# 			right = middle
# 		else:
# 			left = middle
# 	return left


# number_of_tests = int(input())
# for test_index in range(number_of_tests):
#     number_of_sessions, additional_sessions = map(int, input().split())
#     session_times = list(map(int, input().split()))
#     print("Case #" + str(test_index + 1) + ": " + str(split_max_difference(session_times, additional_sessions) if additional_sessions == 1 else get_optimal_difference(session_times, additional_sessions)))

# def find_max_arithmetic_array_length(arr):
# 	diffs = []
# 	for i in range(1, len(arr)):
# 		diffs.append(arr[i] - arr[i - 1])
		
# 	max_diff_length = 1
# 	diff_index = 0
# 	for i in range(1, len(diffs)):
# 		if diffs[i] == diffs[i - 1]:
# 			#print(diffs[i])
# 			current_diff = i - diff_index + 1
# 			if current_diff > max_diff_length:
# 				max_diff_length = current_diff
# 		else:
# 			#print("2")
# 			diff_index = i
			
# 	return max_diff_length + 1

# number_of_tests = int(input())
# for test_index in range(number_of_tests):
# 	size = int(input())
# 	arr = list(map(int, input().split()))
# 	print("Case #" + str(test_index + 1) + ": " + str(find_max_arithmetic_array_length(arr)))

def generate_heights(n, a, b, c):
	if n != 1 and a == 1 and b == 1 and c == 1:
		return "IMPOSSIBLE"
	if a + b - c > n:
		return "IMPOSSIBLE"
	left_height = n - 1
	right_height = n - 1
	number_of_max_heights = c
	result = [0] * n
	left_index = 0
	right_index = n - 1

	if a - c == 0:
		result[0] = n
		number_of_max_heights -= 1
		left_index = 1
	else:
		for i in range(a - c):
			result[i] = left_height
		left_index = a - c
	if b - c == 0:
		result[-1] = n
		number_of_max_heights -= 1
		right_index -= 1
	else:	
		for i in range(b - c):
			if result[n - 1 - i] != 0:
				return "IMPOSSIBLE"
			result[n - 1 - i] = right_height
		right_index = n - 1 - (b - c)

	for i in range(left_index, right_index + 1):
		if result[i] != 0:
			return "IMPOSSIBLE"
		if number_of_max_heights > 0:
			result[i] = n
			number_of_max_heights -= 1
		else:
			result[i] = 1

	return " ".join(map(str, result))
	

number_of_tests = int(input())
for test_index in range(number_of_tests):
	n, a, b, c = map(int, input().split())
	print("Case #" + str(test_index + 1) + ": " + generate_heights(n, a, b, c))
