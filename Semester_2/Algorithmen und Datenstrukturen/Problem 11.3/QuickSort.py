import sys
sys.setrecursionlimit(1_000_000)

def quick_sort(data: list[float | int]) -> list[float | int]:
    if len(data) <= 1:
        return data
    left = []
    right = []
    pivot = data.pop()
    for element in data:
        if element >= pivot:
            right.append(element)
        else:
            left.append(element)
    left = quick_sort(left)
    right = quick_sort(right)
    return left + [pivot] + right


def random_quick_sort(data: list[float | int]) -> list[float | int]:
    import random

    if len(data) <= 1:
        return data
    left = []
    right = []
    pivot = data.pop(random.randrange(0, len(data)))
    for element in data:
        if element >= pivot:
            right.append(element)
        else:
            left.append(element)
    left = quick_sort(left)
    right = quick_sort(right)
    return left + [pivot] + right


def generate_data(length: int, randomized: bool = True) -> list[int]:
    import random

    if randomized:
        return [random.randrange(0, length) for _ in range(length)]
    return [x + 1 for x in range(length)]


r100 = generate_data(100)
s100 = generate_data(100, False)
r1000 = generate_data(1000)
s1000 = generate_data(1000, False)
r10000 = generate_data(10000)
s10000 = generate_data(10000, False)
r100000 = generate_data(100000)
s100000 = generate_data(100000, False)
data = [r100, r1000, r10000, r100000, s100, s1000, s10000, s100000]


text = "*** random datasets ***"
for d in data:
    if len(d) == 100:
        print(text)
        text = "*** sorted datasets ***"
    print("\n\nlength:", len(d))

    quick_sort(d)
    random_quick_sort(d)

#output from ipython
"""
*** random datasets ***
length: 100
CPU times: total: 0 ns
Wall time: 0 ns
CPU times: total: 0 ns
Wall time: 0 ns


length: 1000
CPU times: total: 0 ns
Wall time: 998 µs
CPU times: total: 0 ns
Wall time: 996 µs


length: 10000
CPU times: total: 0 ns
Wall time: 8.54 ms
CPU times: total: 0 ns
Wall time: 7.02 ms


length: 100000
CPU times: total: 93.8 ms
Wall time: 98.8 ms
CPU times: total: 62.5 ms
Wall time: 96.9 ms


*** sorted datasets ***
length: 100
CPU times: total: 0 ns
Wall time: 0 ns
CPU times: total: 0 ns
Wall time: 0 ns


length: 1000
CPU times: total: 15.6 ms
Wall time: 18.7 ms
CPU times: total: 0 ns
Wall time: 9.51 ms


length: 10000
CPU times: total: 1.45 s
Wall time: 1.76 s
CPU times: total: 766 ms
Wall time: 951 ms

length: 100000
CPU times: total: 5min 7s
Wall time: 6min 3s
CPU times: total: 4min 59s
Wall time: 5min 41s
"""
#  single run
"""
In [2]: %time quick_sort(s100000)
CPU times: total: 4min 31s
Wall time: 5min 24s
Out[2]:

In [3]: %time random_quick_sort(s100000)
CPU times: total: 2min 48s
Wall time: 3min 20s
Out[3]:
"""
