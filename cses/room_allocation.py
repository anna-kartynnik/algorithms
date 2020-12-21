import sys, os

# Room allocation

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
START_FLAG = 1
END_FLAG = 2 # start is less than end! for correct sorting
n = int(tokens[0])
times = []
for i in range(n):
    a, b = map(int, tokens[i * 2 + 1:i * 2 + 3])
    times.append((a, START_FLAG, (a, i)))
    times.append((b, END_FLAG, (a, i)))

times.sort()
number_of_rooms = 0
max_number_of_rooms = 0
for time_entry in times:
    time, flag, start_time = time_entry
    if flag == START_FLAG:
        number_of_rooms += 1
        if number_of_rooms > max_number_of_rooms:
            max_number_of_rooms = number_of_rooms
    else:
        number_of_rooms -= 1

occupied_rooms = {}
free_rooms = list(range(max_number_of_rooms))
room_allocation = [0] * n
for time_entry in times:
    time, flag, start_time_pair = time_entry
    if flag == START_FLAG:
        occupied_rooms[start_time_pair] = free_rooms.pop()
        room_allocation[start_time_pair[1]] = occupied_rooms[start_time_pair] + 1 # because our rooms are 0-based, but answer is 1-based
    else:
        free_rooms.append(occupied_rooms[start_time_pair])
        del occupied_rooms[start_time_pair]
        
#print(str(max_number_of_rooms))
#print(" ".join(map(str, room_allocation)))
sys.stdout.write(str(max_number_of_rooms) + "\n")
sys.stdout.write(" ".join(map(str, room_allocation)) + "\n");