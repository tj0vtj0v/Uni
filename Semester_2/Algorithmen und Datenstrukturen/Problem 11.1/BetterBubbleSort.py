def better_bubble_sort(data: list[float | int]) -> None:
    if len(data) <= 1:
        return
    for run in range(len(data)):
        altered = False
        for i in range((len(data) - 1) - run):
            if data[i] > data[i + 1]:
                altered = True
                temp = data[i]
                data[i] = data[i + 1]
                data[i + 1] = temp
        if not altered:
            return
