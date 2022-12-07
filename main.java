import java.util.Scanner;

class play {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";

	public static void main(String[] args) {
		// initialisation des variables

		Array array = new Array();
		int[][] array1 = array.CreateArray();
		Verif verif = new Verif();
		int nbrColumn = 5;
		int nbrLigne = 6;
		int nbrPlayer = 1;
		AddOnArray addOnArray = new AddOnArray();

		for (int x = 0; x <= 42; x++) {
			Player player = new Player();
			int choosenColumn = player.ChoicePlayer();
			array1 = addOnArray.ChangeArray(array1, nbrColumn, nbrLigne, choosenColumn, nbrPlayer);

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					if (j == 6) {
						if (array1[i][j] == 1) {
							System.out.println(ANSI_GREEN + array1[i][j] + " " + ANSI_RESET);
						} else if (array1[i][j] == 2) {
							System.out.println(ANSI_RED + array1[i][j] + " " + ANSI_RESET);
						} else {
							System.out.println(array1[i][j] + " ");
						}
					} else {
						if (array1[i][j] == 1) {
							System.out.print(ANSI_GREEN + array1[i][j] + " " + ANSI_RESET);
						} else if (array1[i][j] == 2) {
							System.out.print(ANSI_RED + array1[i][j] + " " + ANSI_RESET);
						} else {
							System.out.print(array1[i][j] + " ");
						}
					}
				}
			}
			boolean finish = verif.VerifArray(array1);
			if (finish) {
				if (nbrPlayer == 1) {
					System.out.println(ANSI_GREEN + "Vert a gagné !!!" + ANSI_RESET);
					break;
				} else {
					System.out.println(ANSI_RED + "Rouge a gagné !!!" + ANSI_RESET);
					break;
				}

			}

			if (nbrPlayer == 1) {
				nbrPlayer = 2;
			} else {
				nbrPlayer = 1;
			}
			if (x == 42) {
				System.out.println("égalité :)");
			}
		}

	}

}

class Array {
	public int[][] CreateArray() {
		int[][] array = new int[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				array[i][j] = 0;
			}
		}
		return array;
	}
}

class AddOnArray {
	public int[][] ChangeArray(int[][] array, int column, int ligne, int choosenColumn, int player) {
		for (int i = column; i >= 0; i--) {
			if (array[i][choosenColumn - 1] == 0) {
				array[i][choosenColumn - 1] = player;
				break;
			}
		}
		return array;
	}
}

class Player {
	public int ChoicePlayer() {
		boolean chiffre = false;
		int column = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Veuillez saisir une colonne : ");
			String strColumn = sc.nextLine();
			try {
				column = Integer.parseInt(strColumn);
				if (column >= 1 && column <= 7) {
					chiffre = true;
				}
			} catch (NumberFormatException e) {
				System.out.println("Cette valeur n'est pas un chiffre, essaie encore !");
			}
		} while (chiffre != true);

		return column;

	}
}

class Verif {
	public boolean VerifArray(int[][] array) {
		boolean finish = false;
		int compteurPlayer1 = 0;
		int compteurPlayer2 = 0;
		// vérification en ligne
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (array[i][j] == 1) {
					compteurPlayer1++;
					compteurPlayer2 = 0;
					if (compteurPlayer1 == 4) {
						finish = true;
					}
				} else if ((array[i][j] == 2)) {
					compteurPlayer2++;
					compteurPlayer1 = 0;
					if (compteurPlayer2 == 4) {
						finish = true;
					}
				} else {
					compteurPlayer1 = 0;
					compteurPlayer2 = 0;
				}
			}
			compteurPlayer1 = 0;
			compteurPlayer2 = 0;
		}
		// pour les colonnes
		for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 6; i++) {
				if (array[i][j] == 1) {
					compteurPlayer1++;
					compteurPlayer2 = 0;
					if (compteurPlayer1 == 4) {
						finish = true;
					}
				} else if ((array[i][j] == 2)) {
					compteurPlayer2++;
					compteurPlayer1 = 0;
					if (compteurPlayer2 == 4) {
						finish = true;
					}
				} else {
					compteurPlayer1 = 0;
					compteurPlayer2 = 0;
				}
			}
			compteurPlayer1 = 0;
			compteurPlayer2 = 0;
		}
		// pour les diagonales inverse brice
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (i <= 2 && j <= 3) {
					if (array[i][j] == 1 && array[i + 1][j + 1] == 1 && array[i + 2][j + 2] == 1
							&& array[i + 3][j + 3] == 1) {
						finish = true;
						return finish;
					} else if (array[i][j] == 2 && array[i + 1][j + 1] == 2 && array[i + 2][j + 2] == 2
							&& array[i + 3][j + 3] == 2) {
						finish = true;
						return finish;
					}
				}
			}
		}
		// pour les diagonales de brice
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (i <= 2 && j >= 3) {
					if (array[i][j] == 1 && array[i + 1][j - 1] == 1 && array[i + 2][j - 2] == 1
							&& array[i + 3][j - 3] == 1) {
						finish = true;
						return finish;
					} else if (array[i][j] == 2 && array[i + 1][j - 1] == 2 && array[i + 2][j - 2] == 2
							&& array[i + 3][j - 3] == 2) {
						finish = true;
						return finish;
					}
				}
			}
		}
		return finish;
	}
}
