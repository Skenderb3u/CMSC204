package passwordChecker;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author Lirim Krasniqi, CMSC204, Assignment 1
 * Professor Fouche
 * 
 * This class implements the methods that PasswordChecker uses to verify
 * the validity of passwords. This class also implements methods 
 * that allow the user register passwords as well as allowing users to login
 * if the passwords match.
 * Passwords must have more than 6 characters,
 * one uppercase and lowercase alphabetic letter,
 * one digit and one special character,
 * and no digit should repeat more than twice.
 */
public class PasswordCheckerUtility {

	/**
	 * Checks if the password is valid.
	 * @param the password to check for validity
	 * @return true if password is valid, false if invalid.
	 * @throws
	 * LengthException - thrown if length is less than 6 characters.
	 * NoUpperAlphaException - thrown if no uppercase alphabetic.
	 * NoLowerAlphaException - thrown if no lowercase alphabetic.
	 * NoDigitException - thrown if no digit.
	 * NoSpecialCharacterException - thrown if does not meet 
	 * SpecialCharacter requirement.
	 * InvalidSequenceException - thrown if more than 2 of same character.
	 */
	public static boolean isValidPassword(String password) throws 
	LengthException, NoUpperAlphaException, NoLowerAlphaException, 
	NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		if (isValidLength(password) 
				&& hasUpperAlpha(password) 
				&& hasLowerAlpha(password) 
				&& hasDigit(password) 
				&& hasSpecialChar(password) 
				&& NoSameCharInSequence(password)) {
			return true;
		} else {
			return false;
		}
	}

	/** Checks if the password is valid and not between 6-9 characters, 
	 * @param the password to check for weak password.
	 * @return false if password is valid and length is not between
	 * 6-9 characters inclusive.
	 * @throws WeakPasswordException if password is between 
	 * 6-9 inclusive and invalid.
	 */
	public static boolean isWeakPassword(String password)
			throws WeakPasswordException {
		
			if (hasBetweenSixAndNineChars(password)) {
				throw new WeakPasswordException("The password is OK but weak -"
						+ " it contains fewer than 10 characters.");
			} else if (!(hasBetweenSixAndNineChars(password))) {
				return false;
			}
		return false;
	}

	/** 
	 * Returns a list of all invalid passwords.
	 * @param an ArrayList of passwords
	 * @return an arraylist of any invalid passwords.
	 */
	public static ArrayList<String> getInvalidPasswords
	(ArrayList<String> passwords) {
		
		ArrayList<String> invalid = new ArrayList<String>();
		String reason = null;
		for (int i = 0; i < passwords.size(); i++) {

			try {
				isValidPassword(passwords.get(i));
			} catch (LengthException e) {
				reason = passwords.get(i) + " The password must be at "
						+ "least 6 characters long";
				invalid.add(reason);
			} catch (NoUpperAlphaException e) {
				reason = passwords.get(i) + " The password must contain at "
						+ "least one uppercase alphabetic character";
				invalid.add(reason);
			} catch (NoLowerAlphaException e) {
				reason = passwords.get(i) + " The password must contain at "
						+ "least one lowercase alphabetic character";
				invalid.add(reason);
			} catch (NoDigitException e) {
				reason = passwords.get(i) + " The password must contain at "
						+ "least one digit";
				invalid.add(reason);
			} catch (NoSpecialCharacterException e) {
				reason = passwords.get(i) + " The password must contain at "
						+ "least one special character";
				invalid.add(reason);
			} catch (InvalidSequenceException e) {
				reason = passwords.get(i) + " The password cannot contain "
						+ "more than two of the same character in sequence";
				invalid.add(reason);
			}
		}
		return invalid;
	}

	/** Checks if the two passwords are equal.
	 * @param the password and the passwordConfirm to be checked against.
	 * @throws UmatchedException if passwords are not the same.
	 */
	public static void comparePasswords(String password, 
			String passwordConfirm) throws UnmatchedException {

		if (!(password.equals(passwordConfirm))) {
			throw new UnmatchedException("Passwords do not match");
		} 
	}

	/** Compare equality of the two passwords.
	 * @param the password and the passwordConfirm to be checked against.
	 * @return true if the two passwords are equal, false otherwise. 
	 */
	public static boolean comparePasswordsWithReturn(String password,
			String passwordConfirm) {

		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			return false;
		}
	}

	/** Checks if password is at least 6 characters long
	 * @param the password to be checked for length.
	 * @return true if password meets min length requirement.
	 * @throws LengthException if password is less than 6 character.
	 */
	public static boolean isValidLength(String password)
			throws LengthException {
		if (password.length() < 6) {
			throw new LengthException("The password must be"
					+ " at least 6 characters long");
		} else {
			return true;
		}
	}

	/** Checks if password has at least one uppercase alpha character
	 * @param password to check for alpha character uppercase requirement.
	 * @return true if uppercase alpha character is met.
	 * @throws NoUpperAlphaException if password 
	 * does not meet uppercase alpha character requirement.
	 */
	public static boolean hasUpperAlpha(String password)
			throws NoUpperAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoUpperAlphaException(" The password must contain at "
				+ "least one uppercase alphabetic character");

	}

	/** Checks if password has at least one lowercase alpha character
	 * @param password to check for lowercase alpha character requirement.
	 * @return true if lowercase alpha character is met.
	 * @throws NoLowerAlphaException if password 
	 * does not meet lowercase alpha character requirement.
	 */
	public static boolean hasLowerAlpha(String password)
			throws NoLowerAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoLowerAlphaException(" The password must contain at "
				+ "least one lowercase alphabetic character");
	}

	/** Checks if password has a numeric character.
	 * @param password to check for numeric character.
	 * @return true if numeric character requirement is met.
	 * @throws NoDigitException if numeric character requirement is not met.
	 */
	public static boolean hasDigit(String password) throws NoDigitException {

		for (int i = 0; i < password.length(); i++) {
			if (Character.isDigit(password.charAt(i))) {
				return true;
			}
		}
		throw new NoDigitException(" The password must contain "
				+ "at least one digit");
	}

	/** Checks if password has at least one special character,
	 * @param password to check for special character.
	 * @return true if special character requirement is met.
	 * @throws NoSpecialCharacterException if special character
	 * requirement is not met.
	 */
	public static boolean hasSpecialChar(String password)
			throws NoSpecialCharacterException {

		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			return true;
		} else {
			throw new NoSpecialCharacterException(" The password must contain "
					+ "at least one special character");
		}
	}

	/** 
	 * Checks for two repeating characters in sequence.
	 * @param password to check for repeating characters.
	 * @return true if no repeating characters, false otherwise.
	 * @throws InvalidSequenceException if there are 2 repeating characters
	 * in sequence in the password. 
	 */
	public static boolean NoSameCharInSequence(String password)
			throws InvalidSequenceException {

		for (int i = 2; i < password.length(); i++) {
			char prevChar = password.charAt(i-1);
			char prevPrevChar = password.charAt(i-2);
			if (password.charAt(i) == prevChar && password.charAt(i) == prevPrevChar) {
				throw new InvalidSequenceException(" The password cannot contain more "
						+ "than two of the same character in sequence");
			}
		}
		return true;

	}

	/** Checks if the password has between 6-9 characters
	 * @param password to check for length.
	 * @return true if between 6-9 characters, false otherwise.
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() >= 6 && password.length() <= 9) {
			return true;
		} else {
			return false;
		}
	}
}


