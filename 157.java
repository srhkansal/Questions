/**
Easy
Given a file and assume that you can only read the file using a given method read4, implement a method to read_n_characters.
Method read4:
The API read4reads 4 consecutive characters from the file, then writes those characters into the buffer arraybuf.
The return value is the number of actual characters read.
**/


public int read(char[] buf, int n) {
  boolean eof = false;      // end of file flag
  int total = 0;            // total bytes have read
  char[] tmp = new char[4]; // temp buffer

  while (!eof && total < n) {
    int count = read4(tmp);

    // check if it's the end of the file
    eof = count < 4;

    // get the actual count
    count = Math.min(count, n - total);

    // copy from temp buffer to buf
    for (int i = 0; i < count; i++) 
      buf[total++] = tmp[i];
  }

  return total;
}
