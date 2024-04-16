def square_odd_smaller_numbers(n: int) -> int:
    return sum(x ** 2 for x in range(1, n, 2))
