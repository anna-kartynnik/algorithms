import collections

def palindromeReorder(s):
    letters = collections.defaultdict(int)
    for ch in s:
        letters[ch] += 1
    odd_ch = ""
    palindrome_half = ""
    for ch in letters:
        if letters[ch] % 2 == 1:
            if odd_ch:
                return "NO SOLUTION"
            else:
                odd_ch = ch
        else:
            for i in range(letters[ch] // 2):
                palindrome_half += ch

    return f"{palindrome_half}{''.join([odd_ch for i in range(letters[odd_ch])]) if odd_ch else ''}{palindrome_half[::-1]}"
        
    
def main():
    s = input()
    print(palindromeReorder(s))

if __name__ == "__main__":
    main()