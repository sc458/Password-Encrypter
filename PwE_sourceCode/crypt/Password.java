package crypt;

import static java.lang.Math.*;
import java.lang.management.ManagementFactory;
// For java 9
//import java.lang.ProcessHandle*;

/**	class Password
 * 
 * This class is the main class for dealing with en- and decryption
 * of passwords. Each password type Password() contains among others
 * the following attributes
 * 
 * @param	associate		String		Key as address for the encrypted password.
 * 
 * @param	username		String		Username belonging to the account at the associate.
 *      
 * @param	encryptUsername	boolean		Flag for username encryption. If true username 
 * 										is encrypted, else it will be stored directly.
 * 
 * @param	password		String		Encrypted Password.
 *     
 * @param	description		String		Arbitrary text describing the associate. Will be
 * 										stored directly without encryption.
 * 
 */
public class Password {
	public static boolean suppress_output = true;
	private static final String branding_salt = "K-Cl";
	public static final int INVALID_PASSWORD_TIME_DELAY = 500; // in ms
	
	private static String unique;
	private static boolean unique_set = false;
	
	private String associate;
	private String username;
	private String password;
	private String description;
	private boolean encryptUsername;
	private String masterSalt;
	private boolean masterSaltExists = false;

	private static String masterPwd;
	private static String hashedMaster;
	
//	private static ArrayList<String> prepedHashes;

    private static final int MAX_PERM_HASH_TRY = 100;
//    private static final int NUMBER_PREPARED_HASHES = 20;
    
//    private static final int master_penetration_limit = 10;
   
    
    
	// Sorce code from: https://stackoverflow.com/questions/35842/how-can-a-java-program-get-its-own-process-id
	private static String getPID() {
		if ( unique_set ) return unique;
	    // Note: may fail in some JVM implementations
	    // therefore fallback has to be provided

	    // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
	    final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
	    final int index = jvmName.indexOf('@');
	    
	    // part before '@' empty (index = 0) / '@' not found (index = -1)
	    if (index > 0) {
		    try {
		    	return Long.toString(Long.parseLong(jvmName.substring(0, index)));
		    } catch (NumberFormatException e) {
		    	// ignore
		    }
	    }
	    
	    toScreen("<> Run 1 failed <>");
	    
	    // Trying workaround
	    try {

		    java.lang.management.RuntimeMXBean runtime = java.lang.management.ManagementFactory.getRuntimeMXBean();
		    java.lang.reflect.Field jvm = runtime.getClass().getDeclaredField("jvm");
			jvm.setAccessible(true);
			sun.management.VMManagement mgmt = (sun.management.VMManagement) jvm.get(runtime);
			java.lang.reflect.Method pid_method = mgmt.getClass().getDeclaredMethod("getProcessId");
			pid_method.setAccessible(true);

			int pid = (Integer) pid_method.invoke(mgmt);
			
			return Integer.toString(pid);
	    } catch ( Exception e ) {
	    	// ignore
	    }
	    
	    toScreen("<> Run 2 failed <>");
	    
	    if ( !unique_set ) {
	    	unique = Long.toString( System.currentTimeMillis() );
	    	int ind = Math.min(0, unique.length()-6);
	    	
	    	unique = unique.substring(ind);
	    	unique_set = true;
	    }
	    return unique;
	}

    public static void initialiseMaster(String master_pwd) throws RunOutOfHashException {
    	toScreen("> Input: "+master_pwd);
    	hashedMaster =  Hashing.hash_password(master_pwd);
    	toScreen("> Hashed: "+hashedMaster);
    	toScreen("> Process ID: "+getPID());
    	toScreen("> Encrypt seed:"+getPID()+branding_salt);
    	masterPwd = encryptPassword(getPID()+branding_salt, master_pwd);
    	toScreen("> masterPWD: "+masterPwd);
    	master_pwd = null;
    }
    
    public static boolean initialiseMaster(String user_password, String hashed_master) throws RunOutOfHashException {
    	if ( check_password(user_password, hashed_master) ) {
    		// TODO, p2: encrypt masterPWD with PID and random seed
    		hashedMaster = hashed_master;
    		masterPwd = encryptPassword(getPID()+branding_salt, user_password);
    		user_password = null;
    		return true;
    	}
    	
    	return false;
    }
    
    private static void delay() {
    	try {
			Thread.sleep(INVALID_PASSWORD_TIME_DELAY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private static String getdecryptedMaster( ) throws RunOutOfHashException {    	
    	String args[] = masterPwd.split(":");
    	toScreen("> Process ID: "+ getPID());
    	String strg = decrypt(getPID()+branding_salt, args[0], args[1], args[2]);
    	toScreen("> Decrypted Master: "+strg);
    	
    	return strg;
    }
    
/*    private static void setPreparedHashes(String source){
    	prepedHashes = new ArrayList<String>(NUMBER_PREPARED_HASHES);
    	for ( int ii = 1; ii < NUMBER_PREPARED_HASHES; ii++) {
    		prepedHashes.set( ii, Hashing.hash_password(source) );
    	}
    }
    
    private static String popPreparedHash() throws EmptyPrepedHashesException {
    	int last = prepedHashes.size();
    	String hash = prepedHashes.get( last );
    	prepedHashes.remove( last );
    	return hash;
    }
*/
 
	/**	getMasterHash()
	 * 
	 * Checks deposited password to be the master password and returns a hash of the password if input matches.
	 * 
     * @param	salt	Hex-String	(Optional) If present salt will be used for returned hash of 
     * 								master password, else random salt is used.
     * 
     * @return			Hex-String	Hashed master password in Hex-format. If salt present the output will be:
     * 									HASHED_MASTER+":"+CREATED_SALT
     */
    public static String getMasterHash() {
    	//if ( !check_password(masterPwd, hashedMaster) ) {
    	//	throw new InvalidMasterPasswordException("Master password check failed.");
    	//}
    	return hashedMaster;
    	//return Hashing.hash_password(masterPwd);
    }
    private static String getMasterHash(String salt) throws RunOutOfHashException {
    	//if ( !check_password(masterPwd, hashedMaster) ) {
    	//	throw new InvalidMasterPasswordException("Master password check failed.");
    	//}
    	return Hashing.hash_password(getdecryptedMaster(), salt);
    }
    
	/**	toScreen()
	 * 
	 * Prints given String in terminal as long as output is not suppressed globally
     */
    private static void toScreen(String toPrint){
    	if (!suppress_output){
//    		System.out.println(toPrint);
    	}
    }
    
	/**	pr_hash()
	 * 
	 * Prints a hash to screen with given stamp
	 * 
     * @param	hash	Hex-String	Hash to be printed.
     * 
     * @param	code 	String		Stamp for terminal output.
     */
    private static void pr_hash(String hash, String code){
    	if (!suppress_output){
    	System.out.println("*** [Printing Hash] "+code+" ***");
    	System.out.println(hash);
    	System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    	}
    }
    
	/**	print()
	 * 
	 * Prints encrypted password as it would be stored:
	 * 		Incl.: Associate, all salts(for master, one-time pad and permutation) and encrypted password
     */
    public void print(){
    	if (!suppress_output){
    	System.out.println("Your password is stored securely as: ");
    	System.out.println("	Associate:		"+this.associate);
    	System.out.println("	Username:	 	"+this.username);
    	System.out.println("	Password:		"+this.password);
    	System.out.println("	Description:		"+this.description);
    	System.out.println("	Used MasterSalt:	"+this.masterSalt);
    	System.out.println();
    	}
    }

	/**	Password()
	 * 
	 * Constructor for new password. Taking associate, the username, the pure password
	 * and a description and encrypts the password and optionally the username for
	 * storing the Password instance incl. all relevant salts.
	 * 
     * @param	associate		String		Key as address for the encrypted password.
     * 
     * @param	username		String		Username belonging to the account at the associate.
     *      
     * @param	encryptUsername	boolean		Flag for username encryption. If true username 
     * 										will be encrypted, else it will be stored directly.
     * 
     * @param	password		String		Password to be encrypted and stored.
     *     
     * @param	description		String		Arbitrary text describing the associate. Will be
     * 										stored directly without encryption.
     * 
     * 
     * @exception 	RunOutOfHashException 			Forwarded from encodePassword().
     */
    public Password(String associate, String username, boolean encryptUsername, 
    		String password, String description) throws RunOutOfHashException {
    	this.associate = associate;
		this.description = description;
		this.encryptUsername = encryptUsername;
		
		// Early version: realtime input of Pwd:
    	// String hash_args[] = check_password().split(":");
		
		// Now: Using prepared Hashes:
		String hash_args[] = getMasterHash().split(":");
    	this.masterSalt = hash_args[1];
    	 
    	this.password = encryptPassword(hash_args[0], password);
		password = null;
		
		if (encryptUsername){
	    	this.username = encryptPassword(hash_args[0], username);
		} else {
			this.username = username;
		}
    	hash_args = null;
		username = null;
		
    	this.masterSaltExists = true;
    	
    	pr_hash(this.password, "Encrypted PWD-Hash");
    	pr_hash(this.username, "Stored Username");
    	this.print();
    }	
    
    public Password(String associate, String username, boolean encryptUsername, 
    		String password, String description, String masterSalt) {
    	this.associate = associate;
    	this.description = description;
    	this.masterSaltExists = true;
    	this.masterSalt = masterSalt;
    	this.encryptUsername = encryptUsername;
    	this.username = username;
    	this.password = password;
    }
    
    public void assign(Password that) {
    	this.associate = that.associate;
    	this.description = that.description;
    	this.masterSaltExists = that.masterSaltExists;
    	this.masterSalt = that.masterSalt;
    	this.encryptUsername = that.encryptUsername;
    	this.username = that.username;
    	this.password = that.password;
    }
	
	public void setAssociate(String associate) {
		this.associate = associate;
	}	
	public void setUsername(String username, boolean encrypt) throws RunOutOfHashException {
		this.encryptUsername = encrypt;
		if (encrypt){
			String hash_args[];
			if (this.masterSaltExists) {
				// Early version: realtime input of Pwd:
		    	// hash_args = check_password(this.masterSalt).split(":");
				
				// Now: Using prepared Hashes:
				hash_args = getMasterHash(this.masterSalt).split(":");
			} else {
				// Early version: realtime input of Pwd:
		    	// hash_args = check_password().split(":");
				
				// Now: Using prepared Hashes:
				hash_args = getMasterHash().split(":");
		    	this.masterSalt = hash_args[1];
			}
			
	    	this.username = encryptPassword(hash_args[0], username);
	    	this.masterSaltExists = true;
		} else {
			this.username = username;
		}
	}
	public void setPassword(String password) throws RunOutOfHashException {
		String hash_args[];
		if (this.masterSaltExists) {
			// Early version: realtime input of Pwd:
	    	// hash_args = check_password(this.masterSalt).split(":");
			
			// Now: Using prepared Hashes:
			hash_args = getMasterHash(this.masterSalt).split(":");
		} else {
			// Early version: realtime input of Pwd:
	    	// hash_args = check_password().split(":");
			
			// Now: Using prepared Hashes:
			hash_args = getMasterHash().split(":");
	    	this.masterSalt = hash_args[1];
		}
		
    	this.password = encryptPassword(hash_args[0], username);
    	this.masterSaltExists = true;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    
	public String getAssociate() {
		return this.associate;
	}
	public String getUsername() {
		return this.username;
	}	
	public String getPassword() {
		return this.password;
	}	
	public String getDescription() {
		return this.description;
	}
	public boolean getEncryptUsername() {
		return this.encryptUsername;
	}
	
	public String getMasterSalt() {
		return this.masterSalt;
	}
	
	public String[] getAllDecrypted() throws RunOutOfHashException {
		String[] hereugo = new String[4];
		hereugo[0] = this.associate;
		hereugo[3] = this.description;
		String temp[] = this.decrypt();
		hereugo[1] = temp[0];
		hereugo[2] = temp[1];
		temp = null;
		
		return hereugo;
	}

	/**	decrypt()
	 * 
	 * Decrypting all encrypted arguments of a Password instance. This includes the password and if encrypted,
	 * the username as well. Returns both decrypted password and username in any case.
     * 
     * @return			String		Decrypted username and password in following String-format:
     * 										DECRYPTED_USERNAME+":"+DECRYPTED_PASSWORD
     * 
     * 
     * @exception 	RunOutOfHashException 			Forwarded from get_perm_from_hash().
     * 
     * @exception 	InvalidMasterPasswordException	Forwarded from getMasterHash().
     */
    public String[] decrypt() throws RunOutOfHashException {
    	String decrypt[] = new String[2];
    	if(this.encryptUsername){
        	String username_args[] = this.username.split(":");
        	String hash_args[] = getMasterHash(this.masterSalt).split(":");
        	
        	decrypt[0] = decrypt(hash_args[0], username_args[0], username_args[1], username_args[2]);
    	} else {
    		decrypt[0] = this.username;
    	}

//    	System.out.println("APPLE");
//    	System.out.println(this.masterSalt);
    	String hash_args[] = getMasterHash(this.masterSalt).split(":");
    	
    	
    	String password_args[] = this.password.split(":");
    	decrypt[1] = decrypt(hash_args[0], password_args[0], password_args[1], password_args[2]);
    	return decrypt;
    }
    
    public void changeMaster(String newPwd) throws RunOutOfHashException {
    	String decrypted[] = this.decrypt();
		
		String hash_args[] = Hashing.hash_password(newPwd).split(":");
		newPwd = null;
		this.masterSalt = hash_args[1];
    	
    	this.password = encryptPassword(hash_args[0], decrypted[1]);
    	decrypted[1] = null;
		
		if (this.encryptUsername){
	    	this.username = encryptPassword(hash_args[0], decrypted[0]);
		} else {
			this.username = decrypted[0];
		}
    	hash_args = null;
    	decrypted[0] = null;
		
    	this.masterSaltExists = true;    	
    }

    
	/**	getPassword()
	 * 
	 * Decrypting the encrypted password of a Password instance.
     * 
     * @return			String		Decrypted password.
     * 
     * 
     * @exception 	RunOutOfHashException 			Forwarded from get_perm_from_hash().
     * 
     * @exception 	MasterPenetrationLimitException	Forwarded from getMasterHash().
     */
    public String decryptPassword() throws RunOutOfHashException {
    	String password_args[] = this.password.split(":");
    	String hash_args[] = getMasterHash(this.masterSalt).split(":");
    	
    	return decrypt(hash_args[0], password_args[0], password_args[1], password_args[2]);
    }
    
	/**	getUsername()
	 * 
	 * Returning username argument of a Password instance. Decrypting the username if necessary.
     * 
     * @return			String		Username.
     * 
     * 
     * @exception 	RunOutOfHashException 			Forwarded from get_perm_from_hash().
     * 
     * @exception 	MasterPenetrationLimitException	Forwarded from getMasterHash().
     */
    public String decryptUsername() throws RunOutOfHashException {
    	if(this.encryptUsername){
        	String username_args[] = this.username.split(":");
        	String hash_args[] = getMasterHash(this.masterSalt).split(":");
        	
        	return decrypt(hash_args[0], username_args[0], username_args[1], username_args[2]);
    	} else {
    		return this.username;
    	}
    }
    
	/**	encryptPassword()
	 * 
	 * Encrypting a password with given encryption source. Source becomes hashed twice with random
	 * salts for the permutation and the one-time-pad respectively. Encoding the password into the
	 * permutation-hash and encrypting it with the one-time-pad.
	 * 
     * @param	source		Hex-String	Source for the password encryption. Usually this is given by 
     * 									a hashed master password.
     * 
     * @param	password	String		Password to be encrypted.
     * 
     * @return				Hex-String	Hashed Password including the used salts in following hex-format:
     * 										ENCRYPTED_PASSWORD+":"+PAD_SALT+":"+PERM_SALT
     * 
     * 
     * 
     * @exception 	RunOutOfHashException 	Forwarded from encodePassword().
     */

    private static String encryptPassword(String source, String password) throws RunOutOfHashException {
    	String hashed_perm = encodePassword(source, password);
		password = null;
		
    	String hashed_pad = Hashing.hash_password(source);
    	source = null;
    	
    	String hash_args[] = hashed_perm.split(":");
    	hashed_perm = hash_args[0];
    	String permSalt = hash_args[1];
    	
    	hash_args = hashed_pad.split(":");
    	hashed_pad = hash_args[0];
    	String padSalt = hash_args[1];
    	hash_args = null;
    	
    	hashed_pad = DataTypes.hex_add_bin(hashed_pad, hashed_perm);

    	hashed_perm = null;
    	return hashed_pad+":"+padSalt+":"+permSalt;
    }
    
	/**	encodePassword()
	 * 
	 * Encoding a password into a given source for permutation-hash. First Hashing source with random salt
	 * obtaining the permutation-hash and secondly encoding the password into the hash.
	 * 
     * @param	source		Hex-String	Source for the password encryption. Used to get the permutation-hash.
     * 
     * @param	password	String		Password to be encoded.
     * 
     * @return				Hex-String	Hashed Password including the used salts in following hex-format:
     * 										ENCODED_PASSWORD+":"+USED_PERM_SALT
     * 
     * 
     * @exception 	RunOutOfHashException 	Caught from get_perm_from_hash() and retried with new random salt.
     * 										Maximum number of retry is set globally by MAX_PERM_HASH_TRY.
     * 										If maximum retry is reached exception is thrown again.
     */
    private static String encodePassword(String source, String password) throws RunOutOfHashException {
    	password = DataTypes.StringtoHex(password);
    	int pwd_len = password.length();
    	
    	// @LIMITATION: hex-encoded password restricted to 255 digits 
    	int perm[] = new int[pwd_len+2];
    	String hash_args[] = Hashing.hash_password(source).split(":");
    	
    	for ( int ii=1; ii <= max(1, MAX_PERM_HASH_TRY); ii++ ) {
    		toScreen("Try # "+ii);
    		try {
    			perm = get_perm_from_hash(hash_args[0], pwd_len);
    		} catch ( RunOutOfHashException e ) {
    			toScreen("...failed");
    			if ( ii == max(1, MAX_PERM_HASH_TRY) ) {
    				throw new RunOutOfHashException("Maximum Permutation Try reached");
    			}
        		hash_args = Hashing.hash_password(source).split(":");
        		continue;
    		}
    		break;
    	}
    	source = null;
    	
    	//toScreen(target);
    	//toScreen(hash_args[0]);
    	String saltPerm = hash_args[1];
    	
    	char[] hex_target_len = String.format("%2x", pwd_len).replace(" ", "0").toCharArray();
    	//String hashed_perm = hash_args[0].substring(0, perm[0])
    	//		+ String.format("%2x", pwd_len).replace(" ", "0")
    	//		+ hash_args[0].substring(perm[0]+2);
    	String hashed_perm = hash_args[0].substring(0, perm[0])
    			+ hex_target_len[0]
    			+ hash_args[0].substring(perm[0]+1);
    	hashed_perm = hashed_perm.substring(0, perm[1])
    			+ hex_target_len[1]
    			+ hashed_perm.substring(perm[1]+1);
    	
    	hash_args = null;
    	hex_target_len = null;
    	//toScreen(hashed_perm);   	
    	for (int ii=2; ii < pwd_len+2; ii++){	
    		hashed_perm = hashed_perm.substring(0, perm[ii])
    				+ password.charAt(ii-2)
    				+ hashed_perm.substring(perm[ii]+1);
    	} 
		//toScreen(hashed_perm);
    	return hashed_perm+":"+saltPerm;
    }
    
	/**	decrypt()
	 * 
	 * Decrypting a encrypted Hex-String with given source and salts. Source becomes hashed twice with given
	 * permutation and one-time-pad salts. Decrypting the encrypted Hex-String with obtained one-time-pad and
	 * decoding the password.
	 * 
     * @param	source		Hex-String	Used source for the decryption.
     * 
     * @param	encrypted	Hex-String	Argument to be decrypted.
     *      
     * @param	padSalt		Hex-String	Salt used for the one-time-pad-hash of the source.
     * 
     * @return				String		Decrypted argument.
     * 
     * 
     * @exception 	RunOutOfHashException 			Forwarded from get_perm_from_hash().
     */
    private static String decrypt(String source, String encrypted, String padSalt, String permSalt) throws RunOutOfHashException { 	
    	String hashed_pad = Hashing.hash_password(source, padSalt);
    	//pr_hash(hashed_pad+":"+this.salt_pad, "DE - One time Pad");
    	String hashed_perm = Hashing.hash_password(source, permSalt);
    	
    	//pr_hash(hashed_perm+":"+this.salt_perm, "DE - Permutation");
    	
    	int[] perm = get_perm_from_hash(hashed_perm, 0);
    	
    	String crypt_pwd = DataTypes.hex_add_bin(encrypted, hashed_pad);
    	//pr_hash(hashed_pad, "DE - Crypt");
    	//toScreen(crypt_pwd);
    	
    	int pwd_len = Integer.parseInt(crypt_pwd.substring(perm[0],perm[0]+1)+
    			crypt_pwd.substring(perm[1],perm[1]+1), 16);

    	perm = get_perm_from_hash(hashed_perm, pwd_len);
    	String password = "";
    	for(int ii=2; ii < pwd_len+2; ii++){
    		password = password + crypt_pwd.charAt(perm[ii]);
    	}
    	
    	return DataTypes.HextoString(password);
    }

	/**	get_perm_from_hash()
	 * 
	 * Extracts permutation out of a given Hex-String.
	 * 
     * @param	hash	Hex-String	Argument for extracting permutation.
     * 
     * @param	size	int			Size of permutation array to return.
     *      
     * @param	range	int[2]		(Optional) Range for the output array. If not present range=[0,hash.length].
     * 
     * @return			int[size]	Permutation array.
     * 
     * 
     * @exception 	RunOutOfHashException 	Throwing if end of given hash is reached, while permutation array is
     * 										not completely filled yet up to requested size.
     */
    private static int[] get_perm_from_hash(String hash, int size) throws RunOutOfHashException {
    	// @LIMITATION: hex-encoded Password length maximal 255;
    	int range[] = {0,hash.length()};
    	
    	// TODO: Adapt "+2" to "+ll", with ll chosen with respect to range!!
    	//			OR 
    	//		Complain, if range spans more than 255.
    	return get_perm_from_hash(hash, size+2, range);
    }
    
    private static int[] get_perm_from_hash(String hash, int size, int range[]) throws RunOutOfHashException {
    	// @LIMITATION: hex-encoded Password length maximal 255;
    	int kk = 0;
    	int perm[] = new int[size];
    	int temp;
    	int hash_len = hash.length();
    	range[1] = range[1]-range[0];
    	
    	// TODO: Adapt "ii-2" to "ii-ll", with ll chosen with respect to range!!
    	//			OR 
    	//		Complain, if range spans more than 255.
    	
    	// @LIMITATION: hex-encoded password restricted to length of hash 
    	for (int ii=2; ii < hash_len; ii++){
    		// @LIMITATION: Hash-length maximal 255;
    		// Here length of hash is limited to two hex-digets
    		temp = (Integer.parseInt(hash.substring(ii-2, ii), 16) % range[1]) +range[0];
    		if (!(isin(temp, perm))){
    			perm[kk] = temp;
    			kk++;
    			if(kk >= size){
    				return perm;
    			}
    		}
    	}
    	throw new RunOutOfHashException("Permutation could not be created from hash.");
    }
    
	/**	isin()
	 * 
	 * Checking if target is in list. Helper routine for get_perm_from_hash.
	 * 
     * @param	target	int			Argument to look for in list.
     * 
     * @param	list	int[]		Array to search in.
     *      
     * @return			boolean		True  -> target is in list
     */
    public static boolean isin(int target, int[] list){
    	for (int ii=0; ii < list.length; ii++){
    		if (list[ii] == target){
    			return true;
    		}
    	}
    	return false;
    }
    
	/**	check_password()
	 * 
	 * Checks user input to match the master password.
	 * 
     * @param	user_password	String		Inserted Password to be validated.
     * 
     * @param	hashed_master	Hex-String	Hashed master password.
     * 
     * @param	used_salt		Hex-String	Used salt for master password.
     * 
     * @return					boolean		True  -> passwords match.
     */
    public static boolean validate(String user_password) {
    	return check_password(user_password, hashedMaster);
    }
    
    private static boolean check_password(String user_password, String hashed_master){
    	String[] master_args = hashed_master.split(":");
    	boolean check = check_password(user_password, master_args[0], master_args[1]);
    	user_password = null;
    	return check;
    }
    private static boolean check_password(String user_password, String hashed_master, String used_salt){
    	boolean check = hashed_master.equals(Hashing.hash_password(user_password, used_salt));
    	user_password = null;
    	if ( !check ) {
    		delay();
    	}
    	return check;
    }
}