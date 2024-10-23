def get_equivalent_indices(n: int, k: int) -> int:
    if n < 1 or -n > k or k > 0:
        raise ValueError

    return n + k
