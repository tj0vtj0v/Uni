def flat_tree_generator(data):
    for element in data:
        if isinstance(element, list):
            yield from flat_tree_generator(element)
        else:
            yield element


def flatten_tree(data):
    return [x for x in flat_tree_generator(data)]


tree = [[1], [2, 3], [4, [5, 6, [7, 8, [9, [10, 11, 12]]]]]]
print(flatten_tree(tree))
