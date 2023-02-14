#include <iostream>
#include <cstring>
#include <iomanip>

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
		cout << A[i] << " ";
	}
	cout << endl;
}

int * ReadArrayFromFile(char * file_name, int & n) {
	FILE * file = fopen(file_name, "r");
	fscanf(file, "%d", &n);
	int * A = new int[n];
	for (int i = 0; i < n; ++i) {
		fscanf(file, "%d", &A[i]);
	}
	fclose(file);
	return A;
}

char * GetFullPath(char * dir, char * file_name) {
	char * full_path = new char[100];
	strcpy(full_path, dir);
	strcat(full_path, "/");
	strcat(full_path, file_name);
	return full_path;
}

bool check_sorted(int * A, int n) {
	for (int i = 0; i < n - 1; ++i)
		if (A[i] > A[i + 1])
			return false;
	return true;
}

void test_insertion_sort(char* tests_dir, char* test_names[], int tests_num) {
   /*
    * Prints the time it took to run the algorythm for each input size.
    *
    * tests_dir - directory with tests
    * test_names - array of test names
    * tests_num - number of tests
    */
	int test_names_num = sizeof(test_names) / sizeof(char*);

	for (int i = 0; i < tests_num; ++i) {
		int n;
		int * A = ReadArrayFromFile(GetFullPath(tests_dir, test_names[i]), n);
		
		// get time with double precision
		clock_t start = clock();
		InsertionSort(A, n);
		clock_t end = clock();

		if (!check_sorted(A, n)) {
			cout << "Algorythm failed" << endl;
			return;
		}
		cout << "Time for " << test_names[i] << ": " << (double)(end - start)/CLOCKS_PER_SEC  << std::setprecision(15) << endl;
		
		delete[] A;
	}
}

int main() {
	char *  tests_dir = "tests";
	char * test_names[] = {
		"test_1024.txt", "test_2048.txt", "test_4096.txt",
		"test_8192.txt", "test_16384.txt", "test_32768.txt"};
	
	int tests_num = sizeof(test_names) / sizeof(char**);

	test_insertion_sort(tests_dir, test_names, tests_num);
}