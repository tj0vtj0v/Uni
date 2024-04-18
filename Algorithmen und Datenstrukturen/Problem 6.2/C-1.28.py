def norm(*v) -> float:
    return sum([x**len(v) for x in v]) ** (1 / len(v))
