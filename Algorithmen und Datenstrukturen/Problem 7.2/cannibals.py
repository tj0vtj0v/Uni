from shortest_path import shortest_path_search


def successors(C, M):
    def sc(state):
        c, m, b = state
        print(state)
        assert c <= C and m <= M and m <= c  # bc other side is empty
        possibles = {
            (C - c + 1, M - m, not b) if M - m <= C - c + 1 <= C else 'nope': 'action',
            (C - c + 2, M - m, not b) if M - m <= C - c + 2 <= C else 'nope': 'action',
            (C - c, M - m + 1, not b) if C - c >= M - m + 1 <= M else 'nope': 'action',
            (C - c, M - m + 2, not b) if C - c >= M - m + 2 <= M else 'nope': 'action',
            (C - c + 1, M - m + 1, not b) if M - m + 1 <= C - c + 1 <= C and M - m + 1 <= M else 'nope': 'action',
        }
        if 'nope' in possibles.keys():
            possibles.pop('nope')
        return possibles

    return sc


if __name__ == '__main__':
    res = shortest_path_search((3, 3, False), successors(3, 3), lambda state: state == (3, 3, True))
    print(res)
    print('%s transitions' % (int(len(res) / 2)))
