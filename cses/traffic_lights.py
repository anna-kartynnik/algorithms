# Traffic lights
street_length, number_of_tr_lights = map(int, input().split())
tr_positions = list(map(int, input().split()))

class Node:
    def __init__(self, value):
        self.value = value
        self.next = None

distances = []
max_distance = 0
head = Node(0)
node = head
position_finder = {}
sorted_positions = list(tr_positions)
sorted_positions.sort()
for position in sorted_positions:
    if position - node.value > max_distance:
        max_distance = position - node.value
    position_finder[position] = node
    next_ = Node(position)
    node.next = next_
    node = node.next
if street_length - node.value > max_distance:
    max_distance = street_length - node.value
node.next = Node(street_length)

distances.append(max_distance)
    
for i in range(len(tr_positions) - 1, -1, -1):
    current_tr_light = tr_positions[i]
    node = position_finder[current_tr_light]
    node.next = node.next.next
    position_finder[node.next.value] = node
    if node.next.value - node.value > max_distance:
        max_distance = node.next.value - node.value
    if i != 0:
        # we need to return the distance after traffic addition only 
        distances.append(max_distance)
    
distances.reverse()
print(" ".join(map(str, distances)))