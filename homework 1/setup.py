from setuptools import Extension, setup
from Cython.Build import cythonize

ext_modules = [
    Extension("insertion_sort",
              sources=["insertion_sort_cython.pyx"],
              libraries=["m"]  # Unix-like specific
              )
]

setup(
    name='insertion_sort_app',
    ext_modules=cythonize(ext_modules),
    zip_safe=False,
)