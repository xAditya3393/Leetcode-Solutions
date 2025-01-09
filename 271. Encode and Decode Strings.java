
public class Codec {

    //use delimiter to separate strings
    public String encode(List<String> strs) {
        StringBuilder encodeInput = new StringBuilder();
        
        String delimiter = Character.toString((char)257);
        
        for(String s: strs) encodeInput.append(s).append(delimiter);
        encodeInput.deleteCharAt(encodeInput.length() - 1);
        
        return encodeInput.toString();
    }

    //make sure delimiter only exists in between string, not at start and end 
    //s.split(delimiter,-1) -> gets rid of "" 
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        String delimiter = Character.toString((char)257);
        
        for(String st: s.split(delimiter,-1)){
            if(st != delimiter) result.add(st);
        }
        
        return result;        
    }
}



public class Codec {

    //Chunked Transfer Encoding - low level optimization used in HTTP v1.1
    //Based on https://en.wikipedia.org/wiki/Chunked_transfer_encoding

    
    //encode
    //convert the length of each string to an 4 bytes number -> 4 characters (1 character = 1 byte memory size)
    //append 4 byte character set to the begining of each string
    
    //decode
    //convert the 4 byte character set to int
    //recover the individual string and add to result

    //Advantages
    //note characters used to keep occurances since is takes less memory
    //This solution is independent of the character set of the input

        
    public String encode(List<String> strs) {
        StringBuilder encodeInput = new StringBuilder();
         
        for(String s: strs){
            encodeInput.append(stringLengthByteConversion(s));
            encodeInput.append(s);  
        } 
        
        return encodeInput.toString();
    }
    
    //get the 4 byte character set of input string of length l
    private String stringLengthByteConversion(String input){
        int inputSize = input.length();
        char[] bytes = new char[4];
        
        for(int byteNumber = 3; byteNumber >= 0; byteNumber--){
            bytes[3-byteNumber] = (char)(inputSize >> (byteNumber*8) & 0xff);
        }
        
        return new String(bytes);
    }
    
     
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        int start = 0; 
        
        while(start < s.length()){
            int currentLength = stringToByteLengthConversion(s.substring(start, start+4));
            start += 4;
            result.add(s.substring(start, start+currentLength));
            start+= currentLength;
        }
        
        return result;        
    }
    
    //get the length of the string to be recovered
    private int stringToByteLengthConversion(String input){
        
        int result = 0;
        for(char c : input.toCharArray()){
            result = (result << 8) + (int)c;  
        }
        
        return result;
    }
}

