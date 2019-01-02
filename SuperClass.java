import java.io.BufferedWriter;
import java.io.FileWriter;

public class SuperClass {

	private FileWriter fwBufA;
	private BufferedWriter bufA;
	private int[] freq = new int[this.alphabet().length];
	private double[] avgCounts = { 8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0, 0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1,
			6.0, 6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1 };

//	constructor

	public SuperClass() {
	}

//	create all capital letters as char[]

	public char[] alphabet() {
		int counter = 65;
		char[] capAlphaChArray = new char[26];
		for (int i = 0; i < 26; i++) {
			capAlphaChArray[i] = (char) counter;
			counter++;
		}
		return capAlphaChArray;
	}

//	convert String to char[]

	public char[] convStrToChArray(String str) {
		char[] chArray = new char[str.length()];

		for (int i = 0; i < str.length(); i++) {
			chArray[i] = str.charAt(i);
		}
		return chArray;
	}

//	convert char[] to String

	public String convChArrayToStr(char[] chArray) {
		String str = "";

		for (int i = 0; i < chArray.length; i++) {
			str += chArray[i];
		}
		return str;
	}

// checks if String Not 'A', non repeatable and only Cap Letters

	public boolean strUniqAndOnlyCapLetAndNotA(String str) {
		
		if (this.strIsNotA(str) && this.strHasUniqChars(str) && this.strHasOnlyCapLet(str)) {
			return true;
		} else {
			return false;
		}
	}

// checks if String is not 'ABCDE' and not over 26

	public boolean strIsNotA(String keyword) {
		String alpha = "ABCDEFGHIGKLMNOPQRSTUVWXYZ";
		if (keyword.length() > 26) {
			return false;
		}
		if (keyword.equalsIgnoreCase(alpha.substring(0, keyword.length()))) {
			return false;
		} else {
			return true;
		}
	}

//	checks if String has unique chars

	public boolean strHasUniqChars(String str) {
		boolean bo = true;
		for (int i = 0; i < str.length() - 1; i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if ((int) str.charAt(i) == (int) str.charAt(j)) {
					bo = false;
					break;
				}
			}
		}
		return bo;
	}

//	checks if String has only Capital letters

	public boolean strHasOnlyCapLet(String str) {
		boolean bo = true;
		
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i) > 90) || (str.charAt(i) < 65)) {
				bo = false;
				break;
			}
		}
		return bo;
	}

// create MonoAlphabeticCipher char[] from any String

	public char[] convStrToMonoAlphaCipherChArray(String str) {

		char[] returnThisChArray = this.alphabet();
		char[] strArray = this.convStrToChArray(str);

//		compare the two arrays and if match i-th element of monoCipherArray takes the value of the i-th minus one

		for (int i = 0; i < returnThisChArray.length; i++) {
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j] == returnThisChArray[i]) {
					for (int k = i; k > 0; k--) {
						returnThisChArray[k] = returnThisChArray[k - 1];
					}
				}
			}
		}

//		put the keyword as value of the first elements of encryptArray

		for (int n = 0; n < strArray.length; n++) {
			returnThisChArray[n] = strArray[n];
		}
		return returnThisChArray;
	}

// create my matrix rows from char 

	public char[] matrixRow(char ch) {
		char[] returnThisArray = new char[this.alphabet().length];
		char[] alphabet = this.alphabet();

		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == ch) {
				int counter = i;
				for (int j = 0; j < alphabet.length - i; j++) {
					returnThisArray[j] = alphabet[counter];
					counter++;
				}
				int counterB = 0;
				for (int j = alphabet.length - i; j < alphabet.length; j++) {
					returnThisArray[j] = alphabet[counterB];
					counterB++;
				}
			}
		}
		return returnThisArray;
	}

//	Letter Analysis

	public void writeTheLetterAnalysis(String filePathBufAnalysis, String countedLetters) {

		String writeRowsToFile;
		char[] letter = this.alphabet();
//		populate frequency array
		this.populateFreqArray(countedLetters);
		int[] freq = this.freq;
		double[] freqPerCent = new double[this.alphabet().length];
		double[] avrFreq = this.avgCounts;
		double[] diff = new double[this.alphabet().length];

//		sum the number of all capital letters
		double freqSum = 0;
		for (int i = 0; i < this.alphabet().length; i++) {
			freqSum += freq[i];
		}
//		populate diff array and freqPerCent array
		for (int i = 0; i < 26; i++) {
			freqPerCent[i] = (freq[i] / freqSum) * 100;
			diff[i] = avrFreq[i] - freqPerCent[i];
		}

//		all declared arrays must be with values until now apart of the 'writeRowsToFile'

//		start writing to the output file row by row(writeRowsToFile)
		try {
			fwBufA = new FileWriter(filePathBufAnalysis);
			bufA = new BufferedWriter(fwBufA);
			writeRowsToFile = "LETTER ANALYSIS";
			bufA.write(writeRowsToFile);
			bufA.newLine();
			bufA.newLine();
			writeRowsToFile = "Letter    Freq    Freq%    AvgFreq%    Diff";
			bufA.write(writeRowsToFile);
			bufA.newLine();

			for (int i = 2; i < 28; i++) {

				writeRowsToFile = String.format("   %s       %d       %.1f        %.1f       %.1f", letter[i - 2],
						freq[i - 2], freqPerCent[i - 2], avrFreq[i - 2], diff[i - 2]);
				bufA.write(writeRowsToFile);
				bufA.newLine();

			}

//			define the most frequent letter. if some matching the last one is taken

			writeRowsToFile = String.format("The most frequent letter is %s at %.1f", letter[this.posMostFreqLetter()],
					freqPerCent[this.posMostFreqLetter()]);
			bufA.newLine();
			bufA.write(writeRowsToFile);
			bufA.newLine();
			bufA.flush();
			bufA.close();
		} catch (Exception e) {
			System.out.println("Error when writing to file occurred!");
			e.printStackTrace();
			System.out.println(e);
		}
	}

//	define the position of the most frequent letter

	public int posMostFreqLetter() {
		int pos = 0;
		int max = this.freq[0];
		for (int i = 0; i < 25; i++) {
			if (max < this.freq[i]) {
				max = this.freq[i];
				pos = i;
			} else if (max == this.freq[i]) {
				pos = i;
			}
		}
		return pos;
	}

// transform countedLetters from String into int[]

	public void populateFreqArray(String str) {
		for (int i = 0; i < this.alphabet().length; i++) {
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == this.alphabet()[i]) {
					this.freq[i]++;
				}
			}
		}
	}
}
