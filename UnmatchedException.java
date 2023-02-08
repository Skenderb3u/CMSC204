package passwordChecker;

class UnmatchedException extends Exception {

	public UnmatchedException (String message) {
		super(message);
	}
	
	public UnmatchedException() throws UnmatchedException {
		throw new UnmatchedException("Passwords do not match");
	}
}
