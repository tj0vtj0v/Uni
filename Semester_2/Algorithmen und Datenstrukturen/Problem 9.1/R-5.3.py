import sys

n = 26
data = [None] * n
for k in range(n):
    a = len(data)
    b = sys.getsizeof(data)
    print('Length: {0:3d}; Size in bytes: {1:4d}'.format(a, b))
    data.pop(0)

# Size in bytes is a little less, but with higher length the difference shrinks proportionally
