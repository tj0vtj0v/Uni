import math


class Point:
    def __init__(self, x: float, y: float):
        self._x = x
        self._y = y

    def __str__(self):
        return f"({self._x}|{self._y})"

    def vector_length(self):
        return math.sqrt(self._x**2 + self._y**2)
