from shortest_path import shortest_path_search


def successors(X, Y):
    def sc(state):
        x, y = state
        assert x <= X and y <= Y
        return {
            (X, y): 'fill x',
            (x, Y): 'fill y',
            (0, y): 'empty x',
            (x, 0): 'empty y',
            (0, y + x) if y + x <= Y else (x - (Y - y), Y): 'x->y',
            (x + y, 0) if x + y <= X else (X, y - (X - x)): 'x<-y'
        }

    return sc


if __name__ == '__main__':
    res = shortest_path_search((0, 0), successors(418, 986), lambda state: state == (1, 0))
    print(res)
    print('%s transitions' % (int(len(res) / 2)))
    print()
    res = shortest_path_search((0, 0), successors(418, 986), lambda state: state == (6, 0))
    print(res)
    print('%s transitions' % (int(len(res) / 2)))
