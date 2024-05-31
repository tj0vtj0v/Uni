# %%
import random
from datetime import datetime
from pprint import pprint


class MandatoryProblem:
    TEXT: str
    TARGET: str
    result: dict[str: dict[str: any]]  # 1. key: algorithm, 2. key: property

    def main(self):
        # execution of each algorithm
        self.brute_force_search()
        self.kmp_search()
        self.boyer_moore_search()

        # comparison of the algorithms
        self.compare()

    # %%
    def __init__(self, target_length: int = 20):
        structure = {
            "time": None,
            "comparisons": None,
            "and so on": None
        }
        self.result = {
            "brute force": structure.copy(),
            "kmp": structure.copy(),
            "boyer-moore": structure.copy()
        }

        # overhead for dynamic computation
        self.read_file()
        self.generate_target(target_length)

    # %%
    def read_file(self) -> None:
        with open("text.txt", 'r') as text:
            self.TEXT = text.readline()

    # %%
    def generate_target(self, length: int) -> None:
        random_index = random.randrange(0, len(self.TEXT) - length - 1)
        self.TARGET = self.TEXT[random_index:random_index + length + 1]

    # %%
    def brute_force_search(self):
        start_time = datetime.now().timestamp()
        comparisons = 0
        text_length = len(self.TEXT)
        target_length = len(self.TARGET)

        for index in range(text_length - target_length):

            target_index = 0
            comparisons += 1
            while target_index < target_length and self.TEXT[index + target_index] == self.TARGET[target_index]:
                target_index += 1
                comparisons += 1

            if target_index == target_length:
                self.result["brute force"]["time"] = datetime.now().timestamp() - start_time
                self.result["brute force"]["comparisons"] = comparisons
                return index

        return -1

    # %%
    def kmp_search(self):
        pass

    # %%
    def boyer_moore_search(self):
        pass  # implementation on page 588

    # %%
    def compare(self):
        pprint(self.result)


# %%
if __name__ == "__main__":
    MandatoryProblem().main()
