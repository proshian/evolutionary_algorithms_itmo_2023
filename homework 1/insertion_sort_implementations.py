from typing import Iterable, Any

def insertion_sort(A: Iterable[Any], n: int) -> None:
    for j in range(1, n):
        key = A[j]
        i = j - 1
        while i >= 0 and A[i] > key:
            A[i+1] = A[i]
            i -= 1
        A[i+1] = key

def insertion_sort_only_for_loops(A: Iterable[Any], n: int) -> None:
    for j in range(1, n):
        key = A[j]
        breaked = False
        for i in range(j-1, -1, -1):
            if A[i] <= key:
                breaked = True
                break
            A[i+1] = A[i]
        if not breaked:
            i -= 1
        A[i+1] = key
    
algorythms = [insertion_sort, insertion_sort_only_for_loops]