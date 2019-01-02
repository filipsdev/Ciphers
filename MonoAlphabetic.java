
public class MonoAlphabetic extends SuperClass {

	private String keyword;
	private String countedLetters = "";
//  constructor

	public MonoAlphabetic(String keyword) {
		super();
		this.keyword = keyword;
	}
	
//	getter for countedLetters
	
	public String getCountedLetters() {
		return this.countedLetters;
	}
	
//	do Encryption
 
	public String doEncription(String str) {
		
		char[] encryptThis = super.convStrToChArray(str);
		char[] alphabet = super.alphabet();
		char[] cipher = super.convStrToMonoAlphaCipherChArray(this.keyword);

		for (int i = 0; i < encryptThis.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (encryptThis[i] == alphabet[j]) {
					encryptThis[i] = cipher[j];
					this.countedLetters += cipher[j];
					break;
				}
			}
		}
		return super.convChArrayToStr(encryptThis);
	}
	
//	do Decryption
	
	public String doDecription(String str) {
		
		char[] decryptThis = super.convStrToChArray(str);
		char[] alphabet = super.alphabet();
		char[] cipher = super.convStrToMonoAlphaCipherChArray(this.keyword);

		for (int i = 0; i < decryptThis.length; i++) {
			for (int j = 0; j < alphabet.length; j++) {
				if (decryptThis[i] == cipher[j]) {
					decryptThis[i] = alphabet[j];
					this.countedLetters += cipher[j];
					break;
				}
			}
		}
		return super.convChArrayToStr(decryptThis);
	}
}
