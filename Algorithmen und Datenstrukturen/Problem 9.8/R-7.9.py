def concatinate(L, M):
    lp = L._head.prev
    mp = M._head.prev

    lp.next = mp
    mp.next = lp

    L._head.prev = M._head
    M._head.prev = L._head
