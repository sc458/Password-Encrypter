package crypt;

import java.io.UnsupportedEncodingException;


public class DataTypes {
	
	/**	hex_add_bin()
	 * 
	 * Adding two Hex-Strings binary.
	 * 
     * @param	hex_a	Hex-String		First Hex Argument for addition.
     * 
     * @param	hex_b	Hex-String		Second Hex Argument for addition.
     *      
     * @return			Hex-String		Binary Addition of hex_a^hex_b
     */
    public static String hex_add_bin(String hex_a, String hex_b){
    	byte[] b_hex_a = HextoByte(hex_a);
    	byte[] b_hex_b = HextoByte(hex_b);
    	
    	int size_a = b_hex_a.length;
    	int size_b = b_hex_b.length;
    	
    	byte[] added;
    	int ind, delta;
    	if(size_a > size_b){
    		added = new byte[size_a];
    		ind = size_a-size_b;
    		for(int ii=0; ii < ind; ii++){
    			added[ii] = b_hex_a[ii]; 
    		}
    		delta = -ind;
    	} else {
    		added = new byte[size_b];
    		delta = size_b-size_a;
    		for(int ii=0; ii < delta; ii++){
    			added[ii] = b_hex_b[ii]; 
    		}
    		ind = 0;
    	}
  	
    	for (int ii=ind; ii < size_a; ii++){
    		added[ii] = (byte) ((byte) b_hex_a[ii] ^ (byte) b_hex_b[ii+delta]);
    	}
    	
    	return BytetoHex(added);    	
    }
     
	/**	StringtoHex()
	 * 
	 * Encoding String argument to a Hex-String.
	 * 
     * @param	arg		String		Argument to encode.
     *   
     * @return			Hex-String	Encoded Hex-String.
     */
    public static String StringtoHex(String arg){
    	//System.out.println("String2Hex: "+arg);
		try {
	    	byte[] b_temp = arg.getBytes("UTF-8");
	    	//DataTypes.pr_bytes(b_temp, "String2Hex");
	        return DataTypes.BytetoHex(b_temp);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "-1";
    }
    
	/**	HextoString()
	 * 
	 * Decoding Hex-String argument to a String.
	 * 
     * @param	arg		Hex-String	Argument to decode.
     *   
     * @return			String		Decoded String.
     */
    public static String HextoString(String arg){
	    try {
			byte[] b_temp = DataTypes.HextoByte(arg);
			return new String(b_temp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "-1";
    }
	
	/**	concate_byte_arrays()
	 * 
	 * Concating two byte arrays.
	 * 
     * @param	a	byte[]	First byte array to concate.
     *   
     * @param	b	byte[]	Second byte array to concate.
     * 
     * @return		byte[]	Concated byte array.
     */
	public static byte[] concate_byte_arrays(byte[] a, byte[] b) {
		byte[] conc = new byte[a.length + b.length]; 
		System.arraycopy(a, 0, conc, 0, a.length); 
		System.arraycopy(b, 0, conc, a.length, b.length); 
    return conc;
	} 
	
    /** BytetoHex()
     * 
     * Encoding a byte array into a Hex-String.
     *
     * @param   array	byte[]		Array to encode
     * 
     * @return  		Hex-String	Encoded Hex-String with length = 2*size(array)
     */
    public static String BytetoHex(byte[] array) {
    	String hex = "";
    	
    	for (int ii=0; ii<array.length; ii++){
    		hex = hex+String.format("%2x", array[ii]).replace(" ", "0");
    	}
    	
    	return hex;
    }
    
    /** HextoByte()
     * 
     * Decoding a Hex-String to a byte array.
     *
     * @param   hex_String	Hex-String	Hex-String to decode
     * 
     * @return  			byte[]		Decoded byte array with size = hex_String.length/2
     */
    public static byte[] HextoByte(String hex_String) {
        byte[] binary = new byte[hex_String.length() / 2];
        for(int ii=0; ii < binary.length; ii++) {
            binary[ii] = (byte) Integer.parseInt(hex_String.substring(2*ii, 2*ii+2), 16);
        	//System.out.println("H2B: "+hex_String.substring(2*ii, 2*ii+2)+" - "+Integer.parseInt(hex_String.substring(2*ii, 2*ii+2), 16)+" - "+ binary[ii]);
        }
        return binary;
    }
	
	/**	pr_bytes()
	 * 
	 * Prints a byte array to screen with given stamp
	 * 
     * @param	arg		byte[]	Hash to be printed.
     * 
     * @param	code 	String	Stamp for terminal output.
     */
    public static void pr_bytes(byte[] arg, String code){
    	System.out.println("*** [Printing Bytes] "+code+" ***");
    	System.out.print("* | ");
    	for (int ii=0; ii<arg.length; ii++){
    		System.out.print(arg[ii]+" | ");
    	}
    	System.out.println();
    }
    
    /** slowEquals()
     * 
     * Comparing two byte arrays in length-constant time.
     *
     * @param   a	byte[]	First array for comparison
     *
     * @param   b	byte[]	Second array for comparison
     * 
     * @return  	boolean	True -> Arrays match
     */
    private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int ii = 0; ii < a.length && ii < b.length; ii++)
            diff |= a[ii] ^ b[ii];
        return diff == 0;
    }
}