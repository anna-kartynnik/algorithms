# Towers
n = int(input())
cubes = list(map(int, input().split()))

towers = []
towers.append(cubes[0])

def find_tower(cube):
    left = -1
    right = len(towers) - 1
    while right - left > 1:
        middle = left + (right - left) // 2
        if towers[middle] > cube:
            right = middle
        else:
            left = middle
    return right if towers[right] > cube else len(towers)
        

for i in range(1, n):
    tower_index = find_tower(cubes[i])
    if tower_index == len(towers):
        towers.append(cubes[i])
    else:
        towers[tower_index] = cubes[i]

print(len(towers))