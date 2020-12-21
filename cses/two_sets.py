# def twoSets(n):
#     if n % 4 != 0 and n % 4 != 3:
#         print("NO")
#     else:
#         part_sum = (n + 1) * n // 4
#         print("YES")
#         part1_number = 0
#         part1_elements = ""
#         part2_elements = ""
#         for i in range(n, 0, -1):
#             if i <= part_sum:
#                 part1_elements += str(i) + " "
#                 part1_number += 1
#                 part_sum -= i
#             else:
#                 part2_elements += str(i) + " "
#         print(part1_number)
#         print(part1_elements)
#         print(n - part1_number)
#         print(part2_elements)
def twoSets(n):
    if n % 4 != 0 and n % 4 != 3:
        print("NO")
    else:
        S = (n + 1) * n // 4
        print("YES")
        left = 0
        right = n + 1
        while right - left > 1:
            k = left + (right - left) // 2
            s = (2 * n - k) * (k + 1) // 2
            if s <= S:
                left = k
            else:
                right = k
        k = left
        remainder = S - (2 * n - k) * (k + 1) // 2
        # n, n - 1, n - 2, ..., n - k and remainder is the first set then 
        print(k + (2 if remainder > 0 else 1))
        print(" ".join([str(i) for i in range(n - k, n + 1)]) + (" " + str(remainder) if remainder > 0 else ""))
        print(n - k - (2 if remainder > 0 else 1))
        print(((" ".join([str(i) for i in range(1, remainder)]) + " ") if remainder > 1 else "") + " ".join([str(i) for i in range(remainder + 1, n - k)]))
        
    
def main():
    n = int(input())
    twoSets(n)

if __name__ == "__main__":
    main()