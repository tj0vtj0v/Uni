import math

from PointMaker import PointMaker

if __name__ == "__main__":
    point_maker = PointMaker(0, True, 100)

    inside = 0
    outside = 0

    for point in point_maker:
        if point.vector_length() > 1:
            outside += 1
        else:
            inside += 1

    my_pi = 4 * inside / (outside + inside)

    print("my pi:  ", my_pi)
    print("real pi:", math.pi)
    print("AE:     ", abs(math.pi - my_pi), "\n")
