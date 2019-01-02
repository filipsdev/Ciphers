import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String filePathBuf;
		FileWriter fwBuf = null;
		BufferedWriter buf;
		FileReader fr = null;
		String filePathBufAnalysis;
		String fileName;
		String fileContent = "";
		char isPorC;
		
		Scanner sc = new Scanner(System.in);
		SuperClass sup = new SuperClass();

		System.out.println("enter keyword(only non repeatable capital letters):");
		String keyword = sc.nextLine();

//		while keyword does not cover the requirements loops
		
		while (!sup.strUniqAndOnlyCapLetAndNotA(keyword)) {
			System.out.println("enter keyword(only non repeatable capital letters):");
			keyword = sc.nextLine();
		}
		
		MonoAlphabetic m = new MonoAlphabetic(keyword);
		System.out.println(m.convChArrayToStr(m.convStrToMonoAlphaCipherChArray(keyword)));
		
//		Vignere m = new Vignere(keyword);
//		m.printMyMarix(keyword);
		
		System.out.println("enter file name:");
		
		while(true) {
			try {
				fileName = sc.nextLine();
				isPorC = fileName.charAt(fileName.length() - 1);
				
				if(isPorC == 'P') {
					filePathBuf = fileName.substring(0, fileName.length()-1) + "C" + ".txt";
				}else {
					filePathBuf = fileName.substring(0, fileName.length()-1) + "D" + ".txt";
				}
				filePathBufAnalysis = fileName.substring(0, fileName.length()-1) + "F" + ".txt";
				fileName = fileName + ".txt";
				fr = new FileReader(fileName);
				int i;
				while((i= fr.read()) != -1) {
					fileContent += (char) i;
				}
				break;
			} catch(FileNotFoundException e){
				System.out.println("file not found. try again:");
			}
		}
		try {
			fwBuf = new FileWriter(filePathBuf);
			buf = new BufferedWriter(fwBuf);
				if(isPorC == 'P') {
					buf.write(m.doEncription(fileContent));
				}else {
					buf.write(m.doDecription(fileContent));
				}
			buf.flush();
			buf.close();
		}catch(Exception e) {
			System.out.println("Error when writing to file occurred!");
			e.printStackTrace();
			System.out.println(e);
		}
		sup.writeTheLetterAnalysis(filePathBufAnalysis, m.getCountedLetters());
		sc.close();
	}
}
