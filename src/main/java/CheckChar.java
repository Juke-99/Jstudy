import java.util.logging.Logger;

public class CheckChar {
    Logger LOG = Logger.getLogger("CheckChar");
    
    public int[] check(String string) {
        return check(string, 0);
    }
    
    public int[] check(String string, int start) {
        int c;
        int[] stringNum = new int[string.length()];
        
        for(int i = start; i < string.length(); i++) {
            c = string.charAt(i);
            
            if(c < 0x08) {
                LOG.info("0x80");
                stringNum[i] = c;
            } else if (c < 0x800) {
                LOG.info("0x800");
                stringNum[i] = c;
            } else if (c < 0xd800 || c > 0xdfff) {
                LOG.info("0xd800 ~ 0xdfff");
                stringNum[i] = c;
            }
        }
        
        return stringNum;
    }
}
