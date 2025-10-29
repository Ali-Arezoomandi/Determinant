# 🧮 Determinant Calculator in Java

This Java project calculates the **determinant** of a square matrix using **three different methods**:

1. 🔄 **Recursive Method**
2. 📐 **Gaussian Elimination**
3. 🟩 **LU Decomposition**

The program generates a random matrix and computes its determinant using all three methods. It also measures the computation time for each method and writes the results to a file named `output.txt`.

---

## ⚡ Features

- 🎲 Generate a random `n x n` matrix with values in a specified range.
- 🔢 Calculate determinant using:
  - 🔄 Recursive expansion by minors
  - 📐 Gaussian elimination
  - 🟩 LU decomposition
- ⏱ Measure and compare computation times.
- 📝 Write results, including the matrix, determinants, and elapsed times, to `output.txt`.

---

## 🏃 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Ali-Arezoomandi/determinant
   ```
2. Open the project in VS Code or any Java IDE.
3. Compile and run the `_main_` class.
4. Enter the matrix size `n` when prompted.
5. The results will be printed in the console and also saved in `output.txt`.

---

## 📁 File Structure

```
Determinant/
├── _main_.java      	 # Main Java code
├── output.txt           # Results (generated after running)
└── README.md            # Project description
```

---

## 🖥 Example Output

```
With Recursive:
	Matrix:
	[2, 3, 8, 3, 6]
	[7, 4, 8, 3, 3]
	[3, 1, 7, 5, 2]
	[3, 2, 7, 8, 7]
	[8, 9, 2, 7, 9]
	Determinant: 3110.0
	Delta time (ms): 3.2486

With Gaussian:
	Matrix:
	[2, 3, 8, 3, 6]
	[7, 4, 8, 3, 3]
	[3, 1, 7, 5, 2]
	[3, 2, 7, 8, 7]
	[8, 9, 2, 7, 9]
	Determinant: 3110.0000000000005
	Delta time (ms): 0.359

With LU:
	Matrix:
	[2, 3, 8, 3, 6]
	[7, 4, 8, 3, 3]
	[3, 1, 7, 5, 2]
	[3, 2, 7, 8, 7]
	[8, 9, 2, 7, 9]
	Determinant: 3110.0
	Delta time (ms): 0.4696

Compare: 
	📊 Gaussian's time = 0.359  is faster than 🔄 Recursive's time = 3.2486
```

---

## ⚠️ Notes

- ✅ Make sure the matrix is square (n x n) before calculating the determinant.
- 🔁 The program converts the matrix to upper triangular form for Gaussian elimination.
- 🟩 Constructs L and U matrices for LU decomposition.
- 💾 All detailed results are saved in `output.txt` for future reference.

---

## ✍️ Author

**Ali-Arezoomandi**  
📧 Contact: [ali.arezoomandi1723.email@example.com]  
💻 GitHub: [https://github.com/Ali-Arezoomandi](https://github.com/Ali-Arezoomandi)

---
