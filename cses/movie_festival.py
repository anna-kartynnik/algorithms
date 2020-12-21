# Movie festival
n = int(input())
movie_times = [None] * n
for i in range(n):
    movie_times[i] = list(map(int, input().split()))
    
def takeSecond(pair):
    return pair[1]

movie_times.sort(key=takeSecond)
number_of_movies = 0
prev_end_time = 0
for movie_time in movie_times:
    if movie_time[0] >= prev_end_time:
        number_of_movies += 1
        prev_end_time = movie_time[1]
print(number_of_movies)