def maximum_recursive(data: list[float | int]):
    if len(data) == 1:
        return data[0]
    max1 = maximum_recursive(data[0: len(data) // 2])
    max2 = maximum_recursive(data[len(data) // 2: len(data)])
    return max1 if max1 > max2 else max2
