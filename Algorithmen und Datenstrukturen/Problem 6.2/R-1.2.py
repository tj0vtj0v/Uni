def is_even(k: int) -> bool:
    while -1 > k or k > 1:
        k = k - 2 if k > 0 else k + 2

    return k == 0

