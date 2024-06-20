package EncoderAssignment;

public class Runner {

	public static void main(String[] args) {
		
		// Initialize the offset character for the encoder here
		Encoder encoder = new Encoder("b");
		
		// Encode method
		String encodedText = encoder.encode("hello world ?");
		System.out.println(encodedText);
		
		// Decode method
		String decodedText = encoder.decode(encodedText);
		System.out.println(decodedText);
	}

}
