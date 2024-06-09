import decimal


def root_of(x: float, iterations: int = 10, guess: int = 10):
    if x < 0 or guess < 0 or iterations <= 0:
        raise AttributeError

    result = guess
    for _ in range(iterations):
        result = result - ((result ** 2 - x) / (2 * result))

    return result


def inverse_root_of(x: float, iterations: int = 10, guess: int = 10):
    if x < 0 or guess < 0 or iterations <= 0:
        raise AttributeError

    decimal.getcontext().prec = iterations

    result = decimal.Decimal(guess)
    for _ in range(iterations):
        result = result - (((1 / (result ** 2)) - x) / (-(2 / (result ** 3))))

    return result


print(inverse_root_of(100, 10))
