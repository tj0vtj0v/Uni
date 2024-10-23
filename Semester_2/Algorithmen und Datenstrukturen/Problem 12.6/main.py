from pprint import pprint

from DecisionTreeLeaning import DT_LEARNING

examples = [
    {"dependencies": {"visible": 0, "distance": "< 10", "armed": 0, }, "result": True},
    {"dependencies": {"visible": 0, "distance": "< 10", "armed": 1, }, "result": False},
    {"dependencies": {"visible": 0, "distance": "10 - 20", "armed": 0}, "result": False},
    {"dependencies": {"visible": 0, "distance": "10 - 20", "armed": 1}, "result": False},
    {"dependencies": {"visible": 0, "distance": "> 20", "armed": 0, }, "result": False},
    {"dependencies": {"visible": 0, "distance": "> 20", "armed": 1, }, "result": False},
    {"dependencies": {"visible": 1, "distance": "< 10", "armed": 0, }, "result": True},
    {"dependencies": {"visible": 1, "distance": "< 10", "armed": 1, }, "result": True},
    {"dependencies": {"visible": 1, "distance": "10 - 20", "armed": 0}, "result": True},
    {"dependencies": {"visible": 1, "distance": "10 - 20", "armed": 1}, "result": True},
    {"dependencies": {"visible": 1, "distance": "> 20", "armed": 0, }, "result": False},
    {"dependencies": {"visible": 1, "distance": "> 20", "armed": 1, }, "result": False}
]

attributes = [
    {"name": "visible", "values": [0, 1]},
    {"name": "distance", "values": ["< 10", "10 - 20", "> 20"]},
    {"name": "armed", "values": [0, 1]}
]


def print_tree(data: dict | bool, depth: int, decision):
    if type(data) is bool:
        print((depth - 1) * "    " + "|-- " + str(decision) + bool(depth) * " -> " + str(data))
        return
    print((depth - 1) * "    " + '|-- ' + str(decision) + bool(depth) * " -- " + data["attribute"])
    data.pop("attribute")
    for key, value in data.items():
        print_tree(value, depth + 1, key)


result = DT_LEARNING(examples, attributes, examples)
print_tree(result.copy(), 0, "")
print()
pprint(result)
