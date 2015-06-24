package thread.calculation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class NumberWriter {
	public void writing(String neperNumber) {
        try {
            File result = new File("D:/Development/RSA/result.txt");
            FileOutputStream is = new FileOutputStream(result);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);
            w.write(neperNumber);
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file result.txt");
        }
	}
}
