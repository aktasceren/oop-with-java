import java.util.Scanner;

public class check_matrices {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows of first matrix: ");
		int r1 = scan.nextInt();
		System.out.println("Enter the number of columns of first matrix: ");
		int c1 = scan.nextInt();
		System.out.println("Enter the elements of first matrix: ");
		int[][] matrix1 = new int[r1][c1];
		int i, j;
        for(j = 0; j < r1; j++) {
        	for(i=0; i < c1; i++){
                matrix1[j][i] = scan.nextInt();
            }
        }
		System.out.println("Enter the number of rows of second matrix: ");
		int r2 = scan.nextInt();
		System.out.println("Enter the number of columns of second matrix: ");
		int c2 = scan.nextInt();
		System.out.println("Enter the elements of second matrix: ");
		int[][] matrix2 = new int[r2][c2];
		for(j = 0; j < r2; j++) {
        	for(i=0; i < c2; i++){
                matrix2[j][i] = scan.nextInt();
        	}
		}
		if(r2 == c1) {
			matricesMult(r1, r2, c1, c2, matrix1, matrix2);
		}
		else {
			System.out.println("These two matrices cannot be multiply!!! ");
		}
	}
	public static void matricesMult(int row1, int row2, int col1, int col2, int matrix[][], int matrix_2[][] ) {
			{
					int[][] resultMatrix = new int[row1][col2]; 
					int x = 0, i, j, k;
					for(i = 0; i < row1; i++) {
							for(j = 0; j < col2; j++) {
								for(k = 0; k < row2; k++) {
									resultMatrix[i][j] += matrix[i][k]*matrix_2[k][j];
								}
							}
					}
					System.out.println("Result:");
					for(i = 0; i < row1; i++) {
						for(j = 0; j < col2; j++) {
							System.out.print("\t" + resultMatrix[i][j]);
						}
						System.out.println("");
					}
	}
}
}
