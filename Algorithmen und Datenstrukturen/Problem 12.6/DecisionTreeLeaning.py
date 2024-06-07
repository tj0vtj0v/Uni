import math
import random

"""
examples: [{
    "dependencies": {"attribute_name": "value},
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


def DT_LEARNING(examples: list[dict], attributes: list[dict], parent_examples: list[dict]) -> bool | dict:
    """recursive decision tree learning algorithm as discussed in the lecture"""
    tree = {}

    if not examples:
        return PLURALITY_VAL(parent_examples)
    elif _same_classification(examples):
        return examples[0]["result"]
    elif not attributes:
        return PLURALITY_VAL(examples)
    else:
        attribute = _argmax_importance(attributes, examples)
        tree["attribute"] = attribute["name"]
        attributes.remove(attribute)
    for value in attribute["values"]:
        child_examples = _determine_child_examples(attribute["name"], value, examples)

        tree[value] = DT_LEARNING(child_examples, attributes.copy(), examples)

    return tree


def PLURALITY_VAL(examples: list[dict]) -> bool:
    """calculates the most common result in the examples"""
    true_examples = _count_true_examples(examples)
    return true_examples > len(examples) / 2


def IMPORTANCE(attribute: dict, examples: list[dict]) -> float:
    """computes the importance of an attribute"""
    D = _count_true_examples(examples) / len(examples)
    information_gain = _binary_entropy(D)

    for value in attribute["values"]:
        occurrence, positive_occurrence = _attribute_occurrence(examples, attribute["name"], value)
        remainder = occurrence / len(examples) * _binary_entropy(positive_occurrence / occurrence)
        information_gain -= remainder

    return information_gain


def _count_true_examples(examples: list[dict]) -> int:
    """counts the examples with the output value true"""
    true_count = 0
    for example in examples:
        if example["result"]:
            true_count += 1

    return true_count


def _binary_entropy(q: float) -> float:
    """calculates the binary entropy of input q"""
    return 0 if q == 1 or q == 0 else -(q * math.log2(q) + (1 - q) * math.log2(1 - q))


def _attribute_occurrence(examples: list[dict], attribute_name: str, attribute_value: any) -> tuple[int, int]:
    """returns the overall and positive occurrence of an attribute in the given examples"""
    occurrence = 0
    positive_occurrence = 0

    for example in examples:
        if example["dependencies"][attribute_name] == attribute_value:

            occurrence += 1
            if example["result"]:
                positive_occurrence += 1

    return occurrence, positive_occurrence


def _same_classification(examples: list[dict]) -> bool:
    """returns if all examples lead to the same result"""
    classification = examples[0]["result"]
    for example in examples:
        if example["result"] != classification:
            return False
    return True


def _determine_child_examples(attribute_name: dict, attribute_value: str, examples: list[dict]) -> list[dict]:
    """filters examples for an specific attribute value"""
    child_examples = []

    for example in examples:
        if example["dependencies"][attribute_name] == attribute_value:
            child_examples.append(example)

    return child_examples


def _argmax_importance(attributes: list[dict], examples: list[dict]) -> dict:
    """equivalent of an argmax function for the IMPORTANCE function"""
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
