from Tree import Node


def invert(root: Node) -> None:
    if root.left:
        invert(root.left)
    if root.right:
        invert(root.right)
    temp = root.left
    root.left = root.right
    root.right = temp
