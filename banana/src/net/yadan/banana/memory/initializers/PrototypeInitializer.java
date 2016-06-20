/*
 * Copyright (C) 2013 Omry Yadan <omry@yadan.net>
 * All rights reserved.
 *
 * See https://github.com/omry/banana/blob/master/BSD-LICENSE for licensing information
 */
package net.yadan.banana.memory.initializers;

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
  
  public void writeToIntBuffer(IntBuffer buffer) {
	  buffer.put(this.prototype.length);
	  buffer.put(this.prototype);
  }
  
  public static PrototypeInitializer readFromIntBuffer(IntBuffer buffer) {
	  int prototype[] = new int[buffer.get()];
	  buffer.get(prototype);
	  return new PrototypeInitializer(prototype);
  }
}
