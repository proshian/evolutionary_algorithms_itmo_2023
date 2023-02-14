#include <iostream>

using namespace std;


template <class T>
void InsertionSort(T* A, int n) {

	for (int j = 1; j < n; ++j) {
		T key = A[j];
		
		int i = j - 1;

		while (i >= 0 && A[i] > key) {
			A[i + 1] = A[i];
			i--;
		}

		A[i + 1] = key;
	}
}


template <class T>
void PrintArray(T* A, int n) {
	for (int i = 0; i < n; ++i) {
		cout << A[i] << ' ';
	}
	cout << endl;
}

int main() {
	int A[] = { 5, 2, 4, 6, 1, 3 };
	int n = sizeof(A) / sizeof(int);

	PrintArray(A, n);
	InsertionSort(A, n);
	PrintArray(A, n);

	return 0;
}