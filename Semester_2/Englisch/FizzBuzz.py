class FizzBuzz:
    def __init__(self, fizz, buzz):
        self._number = 1
        self._fizz = fizz
        self._buzz = buzz

    def __iter__(self):
        return self

    def __next__(self):
        self._number += 1

        if self._number % (self._buzz * self._fizz) == 0:
            return 'FizzBuzz'
        elif self._number % self._fizz == 0:
            return "Fizz"
        elif self._number % self._buzz == 0:
            return "Buzz"
        else:
            return self._number
