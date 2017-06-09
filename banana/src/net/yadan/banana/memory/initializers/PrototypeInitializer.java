/*
 * Copyright (C) 2013 Omry Yadan <omry@yadan.net>
 * All rights reserved.
 *
 * See https://github.com/omry/banana/blob/master/BSD-LICENSE for licensing information
 */
package net.yadan.banana.memory.initializers;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import net.yadan.banana.memory.IPrimitiveAccess;
import net.yadan.banana.memory.MemInitializer;

public class PrototypeInitializer implements MemInitializer {
  int prototype[];

  public PrototypeInitializer(int recordSize) {
    this(new int[recordSize]);

  }

  public PrototypeInitializer(int prototype_[]) {
    prototype = prototype_;
  }

  @Override
  public void initialize(IPrimitiveAccess mem, int pointer, int blockSize) {
    mem.setInts(pointer, 0, prototype, 0, prototype.length);
  }
  
  public void writeToByteBuffer(ByteBuffer buffer) {
	  buffer.putInt(this.prototype.length);

      IntBuffer intBuffer = buffer.asIntBuffer();
      int startingPosition = intBuffer.position();
      intBuffer.put(this.prototype);
      buffer.position(buffer.position() + (intBuffer.position()-startingPosition)*4);
  }
  
  public static PrototypeInitializer readFromByteBuffer(ByteBuffer buffer) {
	  int prototype[] = new int[buffer.getInt()];

      IntBuffer intBuffer = buffer.asIntBuffer();
      int startingPosition = intBuffer.position();
      intBuffer.get(prototype);
      buffer.position(buffer.position() + (intBuffer.position()-startingPosition)*4);
	  return new PrototypeInitializer(prototype);
  }
}
