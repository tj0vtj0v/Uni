def find_ten_largest(s: list[int]) -> list[int]:
    if len(s) <= 10:            # O(1)
        return s                # O(1)
    while len(s) > 10:          # n-10*
        min = s[0]              # O(1)
        for x in s:             # n*
            if x < min:         # O(1)
                min = x         # O(1)
        s.remove(min)           # O(1)
    return s                    # O(1)

# f(n) = 1+1+n+n+n^2+n^2+n^2+n+1
#      = 3n^2 + 3n + 3
# ==> O(n^2)
