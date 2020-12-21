import bisect
import sys, os

# Concert tickets
#sys.setrecursionlimit(10 ** 6)
tokens = os.read(sys.stdin.fileno(), 10 ** 8).split()
n, m = int(tokens[0]), int(tokens[1])
prices = map(int, tokens[2 : n + 2])
offers = map(int, tokens[n + 2 : n + m + 2])
prices.sort()

result = []
for offer_index in range(len(offers)):
    if len(prices) == 0:
        result.append(-1)
        continue
    price_index = bisect.bisect_right(prices, offers[offer_index])
    if price_index:
        price_index = price_index - 1
    if prices[price_index] <= offers[offer_index]:
        result.append(prices[price_index])
        del prices[price_index]
    else:
        result.append(-1)

print("\n".join([str(_) for _ in result]))