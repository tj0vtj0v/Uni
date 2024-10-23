import ctypes


class MyArray:
    def __init__(self):
        self._n = 0
        self._capacity = 1
        self._A = self._make_array(self._capacity)

    def __len__(self):
        return self._n

    def __getitem__(self, k):
        if not 0 <= k < self._n:
            if not -self._n <= k:
                raise IndexError('invalid index')
            k = self._n + k
        print(k)
        return self._A[k]

    def __setitem__(self, k, value):
        if not 0 <= k < self._n:
            if not -self._n <= k:
                raise IndexError('invalid index')
            k = self._n + k
        print(k)
        self._A[k] = value

    def append(self, obj):
        if self._n == self._capacity:
            self._resize(2 * self._capacity)
        self._A[self._n] = obj
        self._n += 1

    def _resize(self, c):
        B = self._make_array(c)
        for k in range(self._n):
            B[k] = self._A[k]
        self._A = B
        self._capacity = c

    def _make_array(self, c):
        return (c * ctypes.py_object)()

    def insert(self, k, value):
        if not 0 <= k < self._n:
            raise IndexError('invalid index')
        if self._n == self._capacity:
            self._resize(2 * self._capacity)
        for i in range(self._n - 1, k - 1, -1):
            self._A[i + 1] = self._A[i]
        self._A[k] = value
        self._n += 1

    def remove(self, k):
        if not 0 <= k < self._n:
            raise IndexError('invalid index')
        for i in range(k, self._n - 1):
            self._A[i] = self._A[i + 1]
        self._n -= 1
        if (self._capacity / self._n) > 5:  # up to 10
            self._resize(int(self._capacity / 2))
