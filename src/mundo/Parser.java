package mundo;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Parser {
	private byte[] buf = new byte[4 * 1024];
    private int index;
    private BufferedInputStream in;
    private int total;

    public Parser(InputStream inputStream) {
        in = new BufferedInputStream(inputStream);
    }
    private int scan() {
        if (index >= total) {
            index = 0;
            try {
                total = in.read(buf);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (total <= 0) return -1;
        }
        return buf[index++];
    }
    public int pushBack(){
    	return buf[--index];
    }
    public int scanInt() {
        int integer = 0;
        int n = scan();
        while (isWhiteSpace(n)) n = scan();
        int neg = 1;
        if (n == '-') {
            neg = -1;
            n = scan();
        }
        if(n=='+'){
        	n=scan();
        }
        while (isNumber(n)) {
            integer *= 10;
            integer += n - '0';
            n = scan();
        }
        pushBack();
        return neg * integer;
    }
    public int scanChar(){
    	int n=scan();
    	while (isWhiteSpace(n)) n = scan();
    	return n;
    }
    private boolean isNumber(int n){
    	if (n >= '0' && n <= '9')return true;
    	return false;
    }
    private boolean isWhiteSpace(int n) {
        if (n == ' ' || n == '\n' || n == '\r' || n == '\t') return true;
        else return false;
    }
}