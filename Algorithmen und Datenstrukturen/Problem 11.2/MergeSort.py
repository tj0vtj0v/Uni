def merge_sort(data: list[float | int]) -> None:
    if len(data) < 2:
        return
    mid = len(data) // 2
    data1 = data[:mid]
    data2 = data[mid:]
    merge_sort(data1)
    merge_sort(data2)
    merge(data1, data2, data)


def merge(left: list[float | int], right: list[float | int], target: list[float | int]) -> None:
    i, j = 0, 0
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            target[i + j] = left[i]
            i += 1
        else:
            target[i + j] = right[j]
            j += 1
    while i < len(left):
        target[i + j] = left[i]
        i += 1
    while j < len(right):
        target[i + j] = right[j]
        j += 1
