def missingNumber(n, nums):
    n_sum = (n + 1) * n // 2
    given_sum = 0
    for num in nums:
        given_sum += num
    return n_sum - given_sum
        

def main():
    n = int(input())
    nums = [int(x) for x in input().split(" ")]
    print(missingNumber(n, nums))

if __name__ == "__main__":
    main()