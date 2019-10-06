package Password;

import crypt.HashSetting;
import crypt.Hashing;

public class MasterContainer {
	HashSetting masterHashOption, passwordHashOption;
	
	String hashed_master;
	
	public static MasterContainer[] masters = null;
	
	public static void add(String master_pwd, HashSetting masterSettings, HashSetting encryptSettings) {
		String hashed_master = Hashing.hash_password(master_pwd, masterSettings);
		master_pwd = null;
		
		MasterContainer newMaster = new MasterContainer(hashed_master, masterSettings, encryptSettings);
		
		int new_id = 1;
		if (masters == null){
			masters = new MasterContainer[1];
			masters[0] = newMaster;
		} else {
			new_id = masters.length;
		
			MasterContainer[] temp = new MasterContainer[new_id+1];
			for (int ii=0; ii<new_id; ii++){
				temp[ii] = masters[ii]; 
			}
			temp[new_id] = newMaster;
		}
		//return new_id;
	}

	private MasterContainer(String hashed_master, HashSetting masterSettings, HashSetting encryptSettings) {
	    this.hashed_master = hashed_master;
	    this.masterHashOption = masterSettings;
	    this.passwordHashOption = encryptSettings;
    }
}
