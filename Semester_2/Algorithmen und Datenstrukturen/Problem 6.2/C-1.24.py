def count_vowels(sentence: str) -> int:
    vowels = 0
    for character in sentence:
        if character in ('a', 'e', 'i', 'o', 'u'):
            vowels += 1

    return vowels
