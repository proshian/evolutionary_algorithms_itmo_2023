from typing import List, Callable, Iterable, Any
import time
from copy import deepcopy


from numpy.random import randint
import matplotlib.pyplot as plt
from tqdm import tqdm

def insertion_sort(A: Iterable[Any], n: int) -> None:
    for j in range(1, n):
        key = A[j]
        i = j - 1
        while i >= 0 and A[i] > key:
            A[i+1] = A[i]
            i = i - 1
        A[i+1] = key

def insertion_sort2(A: Iterable[Any], n: int) -> None:
    for j in range(1, n):
        key = A[j]
        breaked = False
        for i in range(j-1, -1, -1):
            if A[i] <= key:
                breaked = True
                break
            A[i+1] = A[i]
        if not breaked:
            i = -1
        A[i+1] = key
        
def test_and_comapre_algorythms(algorythms: List[Callable],
                                input_sizes: List[int],
                                min_el: int, max_el: int) -> None:
    """
    Args:
        algorythms: a list of functions to test
        input_sizes: a list of input sizes
    Plots the time it took to run each algorythm for each input size.
    """
    
    algo2times = {algo: [] for algo in algorythms}
    pbar = tqdm(input_sizes)
    for size in pbar:
        A_not_sorted = list(randint(min_el, max_el, size))
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

    for algo, times in algo2times.items():
        plt.plot(input_sizes, times, label=algo.__name__)
    plt.legend()
    plt.show()
    plt.savefig("algorythm_time_comaprison.png")

    print("Algorythm times:")
    print(algo2times)
    for algo, times in algo2times.items():
        print(f"{algo.__name__}: {times}")


if __name__ == "__main__":
    test_and_comapre_algorythms(
        [insertion_sort, insertion_sort2],
        [16, 32, 64, 128, 512, 1024, 2048, 4096, 8192, 16384], 0, 100)
