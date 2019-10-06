package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.FC;

/** @author tmf
 *  
 *  @date 18-02-28 import of crypt.
 * 
 */
import crypt.Password;
import crypt.PasswordSecurity;
import crypt.RunOutOfHashException;

/**
 * Structure of Json-File
 * 
 * JsonObject File
 * 		JsonObject 'head'
 * 			  Attribute 'created'
 * 			  Attribute 'version'
 * 		JsonArray 'user'
 * 			  JsonObject
 * 			  		 Attribute 'name'
 * 					 Attribute 'numberOfPwds'
 *  				 Attribute 'masterPwd'	
 *  			     JsonArray 'passwords'
 *  						 Attribute 'associate'
 *  						 Attribute 'username'
 *  						 Attribute 'encryptUsername'
 *  						 Attribute 'masterSalt'
 *  						 Attribute 'pwd'
 *  						 Attribute 'comment'
 */

/*
 * Json - Policy
 * 
 * - after starting the program, the JsonObject 'sourceFile' is either loaded or created
 * - in case of login to existing account, one specific user from 'sourceFile' is selected and stored in 'currentUser'
 * - in case of a new user, the JsonObject 'currentUser' is created
 * - when a given user exits the menu, 'currentUser' is either added to 'sourceFile' or replaces an existing entry
 * 
 */



public class Controller {
	private static boolean sourceFileExists = false;
	private static boolean masterPwdExists = false;
	private static boolean changesMade = false;
	private static JsonObject sourceFile;
	private static JsonObject currentUser;
	private static ArrayList<Password> pwds;
	public static ArrayList<String> accountnames;
	private static int numOfPwds;
	private static String masterPassword;

	public ImageView startI;
	public ImageView startClI;
	public ImageView menuI;
	public ImageView setI;
	public ImageView clI;
	public ImageView clErrI;
	public ImageView encBaI;
	public ImageView decBaI;
	public ImageView newBaI;
	public ImageView colInfoI;
	public ImageView userManInfoI;
	public ImageView userManClI;
	public ImageView manI;
	public ImageView newLockI;
	public ImageView newMPClI;
	public ImageView newMPErrI;
	public ImageView newMPDoI;
	public ImageView newPI;
	public ImageView newMI;
	public ImageView encErrI;
	public ImageView encDoI;
	public ImageView encSI;
	public ImageView encClI;
	public ImageView encOvClI;
	public ImageView encOvQuI;
	public ImageView newDelI;
	public ImageView decErrI;
	public ImageView decDoI;
	public ImageView decClI;
	public ImageView newUserCl;
	public ImageView newuCl;
	public ImageView menuopenback;
	public ImageView menuopenI;
	public ImageView moCl;
	public ImageView menuopenMsgCl;
	public ImageView testpwdClI;
	public ImageView testpwdMsgCl;
	public ImageView encryptClI;
	public ImageView decryptClI;
	
	protected Image createImage(String path) {
		return new Image(Controller.class.getResourceAsStream(path));
	}
	
	protected Image createSmallImage(String path, double width, double height) {
		return new Image(Controller.class.getResourceAsStream(path),width,height,false,false);
	}
	
	public Controller() {
		startClI = new ImageView(createImage(FC.closeMsgIPath));
		startI = new ImageView(createSmallImage(FC.settingsIPath,FC.inWi*0.06,FC.inHe*0.1));
		newUserCl = new ImageView(createImage(FC.backIPath));
		newuCl = new ImageView(createImage(FC.closeMsgIPath));
		moCl = new ImageView(createImage(FC.closeMsgIPath));
		menuopenback = new ImageView(createSmallImage(FC.backIPath,FC.inWi*0.05,FC.inHe*0.06));
		menuopenI = new ImageView(createSmallImage(FC.settingsIPath,FC.inWi*0.06,FC.inHe*0.1));
		menuI = new ImageView(createImage(FC.menuIPath));
		menuopenMsgCl = new ImageView(createImage(FC.closeMsgIPath));
		testpwdClI = new ImageView(createImage(FC.backIPath));
		testpwdMsgCl = new ImageView(createImage(FC.closeMsgIPath));
		encryptClI = new ImageView(createImage(FC.backIPath));
		decryptClI = new ImageView(createImage(FC.backIPath));
		setI = new ImageView(createImage(FC.settingsIPath));
		clI = new ImageView(createImage(FC.closeIPath));
		clErrI = new ImageView(createImage(FC.closeMsgIPath));
		encBaI = new ImageView(createImage(FC.backIPath));
		decBaI = new ImageView(createImage(FC.backIPath));
		newBaI = new ImageView(createImage(FC.backIPath));
		colInfoI = new ImageView(createImage(FC.colInfoIPath));
		userManInfoI = new ImageView(createImage(FC.colInfoIPath));
		userManClI = new ImageView(createImage(FC.backIPath));
		manI = new ImageView(createImage(FC.manualIPath));
		newLockI = new ImageView(createImage(FC.newLockIPath));
		newMPClI = new ImageView(createImage(FC.closeMsgIPath));
		newMPErrI = new ImageView(createImage(FC.newErrorIPath));
		newMPDoI = new ImageView(createImage(FC.newDoneIPath));
		newPI = new ImageView(createImage(FC.newPlusIPath));
		newMI = new ImageView(createImage(FC.newMinusIPath));
		newDelI = new ImageView(createImage(FC.newDelIPath));
		encErrI = new ImageView(createSmallImage(FC.newErrorIPath,FC.inWi*0.06,FC.inHe*0.06));
		encDoI = new ImageView(createSmallImage(FC.newDoneIPath,FC.inWi*0.06,FC.inHe*0.06));
		encSI = new ImageView(createImage(FC.newSaveIPath));
		encClI = new ImageView(createSmallImage(FC.closeMsgIPath,FC.inWi*0.06,FC.inHe*0.06));
		encOvClI = new ImageView(createSmallImage(FC.closeMsgIPath,FC.inWi*0.06,FC.inHe*0.06));
		encOvQuI = new ImageView(createImage(FC.newQuestIPath));
		decErrI = new ImageView(createImage(FC.newErrorIPath));
		decDoI = new ImageView(createSmallImage(FC.newDoneIPath,FC.inWi*0.06,FC.inHe*0.06));
		decClI = new ImageView(createSmallImage(FC.closeMsgIPath,FC.inWi*0.06,FC.inHe*0.06));
		
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static String chooseMPwdFromUsers(String username) {
		JsonArray users = sourceFile.getJsonArray("user");
		for(int j=0; j<users.size(); j++) {
			if(users.getJsonObject(j).getString("name").equals(username)) {
				return users.getJsonObject(j).getString("masterPwd");
			}
		}
		return null;
	}
	

	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void deleteCurrentUser() {
		String username = currentUser.getString("name");
		currentUser = null;
		// if user also in source file, delete
		JsonArray users = sourceFile.getJsonArray("user");
		int deleteindex = -1;
		for(int j=0; j<users.size(); j++) {
			if(users.getJsonObject(j).getString("name").equals(username)) {
				deleteindex = j;
				break;
			}
		}
		
		if(deleteindex >= 0) {
			users.remove(deleteindex);
			sourceFile.replace("user", users);
		}
	}
	
	
	
	
	
	/** @author tmf
	 *  
	 * @date 18-02-28 	- adapted routine for Password class.
	 * 							to encrypt:		^ Password = new Password(associate, username, encryptUsername, password, description)
	 *  					or	is encrypted:	^ Password = new Password(associate, username, encryptUsername, password, description, masterSalt)
	 * 					- for now: flag encryptUsername=false (username will be stored directly and not be encrypted)
	 *  				- used Password.assign() routine to overwrite one Password instance. 
	 *  
	 * @exception 	RunOutOfHashException			Cause: 	Password to encrypt is to long (max ~28 digits)
	 * 												Details:If end of given hash is reached while permutation array is
     * 														not completely filled yet up to requested size.
     * 														Retried with new random salt.
     * 														Maximum number of retry is set globally by MAX_PERM_HASH_TRY.
     * 														If maximum retry is reached exception is thrown.
	 * 
	 * 				MasterPenetrationLimitException	Thrown if maximal penetration limit for the master password
     * 												is reached. The limit is set globally with master_penetration_limit.
	 */
	public static void storeAndOverwrite(ArrayList<String[]> toStore) {
		for(int j = 0; j < toStore.size(); j++) {
			boolean done = false;
			Password newPwd = null;
			try {
				newPwd = new Password(toStore.get(j)[0], toStore.get(j)[1], false, 
						toStore.get(j)[3], toStore.get(j)[2]);
			} catch (RunOutOfHashException e) {
				e.printStackTrace();
			}
			for(int k = 0; k < pwds.size(); k++) {
				if(pwds.get(k).getAssociate().equals(toStore.get(j)[0])) {
					pwds.get(k).assign(newPwd);
					done = true;
					break;					
				}
			}
			if(!done) {
				pwds.add(newPwd);
			}			
		}
		setChangesMade();
		setNumOfPwds(pwds.size());
	}
	
	/** @author tmf
	 *  
	 * @date 18-02-28 	- adapted routine for Password class.
	 * 											^ Password = new Password(associate, username, encryptUsername, password, description)
	 * 					- for now: flag encryptUsername=false (username will be stored directly and not be encrypted)
	 *  				- used Password.assign() routine to overwrite one Password instance.
	 *  				- changed number of password attribute from 5 -> 4!
	 *  							^ Do not assume this number to be fixed within your methods.
	 *  				- used Password.decrypt() for getting decrypted Password.username and Password.password
	 *  
	 * @exception 	RunOutOfHashException			Cause: 	Password to encrypt is to long (max ~28 digits)
	 * 												Details:If end of given hash is reached while permutation array is
     * 														not completely filled yet up to requested size.
     * 														Retried with new random salt.
     * 														Maximum number of retry is set globally by MAX_PERM_HASH_TRY.
     * 														If maximum retry is reached exception is thrown.
	 * 
	 * 				MasterPenetrationLimitException	Thrown if maximal penetration limit for the master password
     * 												is reached. The limit is set globally with master_penetration_limit.
	 */
	public static void storeEntryChanges(ArrayList<String> toStore) {
				
		ArrayList<Password> changedPwd = new ArrayList<Password>();
		for(int j = 0; j < toStore.size() / 4; j++) {
			try {
				Password newPwd = new Password(toStore.get(j*4), toStore.get(j*4 + 1), false, 
						toStore.get(j*4 + 2), toStore.get(j*4 + 3));
				changedPwd.add(newPwd);
			} catch (RunOutOfHashException e) {
				e.printStackTrace();
			}	
		}

		for(int j = 0; j < pwds.size(); j++) {
			for(int k = 0; k < changedPwd.size(); k++) {
				if(pwds.get(j).getAssociate().equals(changedPwd.get(k).getAssociate())) {
					pwds.get(j).assign(changedPwd.get(k));
				}
			}
		}		
		setChangesMade();
		setNumOfPwds(pwds.size());
	}
		
	
	
	public static void deleteListEntries(ArrayList<Integer> del) {
		Iterator<Password> iter = pwds.iterator();
		int counter = 0;
		
		while(iter.hasNext()) {
			@SuppressWarnings("unused")
			Password p = iter.next();
			if(del.contains(counter)) {
				iter.remove();
			}
			counter++;
		}
		setChangesMade();
		setNumOfPwds(pwds.size());
	}
	
	public static String[] getExistingNameList() {
		String[] toReturn = new String[pwds.size()];
		
		for(int j = 0; j < pwds.size(); j++) {
			toReturn[j] = pwds.get(j).getAssociate();
		}
		
		return toReturn;
	}
	
	public static void setChangesMade() {
		changesMade = true;
	}
	
	public static boolean getChangesMade() {
		return changesMade;
	}
	
//	public static boolean checkUserInputMPwd(String userInput) {		
//		return Password.check_password(userInput, masterPassword);
//	}

	/** @author tmf
	 *  
	 * @date 18-02-28 	- adapted routine for PasswordSecurity class.
	 * 					- getRandomPassword(int passwordLength,  boolean useSpace, boolean useNumbers, boolean useSymbols, String additionalSymbols)
	 * 																																^ optional
	 */
	public static String generateMasterPassword() {
		return PasswordSecurity.getRandomPassword(8, true, true, true);
	}

	/** @author tmf
	 *  
	 * @date 18-02-28 	- adapted routine for Password class.
	 * 					- @WARNING for now user_password is stored directly in Password class.
	 * 
	 * TODO: Encrypt master with seed + PID
	 */
	public static boolean setGivenMasterPassword(String pwd, String Hash) {
	    try {
			return Password.initialiseMaster(pwd, Hash);
		} catch (RunOutOfHashException e) {
			return false;
		}
	}
	
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void changeMasterPwd(String pwd) {
		// change encryption of existing pwds
		for(int j = 0; j<pwds.size(); j++) {
			try {
				pwds.get(j).changeMaster(pwd);
			} catch (RunOutOfHashException e) {
				e.printStackTrace();
			}
		}
		// set new password
		try {
			Password.initialiseMaster(pwd);
		} catch (RunOutOfHashException e) {
			e.printStackTrace();
		}
	}
	
	public static String setNewMasterPassword(String pwd) {
	    try {
			Password.initialiseMaster(pwd);
		} catch (RunOutOfHashException e) {
		}
	    return Password.getMasterHash();
	}
	

	
	private String GetExecutionPath(){
	    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
	    absolutePath = absolutePath.replaceAll("%20"," ");
	    return absolutePath;
	}
	
	
	public static void setNumOfPwds(int num) {
		numOfPwds = num;
	}
	
	public static int getNumOfPwds() {
		return numOfPwds;
	}
	
	
	/** @author tmf
	 *  
	 * @date 18-02-28 	- adapted routine for Password class.
	 */
	public static void appendNewEntry(String name, String uname, String com, String pwd) {
		try {
			Password toAdd = new Password(name, uname, false, pwd, com);
			pwds.add(toAdd);
			setChangesMade();
			setNumOfPwds(pwds.size());
			
			// extract name and master-hash from existing Json
			String cname = currentUser.getString(FC.name);
			String cpwd = currentUser.getString(FC.masterPwd);
			
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			JsonArrayBuilder builder = Json.createArrayBuilder();
			for(int j=0; j<pwds.size(); j++) {
				builder.add(factory.createObjectBuilder()
						.add(FC.associate, pwds.get(j).getAssociate())
						.add(FC.username, pwds.get(j).getUsername())
						.add(FC.encryptUsername, pwds.get(j).getEncryptUsername())
						.add(FC.masterSalt, pwds.get(j).getMasterSalt())
						.add(FC.pwd, pwds.get(j).getPassword())
						.add(FC.comment, pwds.get(j).getDescription())
						);
			}
			JsonArray tempJsonArr = builder.build();
			
			currentUser = factory.createObjectBuilder()
					.add(FC.name, cname)
					.add(FC.numberOfPwds, numOfPwds)
					.add(FC.masterPwd, cpwd)
					.add(FC.passwords, tempJsonArr)
					.build();
			
		} catch (RunOutOfHashException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean checkForPwdDuplicates(String toCheck) {
		for(int j = 0; j < pwds.size(); j++) {
			if(pwds.get(j).getAssociate().equals(toCheck)) {
				return true;
			}
		}
		return false;
	}
	
	
	public static ArrayList<String[]> getPwds(ArrayList<Boolean> sel) {
		ArrayList<String[]> toReturn = new ArrayList<String[]>();
		for(int j=0; j<sel.size(); j++) {
			if(sel.get(j)) {
				try {
					toReturn.add(pwds.get(j).getAllDecrypted());
				} catch (RunOutOfHashException e) {
					e.printStackTrace();
				}
			}
		}
		return toReturn;
	}
	
	
	/**
	 * Read file including all information from all users, store the Json Object
	 * and create String-list of the names (no further reading)
	 */
	public void initialIOSituation() {
		String decodedPath = this.GetExecutionPath();
		pwds = new ArrayList<Password>();
		
		File inputFile = new File(decodedPath + FC.mainFileName);
		sourceFileExists = inputFile.exists() && !inputFile.isDirectory();
		
		accountnames = new ArrayList<String>();
		if(!sourceFileExists) {
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			JsonArrayBuilder builder = Json.createArrayBuilder();
			
			long currentTime = System.currentTimeMillis();
			
			sourceFile = factory.createObjectBuilder()
					.add(FC.head, factory.createObjectBuilder()
							.add(FC.created, currentTime)
							.add(FC.version, FC.programVersion)
							)
					.add(FC.user, builder.build()
					).build();
			return;
		}
		
		InputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(inputFile);
			
			JsonReader reader = Json.createReader(fileInputStream);
			sourceFile = reader.readObject();
			reader.close();
		} catch(Exception e) {
			sourceFile = null;
		}
		
		if(sourceFile == null) {
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			JsonArrayBuilder builder = Json.createArrayBuilder();
			
			long currentTime = System.currentTimeMillis();
			
			sourceFile = factory.createObjectBuilder()
					.add(FC.head, factory.createObjectBuilder()
							.add(FC.created, currentTime)
							.add(FC.version, FC.programVersion)
							)
					.add(FC.user, builder.build()
					).build();
			return;
		}
		
		JsonArray users = sourceFile.getJsonArray(FC.user);
		for(int j=0; j<users.size(); j++) {
			accountnames.add(users.getJsonObject(j).getString(FC.name));
		}
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void createNewUser(String name, String mpwd) {
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonArrayBuilder builder = Json.createArrayBuilder();
		currentUser = factory.createObjectBuilder()
				.add(FC.name, name)
				.add(FC.numberOfPwds, 0)
				.add(FC.masterPwd, mpwd)
				.add(FC.passwords, builder.build())
				.build();
		accountnames.add(name);
	}	

	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void selectCurrentUser(String username) {
		JsonArray users = sourceFile.getJsonArray(FC.user);
		for(int j = 0; j<users.size();j++) {
			if(users.getJsonObject(j).getString(FC.name).equals(username)) {
				currentUser = users.getJsonObject(j);
			}
		}
		numOfPwds = currentUser.getInt(FC.numberOfPwds);
		JsonArray currentpwd = currentUser.getJsonArray(FC.passwords);
		
		for(int j=0; j<currentpwd.size(); j++) {
			pwds.add(new Password(currentpwd.getJsonObject(j).getString(FC.associate),
					currentpwd.getJsonObject(j).getString(FC.username),
					currentpwd.getJsonObject(j).getBoolean(FC.encryptUsername),
					currentpwd.getJsonObject(j).getString(FC.pwd),
					currentpwd.getJsonObject(j).getString(FC.comment),
					currentpwd.getJsonObject(j).getString(FC.masterSalt)));
		}
	}
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void deleteCurrentPasswords() {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		currentUser = factory.createObjectBuilder()
				.add(FC.name, currentUser.getString(FC.name))
				.add(FC.numberOfPwds, 0)
				.add(FC.masterPwd, currentUser.getString(FC.masterPwd))
				.add(FC.passwords, builder.build()).build();
	}
	
	
	/**
	 * @author fabian
	 * @date 08-2018
	 */
	public static void saveChangesOnLogout() {
		JsonArray users = sourceFile.getJsonArray(FC.user);
		int position = -1;
		for(int j=0; j<users.size(); j++) {
			if(users.getJsonObject(j).getString(FC.name).equals(currentUser.getString(FC.name))) {
				position = j;
				break;
			}
		}
		if(position >= 0) {
			JsonArrayBuilder builder = Json.createArrayBuilder();
			for(int j=0; j<users.size(); j++) {
				if(j != position) {
					builder.add(users.getJsonObject(j));
				} else {
					builder.add(currentUser);
				}
			}
			users = builder.build();
		} else {
			String delim = "";
			if(users.size() > 0) {
				delim = ",";
			}
			
			String merge = users.toString();
			merge = merge.substring(0,merge.length()-1) + delim + currentUser.toString() + "]";
						
			JsonReader jsonReader = Json.createReader(new StringReader(merge));
			users = jsonReader.readArray();
			jsonReader.close();
		}
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObject head = sourceFile.getJsonObject(FC.head);
		
		sourceFile = factory.createObjectBuilder()
				.add(FC.head, head)
				.add(FC.user, users
				).build();
		// reset the current user
		currentUser = null;
	}
	
	public boolean saveChanges() {

		if(!(currentUser == null)) {
			saveChangesOnLogout();
		}
		
		String decodedPath = this.GetExecutionPath();
		
		File existingFile = new File(decodedPath + FC.mainFileName);
		File backupFile = null;

		if(sourceFileExists) {
			existingFile.renameTo(new File(decodedPath + FC.mainFileBackupN));
			backupFile = new File(decodedPath + FC.mainFileBackupN);
		}
		existingFile = new File(decodedPath + FC.mainFileName);
		
		boolean allChecksOk = true;
		JsonWriter jsonWriter;
		try {
			jsonWriter = Json.createWriter(new FileOutputStream(existingFile));
			jsonWriter.writeObject(sourceFile);
			jsonWriter.close();
		} catch (Exception e) {
			allChecksOk = false;			
		}

		if(!allChecksOk) {
			
			if(existingFile.exists() && !existingFile.isDirectory()) {
				existingFile.delete();
			}
			if(sourceFileExists) {
				backupFile.renameTo(new File(decodedPath + FC.mainFileName));
			}
			return false;
		} else {
			if(sourceFileExists) {
				backupFile.delete();
			}
			return true;
		}
		
	}
	
}