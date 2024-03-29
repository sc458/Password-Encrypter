package crypt;

import java.security.SecureRandom;
import static java.lang.Math.*;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**	class PasswordSecurity
 * 
 * This class contains auxillary functions to determine the security 
 * of a password. Among others we can investigate a given password for
 * internal patterns, frequently used keywords, personal date (birthdays),
 * or keyboard pattern. This helps the user to choose a more secure
 * password. Further, we have a random password generator as well.
 * 
 */
public class PasswordSecurity {
	private static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
	private static final String space = " ";
	private static final String numbers = "1234567890";
	private static final String symbols = "!"+'"'+"#$%&'()*+,-./:;<=>?@[~`]^_{|}\\";
	
	private static final String keyShiftRight = "snvfrghjokl;,mp[wtdyibecux";
	private static final String keyShiftUpLeft = "qgde3rty8uiojh9014w57f2s6a";
	private static final String keyShiftUpRight = "whfr4tyu9iopkj0-25e68g3d7s";
	private static final String keyShiftUpLeftSymbols = "qgde#rty*uiojh()!$w%&f@s^a";
	private static final String keyShiftUpRightSymbols = "whfr$tyu(iopkj)_@%e^*g#d&s";
	
	private static final String exchangeA = "aA@";
	private static final String exchangeC = "Cc(";
	private static final String exchangeE = "eE3";
	private static final String exchangeL = "lL1[";
	private static final String exchangeS = "sS5$";
	private static final String exchangeV = "vV^";
	private static final String exchangeX = "xX+";
	
	private static final String upperregex = "[A-Z]";
	private static final String lowerregex = "[a-z]";
	private static final String numberregex = "\\d";
	private static final String symbolregex = "[^A-Za-z\\d ]";
	
	private static final int numberLetters = 26;
	private static final int numberNumbers = 10;
	private static final int numberSymbols = symbols.length();
	
	private static final double entropyNumbers = log(10.0)/log(2.0);
	private static final double entropyLetters = log(26.0)/log(2.0);
	private static final double entropySymbols = log((float) numberSymbols)/log(2.0);
	
	private static final String deathFile = "deathWords.lib";
	private static final String keyboardFile = "keyboardPatterns.lib";
	private static final String patternFile = "wordPatterns.lib";
	
	private static final byte spByte = -17;
	private static final String spaceholder	= ""+((char) spByte);
	private static final byte deByte = -16;
	private static final String devider = ""+((char) deByte)+((char) deByte);
	
	/**	keyShift()
	 * 
	 * Given a String instance (a password), this function proposes a
	 * keyshift on the keyboard in a given direction to add a slight
	 * level of complexity to a password or in reverse to investigate,
	 * if someone took an easy password and just shifted it.
	 * 
	 * @param	password		String		Password to be shifted.
	 * 
	 * @param	direction		String		Direction on the keyboard.
	 * 										String has to be one of the options
	 * 										'r'  : shift one key to the right
	 * 										'ul' : ...to the upper left
	 * 										'ur' : ...to the upper right
	 * 										'uls': ...upper left including symbols
	 * 										'urs': ... upper right including symbols
	 * 
	 * @warning: this routine has not been tested extensively
	 * TODO: test routine
	 */
	private static String keyShift(String password, String direction){
		String shift = "";
		if (direction.equals("r")) shift = keyShiftRight;
		if (direction.equals("ul")) shift = keyShiftUpLeft;
		if (direction.equals("ur")) shift = keyShiftUpRight;
		if (direction.equals("uls")) shift = keyShiftUpLeftSymbols;
		if (direction.equals("urs")) shift = keyShiftUpRightSymbols;
		
		if (shift == "") return "-1";

		String proposal = "";
		for (int ii=0; ii<password.length(); ii++){
			byte what = (byte) password.charAt(ii);
			if ( what <= 122 && what >= 97 ) {
				proposal += shift.charAt(what-97);
				continue;
			}
			if ( what <= 90 && what >= 65 ) {
				String add = ""+shift.charAt(what-65);
				proposal += add.toUpperCase();
				continue;
			}
			proposal += (char) what;
		}

		return proposal;
	}
	
	/**	proposeCharExchange()
	 * 
	 * Given a String instance (a password), this function proposes an
	 * other String in which some of the characters are replaced by
	 * similar symbols (e.g. a -> @, s -> $) to add a slight
	 * level of complexity to a password or in reverse to investigate,
	 * if someone took an easy password and just modified it.
	 * 
	 * @param	password		String		Password to be shifted.
	 * 
	 * @warning: this routine has not been tested extensively
	 * TODO: test routine
	 */
	private static String proposeCharExchange(String password){
		String temp = password.toLowerCase();
		String proposal = "";
		for (int ii=0; ii<password.length(); ii++){
			switch ( temp.charAt(ii) ){
			case 'a': 
				proposal += randomGrab(exchangeA, 1);
				continue;
			case 'c':
				proposal += randomGrab(exchangeC, 1);
				continue;
			case 'e':
				proposal += randomGrab(exchangeE, 1);
				continue;
			case 'l':
				proposal += randomGrab(exchangeL, 1);
				continue;
			case 's':
				proposal += randomGrab(exchangeS, 1);
				continue;
			case 'v':
				proposal += randomGrab(exchangeV, 1);
				continue;
			case 'x':
				proposal += randomGrab(exchangeX, 1);
				continue;
			}
			proposal += password.charAt(ii);			
		}
		return proposal;
	}
	
	/**	createRegex()
	 * 
	 * Given a String instance (a password), this function creates a 
	 * regular expression regarding lowercase, uppercase, digits, and
	 * symbols. With this regex, a pattern with respect to the use of
	 * digits, symbols, lower-/uppercase can be investigated
	 * 
	 * @param	pattern		String		Password to be encoded in a regex.
	 * 
	 */
	private static String createRegex(String pattern) {
		/* Patterns : 	l = lowercase
		 * 				u = uppercase
		 * 				d = digits/numbers
		 * 				s = symbols
		 * 				. = arbitrary digit
		 */
		
		String regex = "";
		int pp=0;
		while (pp < pattern.length()){
			switch (pattern.charAt(pp)){
			case ((char) 'l'):
				regex += lowerregex;
				pp++;
				continue;
			case 'u':
				regex += upperregex;
				pp++;
				continue;
			case 'd':
				regex += numberregex;
				pp++;
				continue;
			case 's':
				regex += symbolregex;
				pp++;
				continue;
			case '.':
				regex += ".";
				pp++;
				continue;
			case ' ':
				regex += " ";
				pp++;
				continue;
			case '[':
				regex += '[';
				while(pp < pattern.length()){
					pp++;
					regex += pattern.charAt(pp);
					if(pattern.charAt(pp) == ']') break;
					if(pp == pattern.length()) return "]";
				}
				pp++;
				continue;
			default:
				regex += pattern.charAt(pp);
				pp++;
			}
		}
		return regex;
	}
	
	/**	splitbyPattern()
	 * 
	 * Given a String instance (a password), and the detected patterns
	 * this function splits the input String acording to its patterns
	 * in order to investigate each subpart of the pattern afterwards. 
	 * 
	 * To do so, this function calls the iterative function 
	 * getSplitByPattern() below.
	 * 
	 * @param	text		String		Password to be splitted.
	 * 	
	 * @param	patterns	String[]	Detected patterns.
	 * 
	 */
	private static String[] splitbyPattern(String text, String patterns[]) {
		if ( text == null || text.length() == 0) return new String[] {""};
		
		int minPatternSize = 4;
		String out[] = {text};
		boolean root[] = {false};
		for (int ii=0; ii<patterns.length; ii++){
			String target = createRegex(patterns[ii]);
			String newout[] = {};
			boolean newroot[] = {};
			boolean sizeflag = true;
			for (int kk=0; kk<out.length; kk++) {
				if ( out[kk].length() < minPatternSize || root[kk]) {
					newout = concate(newout, out[kk]);
					newroot = concate(newroot, true);
					continue;
				}
				String newsplit[] = getSplitbyPattern(out[kk], target);
				boolean newroots[] = new boolean[newsplit.length];
				for (int nn=0; nn<newsplit.length; nn++) {
					if (newsplit[nn].matches(target)) newroots[nn] = true;
				}
				newout = concate(newout, newsplit);
				newroot = concate(newroot, newroots);
				sizeflag = false;
			}
			out = newout;
			root = newroot;
			if ( sizeflag ) break;
		}
		return out;		
	}
	
	/**	getSplitbyPattern()
	 * 
	 * Iterative function to split a given String instance (a password). 
	 * 
	 * @param	input		String		String to be splitted.
	 * 	
	 * @param	target		String		Target patterns to split for.
	 * 
	 */
	private static String[] getSplitbyPattern(String input, String target) {
		if (input == null || input.length() == 0) return new String[] {""};

		String before = input;
		input = input.replaceFirst(target, devider);
		
		String[] split = input.split(devider);
		if ( split == null || split.length == 0 ) return new String[] {input};
		
		if (split.length == 1) {
			if( before.length() == split[0].length() ) return split;
		
			if (before.startsWith(split[0])) {
				String outL[] = getSplitbyPattern(split[0], target);
				return concate(outL, before.substring(split[0].length()));	
			} else {
				String outR[] = getSplitbyPattern(split[0], target);
				return concate(before.substring(0, before.length() - split[0].length()), outR);
			}
		}
		
		String outL[] = getSplitbyPattern(split[0], target);
		String outR[] = getSplitbyPattern(split[1], target);
		return concate(outL, before.substring(split[0].length(), before.length() - split[1].length()), outR);
	}
	
	/**	equalsPattern()
	 * 
	 * Given a String instance and certain patterns, this function
	 * checks if the String instance exactly fulfills all the patterns
	 * and retruns a boolean accordingly. 
	 * 
	 * @param	text		String		Instance to be investigated.
	 * 	
	 * @param	patterns	String[]	Patterns to be applied to text.
	 * 
	 */
	private static boolean equalsPattern(String text, String patterns[]){
		/* Patterns : 	l = lowercase
		 * 				u = uppercase
		 * 				d = digits/numbers
		 * 				s = symbols
		 * 				. = arbitrary digit
		 */
		
		for (int ii=0; ii<patterns.length; ii++){
			String target = createRegex(patterns[ii]);
			if ( text.matches(target) ) return true;
		}
		return false;
	}
	
	/**	equalsPattern()
	 * 
	 * Given a String instance and certain patterns, this function
	 * checks if the String instance contains all the patterns and
	 * retruns a boolean accordingly. 
	 * 
	 * @param	text		String		Instance to be investigated.
	 * 	
	 * @param	patterns	String[]	Patterns to be applied to text.
	 * 
	 */
	private static boolean containsPattern(String text, String patterns[]){
		/* Patterns : 	l = lowercase
		 * 				u = uppercase
		 * 				d = digits/numbers
		 * 				s = symbols
		 * 				. = arbitrary digit
		 */
		
		for (int ii=0; ii<patterns.length; ii++){
			String target = ".*" + createRegex(patterns[ii])+".*";
			if ( text.matches(target) ) return true;
		}
		return false;
	}
	
	/**	evaluateDistribution()
	 * 
	 * Empirical function to evaluate the distribution of lower/-upper case,
	 * digits and symbols. This score goes in to the final evaluation of the
	 * strength (or randomness) of a human chosen password.
	 * 
	 */
	private static double evaluateDistribution(int[] array, int[] arg){
		if (array.length < 2) return 0.0;
		
		int counter = 0;
		int pos[] = new int[array.length];
		
		for (int ii=0; ii<array.length; ii++) {
			for (int kk=0; kk<arg.length; kk++) {
				if (array[ii] == arg[kk]) {
					pos[counter] = ii;
					counter += 1;
				}
			}
		}
		
		if (counter == 0) return 0.0;
		
		//System.out.println(pos[0]+"	"+pos[counter-1]+"	"+counter);
		double result = ((double) (pos[counter-1] - pos[0] + 1))/counter;
		//System.out.println(result);
		
		result += (double) pos[0]*(1.0 - (double) pos[counter-1]/(array.length-1));
		//System.out.println((double) pos[0]*(1.0 - (double) pos[counter-1]/(array.length-1)));
		//System.out.println(result);
		
		return 1+log(result);	
	}

	/**	estimatePwStrength()
	 * 
	 * Function to evaluate the strength of a humanly chosen password.
	 * This includes taking into account the length, investigating it
	 * for patterns and for the use of lower-/upper case, digits and 
	 * symbols. In the end the score is from 0 to infinite, while a
	 * score around 80 should be a decent password and a score over
	 * 100 a very strong one.
	 *  
	 * @param	password		String		Instance to be investigated.
	 * 
	 */
	public static double estimatePwStrength(String password){
		int size = password.length();
		if ( size == 0 || password == null) return -1.0;
		if ( size == 1) return 0.0;
		
		int cat[] = new int[size];
		boolean foundLower = false, foundUpper = false,
				foundNumbers = false, foundSymbols = false;
		
		int countLower = 0, countUpper = 0, countNumbers = 0;
		byte bytePass[] = new byte[size]; 
		
		for (int ii=0; ii<size; ii++){
			bytePass[ii] = (byte) password.charAt(ii);
			
			if ( bytePass[ii] < 32 || bytePass[ii] > 126) {
				cat[ii] = 0;
				foundSymbols = true;
			} else {			
				if( bytePass[ii] <= 57 && bytePass[ii] >= 48) {
					cat[ii] = 1;
					foundNumbers = true;
					countNumbers += 1;
				} else {
					if ( bytePass[ii] <= 90 && bytePass[ii] >= 65 ) {
						cat[ii] = 2;
						foundUpper = true;
						countUpper += 1;
					} else {
						if ( bytePass[ii] <= 122 && bytePass[ii] >= 97 ) {
							cat[ii] = 3;
							foundLower = true;
							countLower += 1;
						} else {
							cat[ii] = 0;
							foundSymbols = true;
						}
					}
				}
			}
		}
		
		double entropy = 1.0;
		
		// Include mean distance of Numbers, Symbols, ...
		double strength = entropyLetters*(2*size - countUpper - countLower - max(countUpper,countLower))*evaluateDistribution(cat, new int[] {2,3});
		strength += entropyNumbers*countNumbers*evaluateDistribution(cat, new int[] {1});
		strength += entropySymbols*(size - countUpper - countLower - countNumbers)*evaluateDistribution(cat, new int[] {0});
		
		if(foundSymbols) entropy += numberSymbols;
		if(foundLower) entropy += numberLetters;
		if(foundUpper) entropy += numberLetters;
		if(foundNumbers) entropy += numberNumbers;
		
		entropy = log(entropy)/log(2.0);
		strength = (strength/size + entropy) * (size-1);
		
		// Analyse Patterns within the password
		String Patterns[] = patternAnalysis(password, cat, bytePass);
		int deadDigits = Integer.parseInt(Patterns[0]);
		
		if ( deadDigits == 0 ) return strength;

//		System.out.println(password);
		pr_Str(Patterns, "Detected Patterns:");
		double scale = (double) (size-deadDigits-1)/(size-1);
//		System.out.println(String.format("The Patterns weaken your password strength by %6.2f", 100*(1-scale))+"%");
//		System.out.println("Strength:	"+(scale*strength));
		
//		scale = (double) sqrt( (double) (size-deadDigits)*(size-deadDigits-1))/size;
//		System.out.println(String.format("The Patterns weaken your password strength by %6.2f", 100*(1-scale))+"%");
//		System.out.println("Strength:	"+(scale*strength));
		/*if (strength < 50){
			System.out.println("To increase the password strength you might try one of those proposals:");
			System.out.println("Exchange characters to similar symbols:"+proposeCharExchange(password));
			System.out.println("Perform a certain key shift on the letters with respect to your keyboard:");
			System.out.println("	... to the right:"+keyShift(password, "r"));
			System.out.println("	... to the upper-left:"+keyShift(password, "ul"));
			System.out.println("	... to the upper-right:"+keyShift(password, "ur"));
			System.out.println("	... to the upper-left using symbols:"+keyShift(password, "uls"));
			System.out.println("	... to the upper-right using symbols:"+keyShift(password, "urs"));
			System.out.println("	(note that your input was shifted with respect to the keybordpattern)");
			System.out.println("!!! BUT :  Keep in mind that those proposals still are not as secure as a completely random password!");
		}
		*/
//		if (strength > 100){System.out.println(password);}
		
		return scale*strength;
	}
	
	/**	getStringOf()
	 * 
	 * Returns a String of repeating characters
	 *  
	 * @param	instance		String		Instance to be repeated.
	 * 
	 * @param	repetition		int			Number of repetitions of instance.
	 * 
	 */
	public static String getStringOf(String instance, int repetition) {
		if (repetition == 0) return "";
		return new String(new char[repetition]).replace("\0", instance);
	}
	
	/**	combineStringArrays()
	 * 
	 * Combines two String arrays. Each element of the second String will
	 * be attached to each element of the first string in the output.
	 * 
	 * If the optional flag symmetric is true, all elements in the first
	 * String will be as well attached to all elements of the second one.
	 *  
	 * @param	first		String[]		First string array.
	 * 
	 * @param	second		String[]		Second string array.
	 * 
	 * @param 	symmetric	boolean			(optional) Flag for symmetric combination
	 * 
	 */
	public static String[] combineStringArrays(String[] first, String[] second){
		return combineStringArrays(first, second, false);
	}

	
	public static String[] combineStringArrays(String[] first, String[] second, boolean symmetric){
		int firstSize = first.length;
		int secondSize = second.length;
		
		int size = firstSize*secondSize;
		String out[];
		if (symmetric) {
			out = new String[2*size];
		} else {
			out = new String[size];
		}
		int ind = 0;
		for (int ff=0; ff<firstSize; ff++){
			for (int ss=0; ss<secondSize; ss++){
				out[ind] = first[ff]+second[ss];
				ind++;
			}
		}
		if (symmetric) {
			ind = size;
			for (int ss=0; ss<secondSize; ss++){
				for (int ff=0; ff<firstSize; ff++){
					out[ind] = second[ss]+first[ff];
					ind++;
				}
			}
		}
		return out;
	}
	
	/**	getRandomPassword()
	 * 
	 * Returns a random password from a seed which can be chosen.
	 *  
	 * @param	size		int			Length of the password
	 * 
	 * @param	useSpace	boolean		Flag to include space in the seed.
	 * 
	 * @param	useNumbers	boolean		Flag to include numbers in the seed.
	 * 
	 * @param	useSymbols	boolean		Flag to include symbols in the seed.
	 * 
	 * @param	addchars	String		(optional) String of additional characters to add to seed.
	 * 
	 */
	public static String getRandomPassword(int size, boolean useSpace, 
			boolean useNumbers, boolean useSymbols, String addchars){
		String grab = uppercase+lowercase;
		if(useSpace){
			grab = grab+space;
		}
		if(useNumbers){
			grab = grab+numbers;
		}
		if(useSymbols){
			grab = grab+symbols;
		}
		grab = grab+addchars;
		
		return randomGrab(grab, size);
	}
	
	public static String getRandomPassword(int size,  boolean useSpace, 
			boolean useNumbers, boolean useSymbols){
		return getRandomPassword(size, useSpace, useNumbers, useSymbols, "");
	}
	
	/**	patternAnalysis()
	 * 
	 * Function to detect different patterns in a given String instance.
	 * The character categories have to be given as well. Each character
	 * thereby is categorised by symbol (0), number (1), uppercase (2) or
	 * lowercase (4). The password has to be given as well converted into
	 * an array of bytes, with each element corresponding to one character.
	 *  
	 * @param	password	String		Instance to be investigated.
	 *  
	 * @param	category	int[]		ii-th element describes the category for
	 * 									the ii-th character of the password.
	 * 
	 * @param	PWDinBytes	byte[]		ii-th element describes the ii-th character
	 * 									of the password converted into bytes.
	 * 
	 */
	public static String[] patternAnalysis(String password, int category[], byte[] PWDinBytes) {
		int size = password.length();
		
		if ( size == 0 || password == null) return null;
		if ( size == 1) return null;
		
		String death[] = getCriticalPatternsbyFile(deathFile, password);
		String key[] = getCriticalPatternsbyFile(keyboardFile, password);
		String criticals[] = concate(death, concate(key, getCriticalPatternsbyFile(patternFile, password)));

		String filePatterns[];
		String birthdayPatterns[] = getBirthdayPatterns();
		String Patterns[] = splitbyPattern(password, birthdayPatterns);
		
		String splitPassword[] = {};
		
		
		//Splitting password by birthday patterns and death words
		boolean isPattern[] = {};
		for (int ii=0; ii<Patterns.length; ii++) {
			if (equalsPattern(Patterns[ii], birthdayPatterns)) {
		//		System.out.print("Found:	"+Patterns[ii]+"	(Birthday pattern)");
				isPattern = concate(isPattern, true);
				splitPassword = concate(splitPassword, Patterns[ii]);
				continue;
			}
			filePatterns = reducePasswordbyFilePatterns(Patterns[ii], criticals);
			splitPassword = concate(splitPassword, filePatterns);
			boolean temp[] = new boolean[filePatterns.length];
			for (int kk=0; kk<filePatterns.length; kk++) {
				if ( contains(criticals, filePatterns[kk]) ) {
		//			System.out.print("Found:	"+filePatterns[kk]+"	");
					if ( contains(death, filePatterns[kk]) ) {
		//				System.out.println("(Death-word)");
					} else {
						if ( contains(key, filePatterns[kk]) ) {
		//					System.out.println("(Keyboard pattern)");
						} else { //System.out.println("(Common word pattern)");
						}
					}
					temp[kk] = true;
				}
			}
			isPattern = concate(isPattern, temp);
		}
//		pr_Str(splitPassword, "Split PWD:");
		
		
		// Looking for patterns within the split password
		String patt = "";
		for (int bb = 1; bb <= splitPassword.length/2; bb++){
			int ind;
			String catPatt[] = new String[bb];
			boolean foundPatt[] = new boolean[bb];
			int count[] = new int[bb];
			for (int ii=bb; ii<splitPassword.length; ii++) {
				ind = ii%bb;
				if (splitPassword[ii] == null || splitPassword[ii].length() < 3) {
					foundPatt[ind] = false;
					count[ind] = 0;
					continue;
				}
				if ( isPattern[ii-bb] == isPattern[ii] ) {
					if (splitPassword[ii-bb].equals(splitPassword[ii])) {
						if ( !foundPatt[ind] ) {
							catPatt[ind] = getStringOf(spaceholder, (ii-bb)) + "x";
						}
						catPatt[ind] += getStringOf(spaceholder, (bb-1))+"x";
						foundPatt[ind] = true;
						continue;
					}
				}
				
				if ( foundPatt[ind] ) {
					patt += devider+catPatt[ind].replace("x", splitPassword[ii-bb])+spaceholder;
			//		System.out.println("Found:	"+catPatt[ind].replace("x", splitPassword[ii-bb])+spaceholder);
				}
				foundPatt[ind] = false;
				count[ind] = 0;
			}
			for (int ii=0; ii<bb; ii++){
				if ( foundPatt[ii] ) {
					if( ii == bb) {
			//			System.out.println("Found:	"+catPatt[ii].replace(spaceholder, "*").replace("x", splitPassword[ii]));
						patt += devider+catPatt[ii].replace("x", splitPassword[ii]);
					} else {
			//			System.out.println("Found:	"+catPatt[ii].replace(spaceholder, "*").replace("x", splitPassword[ii])+"*");
						patt += devider+catPatt[ii].replace("x", splitPassword[ii])+spaceholder;
					}
				}
				foundPatt[ii] = false;
				count[ii] = 0;
			}
		}

		if ( patt.equals("") ) {
			Patterns = new String[] {};
		} else {
			if ( patt.startsWith(devider) ) patt = patt.substring(devider.length());
			Patterns = patt.split(devider);
		}
//		pr_Str(Patterns, "Patterns:");
		
		
		// Looking for patterns in the complete password
		filePatterns = findPatterns(password, category, PWDinBytes, true);		
		int countNumbers = Integer.parseInt(filePatterns[0]);	
		death = new String[filePatterns.length-1];
		for (int ii=1; ii < filePatterns.length; ii++){
			death[ii-1] = fill(filePatterns[ii], size, spaceholder);
		}
//		pr_Str(filePatterns, "Detected Patterns:");
		filePatterns = concate(Patterns, death);
		
		
		// Looking for patterns in the reduced password
		if ( splitPassword.length > 1 ) {
			String modPass = "";
			int ind = 0, inred = 0;
			byte inByte[] = new byte[size];
			int cat[] = new int[size];
			for (int ii=0; ii < splitPassword.length; ii++) {
				for (int kk = 0; kk<Patterns.length; kk++) {
					if ( Patterns[kk] == null || Patterns[kk].length() <= ind) continue;
					if ( Patterns[kk].charAt(ind) == spaceholder.charAt(0) ) {
						Patterns[kk] = Patterns[kk].substring(0, ind) + getStringOf(spaceholder, splitPassword[ii].length()) + Patterns[kk].substring(ind+1);
					}
				}
				
				if( isPattern[ii] ) {
					ind += splitPassword[ii].length();
					continue;
				}
					
				modPass += splitPassword[ii];			
				for (int kk=0; kk < splitPassword[ii].length(); kk++) {
					PWDinBytes[inred] = PWDinBytes[ind];
					category[inred] = category[ind];
					inred++;
					ind++;
				}
			}
			
			Patterns = findPatterns(modPass, category, PWDinBytes, false);
//			pr_Str(filePatterns, "File Patterns:");
			ind = 0;
			inred = 0;
			for (int ii=0; ii < splitPassword.length; ii++) {
				if( isPattern[ii] ) {
					for (int kk=1; kk<Patterns.length; kk++) {
						if (Patterns[kk].length() < inred) continue;
						Patterns[kk] = Patterns[kk].substring(0,inred) 
								+ getStringOf(spaceholder, splitPassword[ii].length()) 
								+ Patterns[kk].substring(inred);
					}
					inred += max(splitPassword[ii].length(), 0);
					continue;
				}
				inred += splitPassword[ii].length();
			}
			
			death = new String[Patterns.length-1];
			for (int ii=1; ii < Patterns.length; ii++){
				death[ii-1] = fill(Patterns[ii], size, spaceholder);
			}
//			pr_Str(filePatterns, "Detected Patterns:");
			
			filePatterns = concate(filePatterns, death);
		}
		
		
		// Sorting detected patterns with respect to digits occuring in the pattern
		Arrays.sort(filePatterns, new java.util.Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		    	if (s1 == null && s2 == null) return 0;
		    	if (s1 == null) return - ( s2.length() - s2.replace(spaceholder, "").length() );
		    	if (s2 == null) return s1.length() - s1.replace(spaceholder, "").length();
		    	
		        return s1.length() - s1.replace(spaceholder, "").length() - ( s2.length() - s2.replace(spaceholder, "").length() );
		    }
		});
//		pr_Str(filePatterns, "banana Patterns:");
		
	
		// Merging everything together
		boolean partOfPattern[] = new boolean[size];
		if ( countNumbers > 1 ) {
			for (int ii=0; ii<countNumbers; ii++) {
				partOfPattern[ii] = true;
			}
			Patterns = new String[] {(password.substring(0,countNumbers)
					+getStringOf(spaceholder, size-2*countNumbers)
					+password.substring(0,countNumbers))};
		} else { 
			Patterns = new String[] {};
			countNumbers = 0;
		}
		
		for (int ii=0; ii<filePatterns.length; ii++) {
			if ( filePatterns[ii].replace(spaceholder,"").length() < 3 ) break;
			
			boolean cflag = false;
			String temp = shiftOutRegex(filePatterns[ii]).replace(spaceholder, ".");
			for (int kk = 0; kk<ii; kk++) {
				if ( filePatterns[kk].matches(temp) ) {
					cflag = true;
					break;
				}
			}
			if(cflag) continue;
			
			death = filePatterns[ii].split(spaceholder);
			cflag = false;
			for (int kk=0; kk<death.length; kk++) {
				if ( death[kk].length() == 0) continue;
				if ( contains(criticals, death[kk]) ) {
					cflag = true;
					temp = death[kk];
					isPattern[getIndex(splitPassword, temp)] = false;
				}
				break;
			}
			

			int counter = 0, len = 0;
			for (int kk=0; kk<size; kk++) {
				if ( filePatterns[ii].charAt(kk) != spaceholder.charAt(0)) {
					if ( partOfPattern[kk] ) counter++;
					len++;
					partOfPattern[kk] = true;
				}
			}
			
			counter = max(1,counter);
			if ( !cflag ) {
				temp = filePatterns[ii].replace(spaceholder,"");
				if ( !( ((byte) temp.charAt(0) - (byte) temp.charAt(1))/2 == 0 || 
						shiftOutRegex(filePatterns[ii]).matches(".*"+temp+".*") ) ) {
					counter = max(2,counter);
				}
			}
			countNumbers += len - counter;
			
			Patterns = concate(Patterns, filePatterns[ii]);
		}
		
		
		// Reducing for deathwords
		// TODO: Watch for partOfPattern within the unpatterned deathwords
		for (int ii=0; ii < splitPassword.length; ii++) {
			if ( isPattern[ii] ) countNumbers += splitPassword[ii].length() -1;
		}
//		pr_Str(Patterns, "Detected Patterns:");
//		System.out.println("Dead Digits:	"+countNumbers);
		
		return concate(Integer.toString(countNumbers), Patterns);
	}
	
	/**	shiftOutRegex()
	 * 
	 * Converting special characters in a String for a regex expression.
	 * E.g. ? -> \\?, | -> \\|, * -> \\*, etc.
	 *  
	 * @param	target		String		Instance to be changed.
	 * 
	 */
	private static String shiftOutRegex(String target) {
		return target.replace("\\", "\\\\").replace(".", "\\.").replace("{", "\\{").replace("}", "\\}").replace("[", "\\[")
					.replace("]", "\\]").replace("(", "\\(").replace(")", "\\)").replace("^", "\\^")
					.replace("$", "\\$").replace("|", "\\|").replace("*", "\\*").replace("+", "\\+")
					.replace("?", "\\?");
	}
	
	/**	getIndex()
	 * 
	 * Returns index of first occurance of a given String element in a 
	 * given list or -1, if the element is not included in the list. 
	 *  
	 * @param	list		String[]	List to be searched.
	 *  
	 * @param	target		String		String instance to be looked for.
	 * 
	 */
	private static int getIndex(String[] list, String target) {
		if ( list == null || list.length == 0) return -1;
		
		for (int ii=0; ii<list.length; ii++) {
			if(target.equals(list[ii])) return ii;
		}
		
		return -1;
	}
	
	/**	fill()
	 * 
	 * Given a String type instance, the routine returns the string filled
	 * with a given instance to a defined length. E.g. given the String
	 * tofill='ba', given integer length=6, and given String fillinstance 'na',
	 * the function returns 'banana'
	 *  index of first occurance of a given String element in a 
	 * given list or -1, if the element is not included in the list. 
	 *  
	 * @param	tofill			String		Basis to be appended on.
	 *  
	 * @param	length			int			Length of return String instance.
	 * 
	 * @param	fillinstance	String		Instance used to fill the String.
	 */
	private static String fill(String tofill, int length, String fillinstance) {
		if ( tofill == null || tofill.length() == 0 || length < 1) return "";
		if ( fillinstance == null || fillinstance.length() == 0 ) return tofill;
		if ( tofill.length() > length) return tofill.substring(0, length);

		for (int ii=tofill.length(); ii<length;  ii+=fillinstance.length()) {
			tofill += fillinstance;
		}
		
		return tofill.substring(0, length);
	}
	
	/**	findPatterns()
	 * 
	 * Function to find patterns in a given String instance. The character 
	 * categories have to be given as well. Each character
	 * thereby is categorised by symbol (0), number (1), uppercase (2) or
	 * lowercase (4). The password has to be given as well converted into
	 * an array of bytes, with each element corresponding to one character.
	 *  
	 * @param	text		String		Instance to be investigated.
	 *  
	 * @param	cat			int[]		ii-th element describes the category for
	 * 									the ii-th character of the input String text.
	 * 
	 * @param	bytePass	byte[]		ii-th element describes the ii-th character
	 * 									of the input String text converted into bytes.
	 * 
	 * @param	startend	boolean		If flag is true, then the String instance is
	 * 									investigated towards same start and end.
	 * 									E.g. 123password123, ##appple## 
	 * 									(start and is equal)
	 * 
	 */
	private static String[] findPatterns(String text, int[] cat, byte[] bytePass, boolean startend) {
		int size = text.length();
		int ind = 0;
		String patt = "";
		if ( startend ) {
			for (int ii = size/2; ii > 0; ii--){
				if (text.substring(0,ii).equals(text.substring(size-ii))) {
					ind = ii;
					break;
				}
			}
		}
		patt = Integer.toString(ind);
		
		for (int bb = 1; bb <= size/3; bb++){
			int babo[] = new int[bb];
			int boba[] = new int[bb];
			
			String catPatt[] = new String[bb];
			boolean foundPatt[] = new boolean[bb];
			boolean foundequal[] = new boolean[bb];
			for (int ii=bb; ii<size; ii++) {
				ind = ii%bb;
				if ( cat[ii-bb] == cat[ii] ) {
					babo[ind] = (int) bytePass[ii-bb] - bytePass[ii];
					if (babo[ind] == 0) {
						if ( foundPatt[ind] && !foundequal[ind] ) {
							patt += devider+catPatt[ind];
							catPatt[ind] = getStringOf(spaceholder, (ii-bb))+text.charAt(ii-bb);
						}
						if ( !foundPatt[ind] ) {
							catPatt[ind] = getStringOf(spaceholder, (ii-bb))+text.charAt(ii-bb);
						}
						catPatt[ind] += getStringOf(spaceholder, (bb-1))+text.charAt(ii);
						foundPatt[ind] = true;
						foundequal[ind] = true;
						boba[ind] = 0;
						continue;
					}
					
					if( babo[ind] == boba[ind] ) {
						if ( cat[ii] != 0 ) {
							if ( !foundPatt[ind] ) {
								catPatt[ind] = getStringOf(spaceholder, (ii-2*bb))+text.charAt(ii-2*bb)+getStringOf(spaceholder, (bb-1))+text.charAt(ii-bb);
							}
							catPatt[ind] += getStringOf(spaceholder, (bb-1))+text.charAt(ii);
							foundPatt[ind] = true;
							foundequal[ind] = false;
							boba[ind] = babo[ind];
							continue;
						}
					}
					boba[ind] = babo[ind];
				} else { boba[ind] = (bb-1)*26;	}
				
				if ( foundPatt[ind] ) {
					patt += devider+catPatt[ind];
//					System.out.println(patt.replace(spaceholder, "*"));
				}
				foundPatt[ind] = false;
				//System.out.println();
			}
			for (int ii=0; ii<bb; ii++){
				if ( foundPatt[ii] ) {
					patt += devider+catPatt[ii];
//					System.out.println(patt.replace(spaceholder, "*"));
				}
				foundPatt[ii] = false;
			}
		}
		
//		pr_Str(patt.split(devider), "findPatterns");
		return patt.split(devider);
	}
	
	/**	concate()
	 * 
	 * Concates two boolean arrays, or a boolean array with a boolean scalar.
	 * Second instance b will be appended to the first instance a in an array.
	 *  
	 * @param	a		boolean[]	First boolean array.
	 * 
	 * @param	b		boolean[]	Second boolean array (can as well be boolean b).
	 * 
	 */
	private static boolean[] concate(boolean[] a, boolean[] b) {
		if (a == null || a.length == 0) return b;
		if (b == null || b.length == 0) return a;
		
		int p0 = a.length;
		boolean out[] = new boolean[p0+b.length];
		for (int ii=0; ii<p0; ii++) {
			out[ii] = a[ii];
		}
		for (int ii=0; ii<b.length; ii++) {
			out[p0+ii] = b[ii];
		}
		return out;
	}
	
	private static boolean[] concate(boolean[] a, boolean b) {
		if ( a == null || a.length == 0 ) return new boolean[] {b};
		
		int p0 = a.length;
		boolean out[] = new boolean[p0+1];
		for (int ii=0; ii<p0; ii++) {
			out[ii] = a[ii];
		}
		out[p0] = b;
		return out;
	}
	
	/**	concate()
	 * 
	 * Concates two String arrays (or similar). As well allowed is to concate
	 * one String with a String array. Second instance b will be appended to the
	 * first instance a in an array. Optionally, in case of two String arrays only,
	 * a position can be included to insert the second instance b at a certain
	 * position in the array of the first instance a. Further, an optional flag
	 * replace can be used to replace the element at the given position in array
	 * of instance a while inserting instance b.
	 *  
	 * @param	a			String[]	First string array (can as well be String a).
	 * 
	 * @param	b			String[]	Second string array
	 * 									(can as well be String b, but only if String[] a).
	 * @param 	position	int			(Optional) position in the array a to insert array b
	 *									
	 * @param 	replace		boolean		(Optional) If true, replaces element at index position.
	 */
	private static String[] concate(String[] a, String[] b) {
		if ( a == null || a.length == 0 ) return b;
		if ( b == null || b.length == 0 ) return a;
		
		if ( a.length == 1 ) return concate(a[0], b);
		if ( b.length == 1 ) return concate(a, b[0]);
		
		int p0 = a.length;
		String out[] = new String[p0+b.length];
		for (int ii=0; ii<p0; ii++) {
			out[ii] = a[ii];
		}
		for (int ii=0; ii<b.length; ii++) {
			out[p0+ii] = b[ii];
		}
		return out;
	}

	private static String[] concate(String[] a, String b) {
		if ( a == null || a.length == 0 ) return new String[] {b};
		if ( b == null || b.length() == 0 ) return a;
		
		int p0 = a.length;
		String out[] = new String[p0+1];
		for (int ii=0; ii<p0; ii++) {
			out[ii] = a[ii];
		}
		out[p0] = b;
		return out;
	}

	private static String[] concate(String a, String[] b) {
		if ( a == null || a.length() == 0) return b;
		if ( b == null || b.length == 0 ) return new String[] {a};
		
		int p0 = b.length+1;
		String out[] = new String[p0];
		out[0] = a;
		for (int ii=1; ii<p0; ii++) {
			out[ii] = b[ii-1];
		}
		return out;
	}
	
	private static String[] concate(String[] a, String[] b, int position) {
		return concate(a, b, position, false);
	}
	
	private static String[] concate(String[] a, String b, String[] c) {
		if ( a == null || a.length == 0 ) return concate(b,c);
		if ( b == null || b.length() == 0 ) return concate(a,c);
		if ( c == null || c.length == 0 ) return concate(a,b);
		
		int sa = a.length, sc = c.length;		
		String out[] = new String[sa+sc+1];
		for (int ii=0; ii<sa; ii++) {
			out[ii] = a[ii];
		}
		out[sa] = b;
		sa++;
		for (int ii=0; ii<sc; ii++) {
			out[sa+ii] = c[ii];
		}
		return out;
	}
	
	private static String[] concate(String[] a, String[] b, int position, boolean replace) {
		if (a == null || a.length == 0) return b;
		if (b == null || b.length == 0) return a;
		
		int sa = a.length, sb = b.length;
		if (position > a.length) position = a.length;
		
		int size = sa+sb;
		if ( replace ) size--;
		
		String out[] = new String[size];
		for (int ii=0; ii<position; ii++) {
			out[ii] = a[ii];
		}
		for (int ii=0; ii<sb; ii++) {
			out[position+ii] = b[ii];
		}
		if ( replace ) position++;
		for (int ii=position; ii<sa; ii++) {
			out[ii+sb] = a[ii];
		}
		
		return out;
	}
	
	/** getBirthdayPatterns()
	 * 
	 * Returns a String array containing the regular expressions for all possible
	 * number combinations which can be used to describe a Birthday date (or a similar
	 * date of importance in that respect). 
	 * 
	 */
	private static String[] getBirthdayPatterns() {
		String bPYear[] = {"19[3-9]d", "20[0-2]d"};
		String bPdaymonth3d[] = {"[0-2]dd", "30d", "31[13578]", "d1[0-2]"};
		String bPdaymonth4d[] = {"[0-2]d0[1-9]", "[0-2]d1[0-2]", "300[1-9]", "310[13578]", "301[0-2]", "311[0-2]",
				"0[1-9][0-2]d", "1[0-2][0-2]d", "0[1-9]30", "0[13578]31", "1[0-2]30", "1[0-2]31"};
		
		String[] patString = combineStringArrays(bPdaymonth4d, bPYear, true);
		patString = concate(patString, combineStringArrays(bPdaymonth3d, bPYear, true));
		patString = concate(patString, combineStringArrays(bPdaymonth4d, new String[] {"dd"}, false));
		patString = concate(patString, combineStringArrays(bPdaymonth3d, new String[] {"dd"}, false));
		patString = concate(patString, bPdaymonth4d);
		patString = concate(patString, bPYear);
		
		return patString;
	}
	
	/**	reducePasswordbyFilePatterns()
	 * 
	 * Reduces an original String instance password by excluding the given Patterns
	 * criticals. Each String element of the String array criticals will be looked
	 * for in password and excluded for the output of the routine. After excluding
	 * a substring of password, the String instance password will be split at
	 * the position of occurrence leading to two different String instances. Iterating
	 * all elements of criticals results in a String array containing all fragments of
	 * the original String instance password which do not include any of the Strings in
	 * criticals. This resulting String array will be the output of this function.
	 * E.g. Input : password = 'BAappleNAorangeNA', criticals = ['apple', 'orange']
	 *  	Output: ['BA','NA','NA']
	 *  
	 * @param	password	String		String to be split.
	 * 
	 * @param	criticals	String[]	Array of Strings to be used as splitter for
	 * 									the String instance password.
	 * 
	 */
	private static String[] reducePasswordbyFilePatterns(String password, String[] criticals) {
		if ( criticals == null || criticals.length == 0 ) return new String[] {password};

		Arrays.sort(criticals, new java.util.Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		    	if (s1 == null && s2 == null) return 0;
		    	if (s1 == null) return s2.length();
		    	if (s2 == null) return -s1.length();
		    	
		        return s2.length() - s1.length();
		    }
		});
		//Arrays.sort(criticals, Comparator.comparingInt(String::length));

		int critSize = criticals.length;
		int smallesSize = max(1,criticals[critSize-1].length());
		
		String grab = password.toLowerCase();
		int ind = 0;
		boolean flag = true;
		String splitPassword[] = {};
		while(flag) {
			int p0 = grab.length();
			for (int ii=0; ii<critSize; ii++) {
				if ( criticals[ii] == null || criticals[ii].length() == 0 ) break;
				if ( !grab.contains(criticals[ii]) ) continue;
				
				flag = false;
				if ( grab.indexOf(criticals[ii]) <= (p0-criticals[ii].length()) ) {
					p0 = grab.indexOf(criticals[ii]);
					ind = ii;
				}
				if (p0 == 0) break;
			}
			if (flag) break;

			int matchSize = criticals[ind].length();				
			if (p0 == 0) {
				splitPassword = concate(splitPassword, criticals[ind]);
			} else {
				splitPassword = concate(splitPassword, new String[] {grab.substring(0, p0), criticals[ind]});
			}
			grab = grab.substring(p0+matchSize);
			
			if ( grab.length() < smallesSize ) flag = false;
			flag = true;
		}
		
		if ( grab != null && grab.length() > 0 ) splitPassword = concate(splitPassword, grab);
		return splitPassword;
	}
	
	/**	contains()
	 * 
	 * Checks whether given input String array contains a given String instance
	 * target. Returns boolean true if target is contained in input list. Else 
	 * returns boolean false. 
	 *  
	 * @param	list		String[]	String array to be check for occurance of 
	 * 									String instance target.
	 * 
	 * @param	target		String		String instance targeted in list.
	 * 
	 */
	private static boolean contains(String[] list, String target) {
		if ( list == null || list.length == 0) return false;
		if ( target == null || target.length() == 0) return false;
		
		for (int ii=0; ii<list.length; ii++) {
			if(target.equals(list[ii])) return true;
		}
		
		return false;
	}

	/**	pr_bytes()
	 * 
	 * Prints a byte array to screen with given stamp
	 * 
     * @param	arg		byte[]	Hash to be printed.
     * 
     * @param	code 	String	Stamp for terminal output.
     */
    public static void pr_Str(String[] arg, String code){
 //   	System.out.println("*** [Printing Strings] "+code+" ***");
    	if ( arg == null || arg.length == 0 ) {
 //   		System.out.println("*");
 //       	System.out.println("*** [End Printing] "+code+" ***");
 //       	System.out.println();
    		return;
    	}
 //   	System.out.print("* | ");
    	for (int ii=0; ii<arg.length; ii++){
 //   		System.out.print(arg[ii].replace(spaceholder,"*")+" | ");
    	}
 //   	System.out.println();
 //   	System.out.println("*** [End Printing] "+code+" ***");
 //   	System.out.println();
    }
	
	/**	getCriticalPatternsbyFile()
	 * 
	 * Checks given input String password whether it contains one of the String
	 * instances listed in an external file with the name filename. Each line of
	 * the file filename is taken as a String of which its occurance has to be
	 * checked in the String instance password. In practice the external file
	 * can contain frequently used passwords (e.g. 'banana' or 'password;) or
	 * keyboard patterns (e.g. '1q2w3e' or 'asdf') which would reduce the security of
	 * a password.
	 * The output will be an array of Strings, each String corresponding to a
	 * matching String instance of the external file.
	 *  
	 * @param	filename	String		Filename of the external file to be streamed.
	 * 
	 * @param	password	String		Instance to be checked for containing words from
	 * 									the external file filename.
	 * 
	 */
	private static String[] getCriticalPatternsbyFile(String filename, String password){
		BufferedReader br = null;
		FileReader fr = null;
		
		boolean raiseFlag = true;
		String overlap = "";
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String deathWord;
			while ((deathWord = br.readLine()) != null) {
				if (deathWord == null || deathWord.isEmpty()) continue;
				if (password.toLowerCase().contains(deathWord)){
					if (raiseFlag) {
						overlap = deathWord;
						raiseFlag = false;
						continue;
					}
					overlap += devider+deathWord;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
//		if (!raiseFlag) System.out.println();
		
		return overlap.split(devider);
	}
	
	/**	randomGrab()
	 * 
	 * Returns a random String containing randomly grabbed characters of given
	 * input String instance grabString.
	 *  
	 * @param	grabString		String		Source/seed for the random String returned.
	 * 
	 * @param	size			int			Length of the random String to be returned.
	 * 
	 */
	private static String randomGrab(String grabString, int size){
		int grabSize = grabString.length();
		String randString = "";
		
		SecureRandom rand = new SecureRandom();
		
		for (int ii=0; ii<size; ii++) {
			randString += grabString.charAt(rand.nextInt(grabSize));
		}

		return randString;
	}
}
