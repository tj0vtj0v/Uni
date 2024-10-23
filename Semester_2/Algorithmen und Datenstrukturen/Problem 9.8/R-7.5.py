from CircularLinkedList import CircularLinkedList
from Empty import Empty


def circular_length(l: CircularLinkedList):
    if l.is_empty():
        return 0
    start = l._head
    l.next()
    elements = 1
    while l._head != start:
        l.next()
        elements += 1
    return elements
