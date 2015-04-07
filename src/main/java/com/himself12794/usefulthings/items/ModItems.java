package com.himself12794.usefulthings.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import com.himself12794.usefulthings.Reference;
import com.himself12794.usefulthings.items.armor.AssassinBoots;
import com.himself12794.usefulthings.items.armor.AssassinHood;
import com.himself12794.usefulthings.items.armor.AssassinPants;

public class ModItems {
	
	public static final int NUMBER = 4;
	public static Item coalBall;
	public static Item coalDust;
	public static Item hiddenBlade;
	public static Item hiddenBladeRetracted;
	public static Item ingotAnimus;
	public static Item strangeMirror;
	public static Item assassinBoots;
	public static Item assassinPants;
	public static Item assassinHood;
	
	public static void addItems() {
		coalBall = new CoalBall();
		coalDust = new CoalDust();
		hiddenBlade = new HiddenBlade();
		hiddenBladeRetracted = new HiddenBladeRetracted();
		ingotAnimus = new AnimusIngot();
		strangeMirror = new StrangeMirror();
		assassinBoots = new AssassinBoots();
		assassinPants = new AssassinPants();
		assassinHood = new AssassinHood();
	}
	
	public static void registerTextures( FMLInitializationEvent event ) {
		if(event.getSide() == Side.CLIENT) {
			
		    RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		    		
		    renderItem.getItemModelMesher().register(coalBall, 0, new ModelResourceLocation(Reference.MODID + ":" + ((CoalBall) coalBall).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(coalDust, 0, new ModelResourceLocation(Reference.MODID + ":" + ((CoalDust) coalDust).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(hiddenBlade, 0, new ModelResourceLocation(Reference.MODID + ":" + ((HiddenBlade) hiddenBlade).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(hiddenBladeRetracted, 0, new ModelResourceLocation(Reference.MODID + ":" + ((HiddenBladeRetracted) hiddenBladeRetracted).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(ingotAnimus, 0, new ModelResourceLocation(Reference.MODID + ":" + ((AnimusIngot) ingotAnimus).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(strangeMirror, 0, new ModelResourceLocation(Reference.MODID + ":" + ((StrangeMirror) strangeMirror).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(assassinBoots, 0, new ModelResourceLocation(Reference.MODID + ":" + ((AssassinBoots) assassinBoots).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(assassinPants, 0, new ModelResourceLocation(Reference.MODID + ":" + ((AssassinPants) assassinPants).getName(), "inventory"));
		    renderItem.getItemModelMesher().register(assassinHood, 0, new ModelResourceLocation(Reference.MODID + ":" + ((AssassinHood) assassinHood).getName(), "inventory"));
		    
		}
	}
}
