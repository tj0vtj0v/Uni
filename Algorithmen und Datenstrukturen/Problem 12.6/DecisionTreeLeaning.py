import math
import random
from pprint import pprint

"""
examples: [{
    "dependencies": [{
        "attribute_name": "sth",
        "value": "sth"
        }],
    "result": bool
}]

attribute: {
    "name": "sth",
    "values": ["sth"]
}

tree: { 
    "attribute": "sth",
    "val1": {tree} | bool,
    "val2": {tree} | bool,
    ...
}
"""

exmpls = [
    {"dependencies": [{"visible": 0, "distance": "< 10", "armed": 0, }], "result": True},
    {"dependencies": [{"visible": 0, "distance": "< 10", "armed": 1, }], "result": False},
    {"dependencies": [{"visible": 0, "distance": "10 - 20", "armed": 0, }], "result": False},
    {"dependencies": [{"visible": 0, "distance": "10 - 20", "armed": 1, }], "result": False},
    {"dependencies": [{"visible": 0, "distance": "> 20", "armed": 0, }], "result": False},
    {"dependencies": [{"visible": 0, "distance": "> 20", "armed": 1, }], "result": False},
    {"dependencies": [{"visible": 1, "distance": "< 10", "armed": 0, }], "result": True},
    {"dependencies": [{"visible": 1, "distance": "< 10", "armed": 1, }], "result": True},
    {"dependencies": [{"visible": 1, "distance": "10 - 20", "armed": 0, }], "result": True},
    {"dependencies": [{"visible": 1, "distance": "10 - 20", "armed": 1, }], "result": True},
    {"dependencies": [{"visible": 1, "distance": "> 20", "armed": 0, }], "result": False},
    {"dependencies": [{"visible": 1, "distance": "> 20", "armed": 1, }], "result": False}
]

atrbts = [
    {"name": "visible", "values": [0, 1]},
    {"name": "distance", "values": ["< 10", "10 - 20", "> 20"]},
    {"name": "armed", "values": [0, 1]}
]


def DT_LEARNING(examples: list[dict], attributes: list[dict], parent_examples: list[dict]) -> bool | dict:
    tree = {}

    if not examples:
        return PLURALITY_VAL(parent_examples)
    elif same_classification(examples):
        return examples[0]["result"]
    elif not attributes:
        return PLURALITY_VAL(examples)
    else:
        attribute = _maximise_importance(attributes, examples)
        tree["attribute"] = attribute["name"]
        attributes.remove(attribute)
    for value in attribute["values"]:
        child_examples = _determine_child_examples(attribute["name"], value, examples)

        tree[value] = DT_LEARNING(child_examples, attributes, examples)


    return tree


def PLURALITY_VAL(examples: list[dict]) -> bool:
    true_examples = _count_true_examples(examples)
    return true_examples > len(examples) / 2


def IMPORTANCE(attribute: dict, examples: list[dict]) -> float:
    D = _count_true_examples(examples) / len(examples)
    information_gain = _binary_entropy(D)

    for value in attribute["values"]:
        occurrence, positive_occurrence = _attribute_occurrence(examples, attribute["name"], value)
        remainder = occurrence / len(examples) * _binary_entropy(positive_occurrence / occurrence)
        information_gain -= remainder

    return information_gain


def _count_true_examples(examples: list[dict]) -> int:
    true_count = 0
    for example in examples:
        if example["result"]:
            true_count += 1

    return true_count


def _binary_entropy(q: float) -> float:
    return 0 if q == 1 or q == 0 else -(q * math.log2(q) + (1 - q) * math.log2(1 - q))


def _attribute_occurrence(examples: list[dict], attribute_name: str, attribute_value: any) -> tuple[int, int]:
    occurrence = 0
    positive_occurrence = 0

    for example in examples:
        for dependency in example["dependencies"]:
            if dependency[attribute_name] == attribute_value:

                occurrence += 1
                if example["result"]:
                    positive_occurrence += 1

                break

    return occurrence, positive_occurrence


def same_classification(examples: list[dict]) -> bool:
    classification = examples[0]["result"]
    for example in examples:
        if example["result"] != classification:
            return False
    return True


def _determine_child_examples(attribute_name: dict, attribute_value: str, examples: list[dict]) -> list[dict]:
    child_examples = []

    for example in examples:
        for dependency in example["dependencies"]:
            if dependency[attribute_name] == attribute_value:
                child_examples.append(example)

            break

    return child_examples


def _maximise_importance(attributes: list[dict], examples: list[dict]) -> dict:
    maximum_importance = 0
    maximum_attributes = []

    for attribute in attributes:
        importance = IMPORTANCE(attribute, examples)

        if importance > maximum_importance:
            maximum_importance = importance
            maximum_attributes = [attribute]

        elif importance == maximum_importance:
            maximum_attributes.append(attribute)

    return random.choice(maximum_attributes)


pprint(DT_LEARNING(exmpls, atrbts, exmpls))
