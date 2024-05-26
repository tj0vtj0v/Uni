from Full import Full
from Empty import Empty


class AliceStack:
    def __init__(self, max_len=3):
        self._data = [None] * max_len
        self._n = -1

    def push(self, e):
        if self._n == len(self._data) - 1:
            raise Full
        self._data[self._n] = e
        self._n += 1

    def pop(self):
        if self._n < 0:
            raise Empty
        self._n -= 1
        return self._data[self._n + 1]

    def top(self):
        if self._n < 0:
            raise Empty
        return self._data(self._n)
