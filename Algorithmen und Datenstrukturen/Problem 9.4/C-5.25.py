def remove_all(data, value):
    removed_cells = 0
    for i in range(len(data)):
        if i >= (len(data)-removed_cells):
            return data[0:(len(data) - removed_cells)]
        while data[i+removed_cells] == value:
            removed_cells += 1
            if i >= (len(data)-removed_cells):
                return data[0:(len(data) - removed_cells)]
        data[i] = data[i+removed_cells]
