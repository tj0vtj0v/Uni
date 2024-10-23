def power(x, n):
    if n == 0:                      # O(1)
        return 1                    # O(1)
    return x * power(x, n - 1)      # P(n-1)

# P(n)   = P(n-1) + 1
# P(0)   = 1

# P(n)   = P(n-1) + 1
# P(n-1) = P(n-2) + 1 + 1
# P(n-2) = P(n-3) + 1 + 1 + 1

#        = P(n-n) + n

#        = n - n = 0

#        = P(0) + n
#        =  1   + n

# --> O(n)
