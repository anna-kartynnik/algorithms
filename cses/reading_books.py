import sys, os


tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
reading_times = list(map(int, tokens[1:n + 1]))

if n == 1:
	sys.stdout.write(str(reading_times[0] * 2))
	sys.stdout.write("\n")
else:
	reading_times.sort(reverse=True)

	person1 = [reading_times[0]]
	person2 = [reading_times[1]]
	first_book_taken = True
	for i in range(2, n):
		if person1[-1] < person2[-1]:
			person1.append(person1[-1] + reading_times[i])
			first_book_taken = False
			#print("1: " + str(reading_times[i]) + " " + str(person1[-1]))
		else:
			person2.append(person2[-1] + reading_times[i])
			#print("2: " + str(reading_times[i]) + " " + str(person2[-1]))
	#print(str(person1[-1]))
	#print(str(person2[-1]))
	total_time = person2[-1]
	if not first_book_taken:
		total_time += person1[-1]
	else:
		total_time = reading_times[0] * 2
	sys.stdout.write(str(total_time))
	sys.stdout.write("\n")	
