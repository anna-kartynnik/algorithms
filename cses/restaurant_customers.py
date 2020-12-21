# Restaurant customers
n = int(input())
customer_times = [0] * (2 * n)
finder = {}
for i in range(n):
    in_, out = map(int, input().split())
    customer_times[i] = in_
    customer_times[n + i] = out
    finder[in_] = "in"
    finder[out] = "out"
customer_times.sort()
number_of_customers_together = 0
max_customers = 0
for customer_time in customer_times:
    if finder[customer_time] == "in":
        number_of_customers_together += 1
    else:
        number_of_customers_together -= 1
    if number_of_customers_together > max_customers:
        max_customers = number_of_customers_together
print(max_customers)