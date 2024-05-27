def minmax_recursive(data: list[float | int]):
    if len(data) < 2:
        return min(data), max(data)
    mm1 = minmax_recursive(data[0: len(data) // 2])
    mm2 = minmax_recursive(data[len(data) // 2: len(data)])
    return min(mm1[0], mm2[0]), max(mm1[1], mm2[1])
