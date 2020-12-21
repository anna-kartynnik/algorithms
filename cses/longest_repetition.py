def longestRepetition(sequence):
    if len(sequence) == 0:
        return 0
    max_rep_length = 0
    last_seq_char = sequence[0]
    current_rep_length = 1
    for i in range(1, len(sequence)):
        if sequence[i] == sequence[i - 1]:
            current_rep_length += 1
        else:
            if current_rep_length > max_rep_length:
                max_rep_length = current_rep_length
            last_seq_char = sequence[i]
            current_rep_length = 1
    
    return max_rep_length if max_rep_length > current_rep_length else current_rep_length
    

def main():
    seq = input()
    print(longestRepetition(seq))

if __name__ == "__main__":
    main()