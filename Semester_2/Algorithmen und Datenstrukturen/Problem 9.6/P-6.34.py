from MyStack import MyStack


def postfix_solver(expression: str) -> int:
    calc = MyStack()

    for element in expression.split(" "):
        if element.isdigit():
            calc.push(element)
        else:
            y = calc.pop()
            x = calc.pop()
            calc.push(str(eval(x + element + y)))

    return calc.pop()


print(postfix_solver("5 2 + 8 3 - * 4 /"))
#  print(postfix_solver("5 2 + 8 3 − ∗ 4 /")) #  - and * are the bitch
