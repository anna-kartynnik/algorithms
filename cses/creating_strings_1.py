import collections

chars = [chr(code) for code in range(97, 123)]

def getPermutations(prefix, left_letters_counter, left_length, result_collector):
    for ch in chars:
        if left_letters_counter[ch] == 0:
            continue
        if left_length == 1:
            result_collector.append(prefix + ch)
            continue
        left_letters_counter[ch] -= 1
        getPermutations(prefix + ch, left_letters_counter, left_length - 1, result_collector)
        left_letters_counter[ch] += 1    

def creatingStrings1(s):
    letters_counter = collections.Counter(s)
    result_collector = []
    getPermutations("", letters_counter, len(s), result_collector)
    return result_collector
    
def main():
    s = input()
    result_strs = creatingStrings1(s)
    print(len(result_strs))
    for result_str in result_strs:
        print(result_str)

if __name__ == "__main__":
    main()