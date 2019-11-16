package crypt;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**	class Hashing
 * 
 * This class contains all operations needed to create hash for a given data.
 * The public function hash_password() is called with different input variables
 * in order to create a hash in form of a hex-String.
 * 
 */
public class Hashing {
	private static final String branding_salt = "NaHCO_3";
	
    private static final int DEFAULT_SALT_BYTE_SIZE = 28; // Size(Hex-Salt) = Byte_size * 2
    private static final int DEFAULT_HASH_BIT_SIZE = 28 * 8; // Hexadecimal-Hash = Bit_size / 4
    // 24 -> 14
    private static final int DEFAULT_HASH_ITERATIONS = 1000;
    private static final String DEFAULT_HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    
    private static final String PRNG_ALGORITHM = "NativePRNG"; // Alternatively "SHA1PRNG";
    
	/**	get_rand_salt()
	 * 
	 * CSPRNG (cryptographically secure pseudo-random number generator):
	 * Creates a random byte array of size salt_byte_size.
     * 
     * @param	salt_byte_size		int			Length of byte array to return
     * 
     * @return						byte[]		CSPRN of length salt_byte_size
     */
    private static byte[] get_rand_salt(int salt_byte_size){    
	    byte[] salt = new byte[salt_byte_size];
	    
	    SecureRandom rand;
		try {
			rand = SecureRandom.getInstance(PRNG_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
	    	rand = new SecureRandom();
		}
	    rand.nextBytes(salt);
    	
    	return salt;
    }
    
    
	/**	hash_password()
	 * 
	 * Interface for hashing a String instance.
     * 
     * @param	password	String		Password to hash.
     * 
     * @param	salt 		Hex-String	(optional) Salt used for hashing.
     * 									If not present, random salt is used.
     * 
     * @param	HOpt 		HashSettings(optional) Settings for hashing algorithm.
     * 									Incl. Hash Algorithm, Hash size, Salt size and Iterations.
     * 									If not present, default settings are used.
     * 
     * @return				Hex-String	Hashed Password in hex-format.
     * 									If salt not present, used salt will be attached "HASH"+":"+"SALT"
     */
    public static String hash_password(String password) {
    	byte[] salt = get_rand_salt(DEFAULT_SALT_BYTE_SIZE);
    	
    	//DataTypes.pr_bytes(salt, "Random salt");
    	//String test = DataTypes.BytetoHex(salt);
    	//System.out.println("In Hex:");
    	//System.out.println(test);
    	//System.out.println("Back to Byte:");
    	//DataTypes.pr_bytes(DataTypes.HextoByte(test), "Rand");
    	return pbkdf2_hash((branding_salt+password).toCharArray(),
    				salt,
    				DEFAULT_HASH_BIT_SIZE,
    				DEFAULT_HASH_ITERATIONS
    			)  
    			+ ":" + DataTypes.BytetoHex(salt);
    }
    
    public static String hash_password(String password, String salt) {
    	byte[] b_salt = DataTypes.HextoByte(salt);
    			
    	return pbkdf2_hash((branding_salt+password).toCharArray(), b_salt,
    			DEFAULT_HASH_BIT_SIZE, DEFAULT_HASH_ITERATIONS);
    }
    
    public static String hash_password(String password, HashSetting HOpt) {
    	byte[] salt = get_rand_salt(HOpt.SALT_BYTE_SIZE);
    	
    	return pbkdf2_hash((branding_salt+password).toCharArray(),
    				salt,
    				HOpt.HASH_BIT_SIZE,
    				HOpt.HASH_ITERATIONS
    			)  
    			+ ":" + DataTypes.BytetoHex(salt);
    }
    
    public static String hash_password(String password, String salt, HashSetting HOpt) {
    	byte[] b_salt = DataTypes.HextoByte(salt);
    			
    	return pbkdf2_hash((branding_salt+password).toCharArray(), b_salt,
    			HOpt.HASH_BIT_SIZE, HOpt.HASH_ITERATIONS);
    }
    
    
	/**	pbkdf2_hash()
	 * 
	 * Hashes a password with PBEKDF2-Hash algorithm.
     * 
     * @param	password	char[]		Password to hash divided in characters.
     * 
     * @param	salt 		byte[]		Salt to use for hashing.
     * 
     * @param	hashSize	int 		Size of hashed password output Hex-String.
     * 
     * @param	iterations	int			Number of iterations for hashing algorithm.
     * 
     * @return				Hex-String	Hashed Password in hex-format.
     */
    private static String pbkdf2_hash(char[] password, byte[] salt, int hashSize, int iterations){
		PBEKeySpec key_spec = new PBEKeySpec(
				password, 
				salt, 
				iterations, 
				hashSize
		);
		password = null;
    	try {
    		SecretKeyFactory factory = SecretKeyFactory.getInstance(DEFAULT_HASH_ALGORITHM);

    		return DataTypes.BytetoHex(factory.generateSecret(key_spec).getEncoded());
    	} catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
    		throw new RuntimeException ( e );
    	}
    }
    
	/**	SHA_hash()
	 * 
	 * Hashes a password with pure SHA or MD5 hash algorithm.
	 * NOTE:	Not yet tested nor respected in the program.
	 * 			Idea for low security or for PERM/Pad hash
     * 
     * @param	password	char[]		Password to hash divided in characters.
     * 
     * @param	salt 		byte[]		Salt to use for hashing.
     * 
     * @return				Hex-String	Hashed Password in hex-format.
     */
    private static String SHA_hash(byte[] password, byte[] salt){
    	MessageDigest md;
		try {
			md = MessageDigest.getInstance(DEFAULT_HASH_ALGORITHM);
	    	md.reset();
	    	// (password+salt).getBytes("UTF-8");
	    	// Back with String reverse = new String(HereByteArray, "UTF-8");
	    	md.update(DataTypes.concate_byte_arrays(password, salt));

	    	return DataTypes.BytetoHex(md.digest())+":"
	    			+DataTypes.BytetoHex(salt);
	    	
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
    }
}
