from ChainHashMap import ChainHashMap


def fill_map(offset):
    for x in range(offset, 10+offset):
        test_map[x] = x


test_map = ChainHashMap(cap=10)
fill_map(0)
fill_map(100)
print(test_map[1])
print(test_map[101])
