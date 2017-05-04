package chess;

import java.util.Arrays;

public class ByteArrayWrapper{
  private final byte[] rawHash;
  
  @Override
  public boolean equals(Object o){
    if(o instanceof ByteArrayWrapper){
      return Arrays.equals(rawHash,((ByteArrayWrapper)o).rawHash);
    }
      return false;
    
  }
  @Override
  public int hashCode(){
    return rawHash[0] | (((int) rawHash[1]) << 8) | (((int) rawHash[2]) << 16) | (((int) rawHash[3]) << 24);
  }

  public ByteArrayWrapper(byte[] me) {
    rawHash = me;
  }
}