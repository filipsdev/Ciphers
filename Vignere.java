
public class Vignere extends SuperClass {

	private String keyword;
	private String countedLetters = "";


//	constructor

	public Vignere(String keyword) {
		super();
		this.keyword = keyword;
	}
	
//	getter for counted letters
	
	public String getCountedLetters() {
		return this.countedLetters;
	}

//	create VignereCipher a.k.a. my matrix

	public char[][] matrix(String keyword) {

		char[] matrixRow = new char[super.alphabet().length];
		char[][] matrix = new char[keyword.length()][matrixRow.length];

		for (int i = 0; i < keyword.length(); i++) {
			matrixRow = super.matrixRow(keyword.charAt(i));

			for (int j = 0; j < matrixRow.length; j++) {
				matrix[i][j] = matrixRow[j];
			}
		}
		return matrix;
	}

//	Encrypt

	public String doEncription(String str) {
		char[][] matrix = this.matrix(this.keyword);
		char[] encryptThis = super.convStrToChArray(str);
		char[] alphabet = super.alphabet();

		int counter = 0;
		for (int i = 0; i < encryptThis.length; i++) {
			if (counter > this.keyword.length() - 1) {
				counter = 0;
			}
			for (int j = 0; j < alphabet.length; j++) {

				if (encryptThis[i] == alphabet[j]) {
					encryptThis[i] = matrix[counter][j];
					this.countedLetters += matrix[counter][j];
					counter++;					
					break;
				}
			}
		}
		return super.convChArrayToStr(encryptThis);
	}

//	Decrypt

	public String doDecription(String str) {
		char[][] matrix = this.matrix(this.keyword);
		char[] decryptThis = super.convStrToChArray(str);
		char[] alphabet = super.alphabet();

		int counter = 0;
		for (int i = 0; i < decryptThis.length; i++) {
			if (counter > this.keyword.length() - 1) {
				counter = 0;
			}
			for (int j = 0; j < alphabet.length; j++) {
				if (decryptThis[i] == matrix[counter][j]) {
					decryptThis[i] = alphabet[j];
					this.countedLetters += alphabet[j];
					counter++;
					break;
				}
			}
		}
		return super.convChArrayToStr(decryptThis);
	}

//	print my matrix

	public void printMyMarix(String keyword) {
		String str = "";
		char[] alphabetCh = super.alphabet();
		String alphabet = "  ";

		for (int i = 0; i < keyword.length(); i++) {
			str = i + 1 + "|";
			for (int j = 0; j < 26; j++) {
				str += " " + this.matrix(keyword)[i][j] + " ";
			}
			System.out.println(str);
		}
		String numbers = "  ";
		String lines = "  ";
		for (int i = 1; i < 27; i++) {
			lines += " _ ";
			alphabet += " " + alphabetCh[i - 1] + " ";
			if (i < 10) {
				numbers += " " + i + " ";
			} else {
				numbers += i + " ";
			}
		}
		System.out.println(lines);
		System.out.println(numbers);
		System.out.println(alphabet);
	}
}
