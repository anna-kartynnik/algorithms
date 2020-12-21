# Ferris wheel
n, max_weight = [int(_) for _ in input().split()]
weights = [int(_) for _ in input().split()]
gondolas = []
weights.sort(reverse=True)

left_index = 0
right_index = len(weights) - 1
while left_index <= right_index:
    gondolas.append(weights[left_index])
    left_index += 1
    if left_index > right_index:
        break
    if gondolas[-1] < max_weight:
        if gondolas[-1] + weights[right_index] <= max_weight:
            gondolas[-1] += weights[right_index]
            right_index -= 1

print(len(gondolas))