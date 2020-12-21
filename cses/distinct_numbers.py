def distinctNumbers(nums):
    distinct = set(nums)
    return len(distinct)

    
def main():
    n = int(input())
    nums = [int(i) for i in input().split()]
    print(distinctNumbers(nums))

if __name__ == "__main__":
    main()