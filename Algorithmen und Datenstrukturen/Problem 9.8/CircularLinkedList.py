from Empty import Empty

class CircularLinkedList:
    class _Node:
        # Streamline memory usage
        __slots__ = 'element', 'next'

        def __init__(self, element, next):
            self.element = element
            self.next = next

    def __init__(self):
        self._head = None
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def write(self, e):
        if self.is_empty():
            self._head = self._Node(e, None)
            self._head.next = self._head
        else:
            new = self._Node(e, self._head.next)
            self._head.next = new
            self._head = new
        self._size += 1

    def read(self):
        if self.is_empty():
            raise Empty
        return self._head.element

    def next(self):
        if self.is_empty():
            raise Empty
        self._head = self._head.next

    def remove(self):
        if self.is_empty():
            raise Empty
        if self._size == 1:
            self._size = 0
            self._head = None
            return
        to_remove = self._head
        self._head = self._head.next
        to_remove.next = self._head.next
        to_remove.element = self._head.element
        self._size -= 1
