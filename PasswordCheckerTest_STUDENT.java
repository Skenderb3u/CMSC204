package passwordChecker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Lirim Krasniqi, CMSC204, Assignment 1
 * These are my written jUnit tests.
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	
	@Before
	public void setUp() throws Exception {
		String[] p = {"Dis1!", "a!132132132", "A!132132132", "A1!abca", "A1!aaaaaaaa", "AaBbCc1!"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Dis1!"));
			assertTrue("Did not throw lengthException", false);
		} catch(LengthException e) {
			assertTrue("Successfully threw a lengthExcepetion", true);
		} catch(Exception e) {
			assertTrue("Threw some other exception besides lengthException",false);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("a!132132132"));
			assertTrue("Did not throw NoUpperAlphaException",false);
		} catch(NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaExcepetion",true);
		}
		catch(Exception e)
		{
			assertTrue("Threw some other exception besides NoUpperAlphaException",false);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("A!132132132"));
			assertTrue("Did not throw NoLowerAlphaException",false);
		} catch(NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaException",true);
		} catch(Exception e) {
			assertTrue("Threw some other exception besides NoLowerAlphaException",false);
		}
	}
	/**
	 * Test if the password has a valid but weak password. 
	 * This test should throw a WeakPasswordException.
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("A1!abca"));
			assertTrue("Did not throw InvalidSequenceException",false);
		} catch(WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException",true);
		} catch(Exception e) {
			assertTrue("Threw some other exception besides WeakPasswordException",false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("A1!aaaaaaaa"));
			assertTrue("Did not throw InvalidSequenceException",false);
		} catch(InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException",true);
		} catch(Exception e) {
			assertTrue("Threw some other exception besides InvalidSequenceException",false);
		}
	}

	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("A!abcabc"));
			assertTrue("Did not throw NoDigitException",false);
		} catch(NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException",true);
		} catch(Exception e) {
			assertTrue("Threw some other exception besides NoDigitException",false);
		}
	}

	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
			try {
				assertTrue(PasswordCheckerUtility.isValidPassword("AaBbCc1!"));
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| NoSpecialCharacterException | InvalidSequenceException e) {
				assertTrue("Threw an exception",false);
			}
			assertTrue("Did not throw an exception",true);
	}

	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "Dis1!");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "a!132132132");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("uppercase"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "A!132132132");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		

		scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "A1!aaaaaaaa");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
		
	}
}
