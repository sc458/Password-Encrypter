package crypt;

public class HashSetting {
    // Choose HASH_SIZE in byte minimal 2*ROUND((MAX_PASSWORD_LENGTH + 2.08)/1.268 )
    public final int SALT_BYTE_SIZE;
    public final int HASH_BIT_SIZE;
    
    public final int HASH_ITERATIONS;
    public final String HASH_ALGORITHM;	
    
	public static HashSetting[] Setting = null;
	
	public boolean equals(HashSetting compare){
		if(this.HASH_BIT_SIZE != compare.HASH_BIT_SIZE){
			return false;
		}
		if(this.SALT_BYTE_SIZE != compare.SALT_BYTE_SIZE){
			return false;
		}
		if(this.HASH_ITERATIONS != compare.HASH_ITERATIONS){
			return false;
		}
		if(! this.HASH_ALGORITHM.equals(compare.HASH_ALGORITHM)){
			return false;
		}
		return true;
	}
	
/*	public void add(int salt_byte_size, int hash_byte_size, int iterations, String algorithm){
		if (Setting == null){
			Setting = new HashSetting[1];
			Setting[0] = new HashSetting(salt_byte_size, hash_byte_size, iterations, algorithm);
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
	*/
	
    public HashSetting(){
        // Choose HASH_SIZE in byte minimal 2*ROUND((MAX_PASSWORD_LENGTH + 2.08)/1.268 )
    	this.SALT_BYTE_SIZE = 28;
    	this.HASH_BIT_SIZE = 28*8;
    	
    	this.HASH_ITERATIONS = 200;
//    	this.master_penetration_limit = 10;
    	
    	this.HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    }
    
    public HashSetting(int salt_byte_size, int hash_byte_size, int iterations, String algorithm){
        // Choose HASH_SIZE in byte minimal 2*ROUND((MAX_PASSWORD_LENGTH + 2.08)/1.268 )
    	this.SALT_BYTE_SIZE = salt_byte_size;
    	this.HASH_BIT_SIZE = hash_byte_size*8;
    	
    	this.HASH_ITERATIONS = iterations;
//    	this.master_penetration_limit = 10;
    	
    	this.HASH_ALGORITHM = algorithm;//"PBKDF2WithHmacSHA1";
    }
}
