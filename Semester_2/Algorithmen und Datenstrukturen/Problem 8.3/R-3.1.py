import math

import matplotlib.pyplot as plt
import numpy as np

fig = plt.figure()
x = np.arange(1, 20, .1)

y1 = [8 * n for n in x]
y2 = [4 * n * math.log2(n) for n in x]
y3 = [2 * n**2 for n in x]
y4 = [n**3 for n in x]
y5 = [2**n for n in x]

plt.scatter(x, y1, marker=".", label="8n")
plt.scatter(x, y2, marker=".", label="4n log n")
plt.scatter(x, y3, marker=".", label="2n^2")
plt.scatter(x, y4, marker=".", label="n^3")
plt.scatter(x, y5, marker=".", label="2^n")

plt.xscale("log")
plt.yscale("log")

plt.legend()
plt.show()
