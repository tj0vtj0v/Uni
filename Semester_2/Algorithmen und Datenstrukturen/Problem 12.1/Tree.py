class Node:
    def __init__(self, data):
        self.left = None
        self.right = None
        self.data = data

    def insert(self, data):
        if self.data:
            if data < self.data:
                if self.left is None:
                    self.left = Node(data)
                else:
                    self.left.insert(data)
            elif data > self.data:
                if self.right is None:
                    self.right = Node(data)
                else:
                    self.right.insert(data)
        else:
            self.data = data

    def print(self):
        if self.left:
            self.left.print()
        print(self.data)
        if self.right:
            self.right.print()

    def preorder_generator(self):
        yield self.data
        if self.left:
            yield from self.left.preorder_generator()
        if self.right:
            yield from self.right.preorder_generator()

    def inorder_generator(self):
        if self.left:
            yield from self.left.inorder_generator()
        yield self.data
        if self.right:
            yield from self.right.inorder_generator()

    def postorder_generator(self):
        if self.left:
            yield from self.left.postorder_generator()
        if self.right:
            yield from self.right.postorder_generator()
        yield self.data

    def preorder_printer(self):
        print(self.data)
        if self.left:
            self.left.preorder_printer()
        if self.right:
            self.right.preorder_printer()

    def inorder_printer(self):
        if self.left:
            self.left.inorder_printer()
        print(self.data)
        if self.right:
            self.right.inorder_printer()

    def postorder_printer(self):
        if self.left:
            self.left.postorder_printer()
        if self.right:
            self.right.postorder_printer()
        print(self.data)
