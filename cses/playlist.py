# Playlist
n = int(input())
songs = list(map(int, input().split()))
song_last_index = {}
last_duplicate_index = -1
max_distinct_songs = 0
for i in range(n):
    if songs[i] in song_last_index:
        if last_duplicate_index < song_last_index[songs[i]]:
            last_duplicate_index = song_last_index[songs[i]]
    if i - last_duplicate_index > max_distinct_songs:
        max_distinct_songs = i - last_duplicate_index
    song_last_index[songs[i]] = i
print(max_distinct_songs)