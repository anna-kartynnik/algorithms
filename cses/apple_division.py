def appleDivision(n, weights): 
    min_diff = float("inf")
    
    def appleGroups(i, group1_weight, group2_weight):
        nonlocal min_diff
        if i == n:
            diff = abs(group1_weight - group2_weight)
            if min_diff > diff:
                min_diff = diff
            return
        appleGroups(i + 1, group1_weight + weights[i], group2_weight)
        appleGroups(i + 1, group1_weight, group2_weight + weights[i])
    
    appleGroups(0, 0, 0)
    return min_diff
    
def main():
    n = int(input())
    print(appleDivision(n, [int(weight) for weight in input().split()]))

if __name__ == "__main__":
    main()