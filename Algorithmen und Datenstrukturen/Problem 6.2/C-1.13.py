def reverse(some_items: list) -> list:
    return [some_items[-x-1] for x in range(0, len(some_items))]
