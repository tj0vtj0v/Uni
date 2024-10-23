def minmax(data: list[int]) -> tuple[int, int]:
    minimum = data[0]
    maximum = data[0]

    for element in data:
        if element < minimum:
            minimum = element
        elif element > maximum:
            maximum = element

    return minimum, maximum
