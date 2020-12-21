import sys, os

# Tasks and deadlines

tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
tasks = []
for i in range(n):
	duration, deadline = map(int, tokens[2 * i + 1:2* i + 3])
	tasks.append((duration, deadline))

tasks.sort()

reward = 0
current_time = 0
for task in tasks:
	current_time += task[0] # duration
	reward += task[1] - current_time # deadline - time


sys.stdout.write(str(reward))
sys.stdout.write("\n")