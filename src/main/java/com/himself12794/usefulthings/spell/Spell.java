package com.himself12794.usefulthings.spell;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.himself12794.usefulthings.UsefulThings;
import com.himself12794.usefulthings.entity.EntitySpell;
import com.himself12794.usefulthings.network.MessageServer;
import com.himself12794.usefulthings.util.Reference;
import com.himself12794.usefulthings.util.UsefulMethods;

public class Spell {
	
	private String displayName;
	private SpellType type = SpellType.HIT_SCAN;
	private float power = 2.0F;
	private int duration = 20;
	private int coolDown = 4;
	
	/**
	 * This casts the spell.
	 * Returning true will trigger the cool down.
	 * 
	 * @param world
	 * @param caster
	 * @param stack
	 * @param modifier
	 * @return success
	 */
	public final boolean cast(World world, EntityLivingBase caster, ItemStack tome, float modifier) {
		return type.action(this, world, caster, tome, modifier);
	}
	/**
	 * Called when the spell affects a target.
	 * Used only by types HIT_SCAN and RANGED, HIT_SCAN when it impacts an entity, and
	 * RANGED when it impacts anything. Return determines whether the stack should be damaged.
	 * 
	 * @param world
	 * @param target
	 * @param caster
	 * @param modifier
	 * @param stack
	 * @return success
	 */
	public boolean onStrike(World world, MovingObjectPosition target, EntityLivingBase caster, float modifier ) {
		boolean flag = false;
		if (target.entityHit != null) {
			flag = target.entityHit.attackEntityFrom(DamageSource.magic, getPower() * modifier);
			((EntityLivingBase)target.entityHit).setRevengeTarget(caster);
		} 
		return flag;
	}
	
	/**
	 * Called when the spell is cast.
	 * Determines how the spell affects the player and world when cast.
	 * Return whether or not a successful use occurred. Cooldown only occurs if both are successful.
	 * 
	 * @param world
	 * @param caster
	 * @param modifier
	 * @param stack
	 * @return whether or not the spell counts as successful, and should count as a use
	 */
	public boolean onCast(World world, EntityLivingBase caster, ItemStack stack, float modifier) {
		return true;
	}
	
	/**
	 * The action to be performed when the spell is being prepared, before it is actually cast.
	 * 
	 * @return whether or not casting should continue.
	 */
	public boolean onPrepareSpell(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		return true;
	}
	
	/**
	 * Called when spell is aborted before duration is over.
	 * Return false prevent the cooldown
	 * 
	 * @param stack
	 * @param world
	 * @param playerIn
	 * @param timeLeft
	 * @return whether or not to cancel the cooldown
	 */
	public boolean onFinishedCastingEarly(ItemStack stack, World world, EntityPlayer playerIn, int timeLeft) {
		return true;
	}
	
	/**
	 * Called when spell is done being cast, before the cool down is triggered.
	 * Return false to negate the cool down.
	 * 
	 * @param stack
	 * @param world
	 * @param caster
	 * @return whether or not to negate the cool down
	 */
	public boolean onFinishedCasting(ItemStack stack, World world, EntityPlayer caster){ 
		return true;
	}
	
	/**
	 * Determines whether or not spells that have a duration should show this on the tooltip.
	 * 
	 * @param stack
	 * @param caster
	 * @param par3 whether or not advanced tooltips are enabled
	 * @return
	 */
	public boolean showDuration(ItemStack stack, EntityPlayer caster, boolean par3) {
		return true;
	}
	
	
	/**Gets the spell description.
	 * @param player 
	 * @param stack 
	 * 
	 * @return
	 */
	public String getInfo(ItemStack stack, EntityPlayer player) {
		return "Deals damage to an enemy";
	}
	
	public String getDisplayName() {
		return ("" + StatCollector.translateToLocal(getUnlocalizedName() + ".name")).trim();
	}
	protected Spell setType(SpellType type) { this.type = type; return this; }
	
	public SpellType getType() { return type; }
	
	protected Spell setPower(float value) { power = value; return this; }

	public float getPower() { return power; }
	
	protected Spell setCoolDown(int amount) { coolDown = amount; return this; }
	
	public int getCoolDown() { return coolDown; }
	
	protected Spell setUnlocalizedName( String name ) { displayName = name; return this; }
	
	public String getUnlocalizedName() { return "spell." + displayName; }
	
	protected Spell setDuration(int time) { duration = time; return this; }
	
	public int getDuration() { return duration; }

	public float getBrightness() { return 5.0F; }
	
	/**
	 * Location for model if the default Magic Tome one is not desired.
	 * 
	 * @param stack
	 * @param player
	 * @param useRemaining
	 * @return
	 */
	public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {
		return null;
	}
	
	public static void registerSpells() {

		Spell damage = new Damage();		
		Spell incinerate = new Incinerate();
		Spell lightning = new Lightning();
		Spell heal = new Heal();
		Spell death = new Death();
		Spell dummy = new Dummy();
		
		UsefulThings.logger.info("Registered [" + SpellRegistry.getSpellCount() + "] spells");
		
	}
	
	public final ItemStack setSpell(ItemStack stack) {
		NBTTagCompound nbt = null;
		String spell = getUnlocalizedName();
		if (!stack.hasTagCompound()) {
			nbt = new NBTTagCompound();
		} else {
			nbt = stack.getTagCompound();
		}
		if (!spellExists(spell)) {
			UsefulThings.logger.fatal("Cannot set unregistered spell \"" + spell + "\"");
		} else {
			nbt.setString(Reference.MODID + ".spell.currentSpell", spell);
			stack.setTagCompound(nbt);
		}
		return stack;
	}
	
	public static boolean spellExists(String unlocalizedName) {
		return SpellRegistry.getSpells().containsKey(unlocalizedName);
	}
	
	public final boolean canCastSpell( EntityPlayer player ) {
		
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		return coolDowns.getInteger(getUnlocalizedName()) <= 0;
	}
	
	public final int getCoolDownRemaining(EntityPlayer player) {
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		return coolDowns.getInteger(getUnlocalizedName());
	}
	
	public final void setCoolDown(EntityPlayer player, int amount) {
		int id = player.getEntityId();
		NBTTagCompound coolDowns = player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
		
		coolDowns.setInteger(getUnlocalizedName(), amount);
		player.getEntityData().setTag(Reference.MODID + ".spell.spellCooldowns", coolDowns);
	}
	
	public final void triggerCooldown( EntityPlayer player ) {
		UsefulThings.print("Triggering cooldown");
		setCoolDown(player, getCoolDown());
	}

	public static boolean hasSpell(ItemStack stack) {
		return stack.hasTagCompound() && stack.getTagCompound().hasKey(Reference.MODID + ".spell.currentSpell");
	}
	
	public static Spell getSpell(ItemStack stack) {
		return SpellRegistry.lookupSpell(stack);
	}
	
	public static NBTTagCompound getCooldowns(EntityPlayer player) {
		return player.getEntityData().getCompoundTag(Reference.MODID + ".spell.spellCooldowns");
	}	
}
