import random

from Point import Point


class PointMaker:
    def __init__(self, resolution: float = 0, randomized: bool = False, random_count: int = 0):
        self._resolution = resolution
        self._random = randomized
        self._random_count = random_count
        self._current_x = 0
        self._current_y = 0

    def __next__(self):
        if self._random:
            return self._random_next()
        else:
            return self._grid_next()

    def _random_next(self) -> Point:
        self._random_count -= 1

        if self._random_count >= 0:
            return Point(random.random(), random.random())
        else:
            raise StopIteration

    def _grid_next(self) -> Point:
        self._current_x += self._resolution

        if self._current_x > 1:
            self._current_y += self._resolution
            self._current_x = 0

        if self._current_y > 1:
            raise StopIteration

        return Point(self._current_x, self._current_y)

    def __iter__(self):
        return self
