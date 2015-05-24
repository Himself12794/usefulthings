package com.himself12794.usefulthings.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.util.MovingObjectPosition;

import com.google.common.collect.Maps;

public class ServerMessageQueue {
	
	public static class MovingObjectsQueue {
		private static final MovingObjectsQueue INSTANCE = new MovingObjectsQueue();
		private List<MovingObjectPosition> queue = new ArrayList();
		
		public static void queuePosition(MovingObjectPosition pos) {
			
			INSTANCE.queue.add(pos);
		}
		
		public static MovingObjectPosition getNextPostionInQueue() {
			if (!INSTANCE.queue.isEmpty()) {
				MovingObjectPosition pos = INSTANCE.queue.get(0);
				INSTANCE.queue.remove(0);
				return pos;
			}
			return null;
		}

	}
	
	
}
