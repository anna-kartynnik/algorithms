def bitStrings(n):
    bits_str = 2 ** n
    threshold = 10 ** 9 + 7
    if bits_str < threshold:
        print(bits_str)
    else:
        print(bits_str % threshold)
        
    
def main():
    n = int(input())
    bitStrings(n)

if __name__ == "__main__":
    main()