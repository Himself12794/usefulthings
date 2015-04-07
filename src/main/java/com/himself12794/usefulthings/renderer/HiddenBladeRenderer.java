package com.himself12794.usefulthings.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;

import com.himself12794.usefulthings.models.HiddenBladeModel;

public class HiddenBladeRenderer extends ItemRenderer {
	private final HiddenBladeModel model;
	
	public HiddenBladeRenderer(Minecraft mc) {
		super(mc);
		this.model = new HiddenBladeModel();
	}
	
	
}
