package EncoderAssignment;

public class Encoder {
	
	private static final String refTable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
	
	private String offSetCharacter;
	
	public Encoder(String offSetCharacter) {
		super();
		this.offSetCharacter = offSetCharacter;
	}
	
	public String encode(String plainText) {
		
		if (refTable.contains(offSetCharacter.toUpperCase()) == false) {
			System.out.println("Invalid offset character initialized");
			return plainText;
		}
		
		String unencodedText = plainText.toUpperCase();
		int offSetCharacterIndex = refTable.indexOf(offSetCharacter.toUpperCase());
		String encodedText = "" + offSetCharacter.toUpperCase();
		
		// Calculating the index where the table should be split based on position of the offset character
		// shift is the number of positions that characters are shifted down in the table
		int shift = refTable.length() - offSetCharacterIndex;
		
		// The characters that are shifted down beyond index no. 43 will be brought to the top of the table 
		// shiftedTable is now the encoded table 
		// And the characters in the encoded shiftedTable have aligned indexes with their decoded characters in refTable
		String shiftedTable = refTable.substring(shift) + refTable.substring(0, shift);
		
		for (int i = 0; i < unencodedText.length(); i++) {
			
			// Finding index of unencoded character in refTable
			String unencodedChar = String.valueOf(unencodedText.charAt(i));
			// If character not found in refTable, it is added into encodedText in the same position 
			if (refTable.contains(unencodedChar) == false) {
				encodedText += unencodedChar;
				continue;
			}
			
			// The encoded character in shiftedTable can be found with the same index
			int unencodedCharIndex = refTable.indexOf(unencodedChar);
			encodedText += String.valueOf(shiftedTable.charAt(unencodedCharIndex));
		}
		
		return encodedText;
	}
	
	// The decode method uses the same logic as the encode method
	// The encoded shiftedTable is found using the offset character provided at the start of the encodedText
	// The decoded characters are thus found via the aligning indexes of shiftedTable and refTable
	public String decode(String encodedText) {
		String upperEncodedText = encodedText.toUpperCase();
		String offSetChar = String.valueOf(upperEncodedText.charAt(0));
		
		if (refTable.contains(offSetChar) == false) {
			System.out.println("Invalid offset character given");
			return encodedText;
		}
		
		int offSetCharacterIndex = refTable.indexOf(offSetChar);
		StringBuilder stringBuilder = new StringBuilder(upperEncodedText).deleteCharAt(0);
		String textToDecode = stringBuilder.toString();
		String decodedText = "";
		
		int shift = refTable.length() - offSetCharacterIndex;
		String shiftedTable = refTable.substring(shift) + refTable.substring(0, shift);
		
		for (int i = 0; i < textToDecode.length(); i++) {
			
			String encodedChar = String.valueOf(textToDecode.charAt(i));
			
			// Check for character not in refTable
			if (refTable.contains(encodedChar) == false) {
				decodedText += encodedChar;
				continue;
			}
			
			int encodedCharIndex = shiftedTable.indexOf(encodedChar);
			decodedText += String.valueOf(refTable.charAt(encodedCharIndex));
			
		}
		
		return decodedText;
	}
}
