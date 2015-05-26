package com.himself12794.usefulthings.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.google.common.collect.Maps;
import com.himself12794.usefulthings.spell.Spell;

public class ServerMessageQueue {
	
	public static class CastObject {
		private World world;
		private EntityLivingBase caster;
		private Spell spell;
		private float modifier;
		
		
		public CastObject(World world, EntityLivingBase caster, Spell spell, float modifier){
			this.world = world;
			this.caster = caster;
			this.spell = spell;
			this.modifier = modifier;
		}
	}
	
	public static class MovingObjectsQueue {
		private static final MovingObjectsQueue INSTANCE = new MovingObjectsQueue();
		//private Map<CastObject, MovingObjectPosition> queue = Maps.newHashMap();
		private CastObject cast;
		private MovingObjectPosition pos;
		
		public static void queueCast(CastObject cast) {
			
			INSTANCE.cast = cast;
			//INSTANCE.queue.put(cast, null);
		}
		
		public static boolean hasCastObject(CastObject casted) {
			return INSTANCE.cast != null;
		}
		
		public static MovingObjectPosition getCastObjectPosition(CastObject casted) {
			if (hasCastObject(casted)) return INSTANCE.pos;
			return null;
		}
		
		public static void associateMovingPosObj(MovingObjectPosition pos) {
			INSTANCE.pos = pos;
		}

	}
	
	
	
}
