from typing import List, Callable, Iterable, Any
import time
import os
from copy import deepcopy


import matplotlib.pyplot as plt
from tqdm import tqdm


from insertion_sort_implementations import algorythms


def read_list_from_file(path: str) -> List[int]:
    """
    Reads a list of integers from a file.
    The file has a number of elements on the first line
    and the elements themselves on each subsequent line.

    Args:
        path: path to the file
    """
    with open(path, "r") as f:
        n = int(f.readline())
        return n, [int(f.readline()) for _ in range(n)]

def sort_test_names(test_names: List[str]) -> List[str]:
    """
    Sorts the list of test names by the size of the input.
    """
    return sorted(test_names, key=lambda x: int(x.split("_")[1].split(".")[0]))


def test_and_comapre_algorythms(algorythms: List[Callable],
                                test_dir: str, test_names = None) -> None:
    """
    Plots and prints the time it took to run each algorythm for each input size.

    Args:
        algorythms: a list of functions to test
        test_dir: a directory with test files. ALl files are assumed
            to be named as "test_{size}.txt". The first line of the file
            is assumed to be the size of the input and the subsequent
            lines are assumed to be the elements of the input.
        test_names: a list of test names. If None, all files in the
            test directory are assumed to be tests.
    """
    if test_names is None:
        test_names = sort_test_names(os.listdir(test_dir))
    input_sizes = []
    algo2times = {algo: [] for algo in algorythms}
    pbar = tqdm(test_names)
    for test_name in pbar:
        path = os.path.join(test_dir, test_name)
        size, A_not_sorted = read_list_from_file(path)
        input_sizes.append(size)
        for algorythm in algorythms:
            pbar.set_description(f"Testing {algorythm.__name__} with input size {size}")
            A = deepcopy(A_not_sorted)
            start = time.time()
            algorythm(A, size)
            end = time.time()
            if A != sorted(A_not_sorted):
                print(f"initial array: {A_not_sorted}")
                print(f"algorytm result: {A}")
                print(f"correct result: {sorted(A_not_sorted)}")
                raise ValueError(f"Algorythm {algorythm.__name__} failed to sort an array")
            algo2times[algorythm].append(end - start)

    plt.figure(figsize=(8, 6), dpi=80)

    for algo, times in algo2times.items():
        plt.plot(input_sizes, times, label=algo.__name__)
    plt.legend()
    # plt.show()
    plt.savefig(os.path.join("images_for_readme", "algorythm_time_comaprison.png"))

    print("Algorythm times:")
    for algo, times in algo2times.items():
        print(f"{algo.__name__}: {times}")


if __name__ == "__main__":
    test_and_comapre_algorythms(algorythms, "tests")