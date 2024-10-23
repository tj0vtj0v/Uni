character = ('c', 'a', 't', 'd', 'o', 'g')

for c1 in character:
    for c2 in character:
        for c3 in character:
            for c4 in character:
                for c5 in character:
                    for c6 in character:
                        if len({c1, c2, c3, c4, c5, c6}) == 6:
                            print(c1+c2+c3+c4+c5+c6)
