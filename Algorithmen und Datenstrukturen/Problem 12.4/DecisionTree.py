tree = {
    "attribute": "sunny",
    True: {
        "attribute": "spotted",
        True: {
            "attribute": "people_around",
            True: False,
            False: {
                "attribute": "legal_spot",
                True: True,
                False: False
            }
        },
        False: True
    },
    False: {
        "attribute": "legal_spot",
        True: {
            "attribute": "people_around",
            True: False,
            False: True
        },
        False: False
    }
}

situation = {
    'sunny': True,
    'people_around': False,
    'spotted': True,
    'legal_spot': False
}


def can_i_fly_my_drone(condition: dict[str: bool]) -> bool:
    result = tree
    while type(result) is not bool:
        result = result[condition[result["attribute"]]]

    return True


print(can_i_fly_my_drone(situation))
