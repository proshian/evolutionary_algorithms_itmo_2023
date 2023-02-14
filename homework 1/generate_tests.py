from typing import List
import os

from numpy.random import randint

def generate_tests(input_sizes: List[int], min_el: int,
                   max_el: int, dir_name: str = "tests") -> None:
    if not os.path.exists(dir_name):
        os.makedirs(dir_name)

    for input_size in input_sizes:
        with open(os.path.join(".","tests", f"test_{input_size}.txt"), "w") as f:
            f.write(f"{input_size}\n")
            A = randint(min_el, max_el, input_size)
            f.write("\n".join(map(str, A)))

if __name__ == "__main__":
    input_sizes = [2**i for i in range(10, 16)]
    generate_tests(input_sizes, 0, 100)
            