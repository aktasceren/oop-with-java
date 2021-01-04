package application;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FileOperations {
	
	// 171805053 Soner Cengiz
	// 181805027 Ceren Aktaþ

	  /** Read fixed number of characters from a DataInput stream */
	  public static String readFixedLengthString(int size,DataInput in) throws IOException 
	  {
	    // Declare an array of characters
	    char[] chars = new char[size];

	    // Read fixed number of characters to the array
	    for (int i = 0; i < size; i++)
	      chars[i] = in.readChar();

	    return new String(chars);
	  }

	  /** Write fixed number of characters to a DataOutput stream */
	  public static void writeFixedLengthString(String s, int size,DataOutput out) throws IOException 
	  {
	    char[] chars = new char[size];

	    // Fill in string with characters
	    s.getChars(0, s.length(), chars, 0);

	    // Fill in blank characters in the rest of the array
	    for (int i = Math.min(s.length(), size); i < chars.length; i++)
	    {
	      chars[i] = ' ';
	    }

	    // Create and write a new string padded with blank characters
	    out.writeChars(new String(chars));
	  }


}
//171805053 Soner Cengiz
//181805027 Ceren Aktaþ
