import sys, os
 
 
tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n = int(tokens[0])
k = int(tokens[1])
a = list(map(int, tokens[2:n + 2]))



sys.stdout.write(str(result))
sys.stdout.write("\n")




5 8
########
#..#...#
####.#.#
#..#...#
########