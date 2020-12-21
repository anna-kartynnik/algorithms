def increasingArray(n, nums):
    number_of_increases = 0
    for i in range(1, len(nums)):
        if nums[i] < nums[i - 1]:
            number_of_increases += nums[i - 1] - nums[i]
            nums[i] = nums[i - 1]
    return number_of_increases
        

def main():
    n = int(input())
    nums = [int(x) for x in input().split(" ")]
    print(increasingArray(n, nums))

if __name__ == "__main__":
    main()