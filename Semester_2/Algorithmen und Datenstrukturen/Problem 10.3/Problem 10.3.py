def power(x, n):
    if n == 0:
        return 1
    if n % 2:
        return x * power(x, n // 2) ** 2
    else:
        return power(x, n // 2) ** 2

#  P(n)   = P(n/2) + 1
#  P(1)   = 1

#  P(n)   = P(n/2) + 1
#  P(n/2) = P(n/4) + 1 + 1

#         = P(n/2**k) + k

#         = P(n/2**k)
#       1 = n/2**k
#       k = log2(n)

#         = P(1) + log2(n)

# --> O(log n)
