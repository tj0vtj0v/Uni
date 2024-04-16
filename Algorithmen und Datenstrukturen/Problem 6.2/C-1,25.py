import string


def remove_punctuation(s: str) -> str:
    return "".join([char for char in s if char not in string.punctuation])
