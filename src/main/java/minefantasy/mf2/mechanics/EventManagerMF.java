package minefantasy.mf2.mechanics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.api.helpers.ArrowEffectsMF;
import minefantasy.mf2.api.helpers.EntityHelper;
import minefantasy.mf2.api.helpers.TacticalManager;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.LevelupEvent;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SyncSkillEvent;
import minefantasy.mf2.api.stamina.StaminaBar;
import minefantasy.mf2.api.tool.IHuntingItem;
import minefantasy.mf2.api.weapon.WeaponClass;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.config.ConfigStamina;
import minefantasy.mf2.entity.EntityItemUnbreakable;
import minefantasy.mf2.farming.FarmingHelper;
import minefantasy.mf2.item.ItemResearchScroll;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.weapon.ItemWeaponMF;
import minefantasy.mf2.network.packet.LevelupPacket;
import minefantasy.mf2.network.packet.SkillPacket;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.ChunkEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventManagerMF
{
	public static Random rand = new Random();
	@SubscribeEvent
	public void tryDropItems(LivingDropsEvent event)
	{
		EntityLivingBase dropper = event.entityLiving;
		
		if(dropper instanceof EntityGhast)
		{
			//TODO: TEMPORARY
			int dropCount = (1 + rand.nextInt(event.lootingLevel+1) + (2 + rand.nextInt(8)));
			
			for(int a = 0; a < dropCount; a++)
			{
				dropper.entityDropItem(new ItemStack(ComponentListMF.dragon_heart), 0.0F);
			}
		}
		if(dropper instanceof EntityChicken)
		{
			//TODO: TEMPORARY
			int dropCount = 1 + rand.nextInt(event.lootingLevel+1*4);
			
			for(int a = 0; a < dropCount; a++)
			{
				dropper.entityDropItem(new ItemStack(Items.feather), 0.0F);
			}
		}
		if(dropper instanceof IAnimals && !(dropper instanceof IMob))
		{
			if(ConfigHardcore.hunterKnife && !dropper.getEntityData().hasKey("hunterKill"))
			{
				event.setCanceled(true);
				return;
			}
			if(ConfigHardcore.lessHunt)
			{
				alterDrops(dropper, event);
			}
		}
		if(getRegisterName(dropper).contains("Horse"))
		{
			int dropCount = rand.nextInt(3 + event.lootingLevel);
			if(ConfigHardcore.lessHunt)
			{
				dropCount = 1 + rand.nextInt(event.lootingLevel+1);
			}
			
			Item meat = dropper.isBurning() ? FoodListMF.horse_cooked : FoodListMF.horse_raw;
			for(int a = 0; a < dropCount; a++)
			{
				dropper.entityDropItem(new ItemStack(meat), 0.0F);
			}
		}
		if(getRegisterName(dropper).contains("Wolf"))
		{
			int dropCount = rand.nextInt(3 + event.lootingLevel);
			if(ConfigHardcore.lessHunt)
			{
				dropCount = 1 + rand.nextInt(event.lootingLevel+1);
			}
			
			Item meat = dropper.isBurning() ? FoodListMF.wolf_cooked : FoodListMF.wolf_raw;
			for(int a = 0; a < dropCount; a++)
			{
				dropper.entityDropItem(new ItemStack(meat), 0.0F);
			}
		}
		dropLeather(event.entityLiving, event);
		
		if(dropper instanceof EntityWitch)
		{
			dropper.entityDropItem(ItemResearchScroll.getRandomDrop(), 0.0F);
		}
		if(dropper instanceof EntitySkeleton)
		{
			EntitySkeleton skeleton = (EntitySkeleton)dropper;
			
			if((skeleton.getHeldItem() == null || !(skeleton.getHeldItem().getItem() instanceof ItemBow)) && event.drops != null && !event.drops.isEmpty())
			{
				Iterator<EntityItem> list = event.drops.iterator();
				
				while(list.hasNext())
				{
					EntityItem entItem = list.next();
					ItemStack drop = entItem.getEntityItem();
					
					if(drop.getItem() == Items.arrow)
					{
						entItem.setDead();
					}
				}
			}
		}
	}
	
	private void dropLeather(EntityLivingBase mob, LivingDropsEvent event) 
	{
		boolean dropHide = shouldAnimalDropHide(mob);
		Item hide = getHideFor(mob);
		
		if(event.drops != null && !event.drops.isEmpty())
		{
			Iterator<EntityItem> list = event.drops.iterator();
			
			while(list.hasNext())
			{
				EntityItem entItem = list.next();
				ItemStack drop = entItem.getEntityItem();
				
				if(drop.getItem() == Items.leather)
				{
					entItem.setDead();
					dropHide = true;
				}
			}
		}
		if(dropHide && hide != null && !(ConfigHardcore.hunterKnife && !mob.getEntityData().hasKey("hunterKill")))
		{
			mob.entityDropItem(new ItemStack(hide), 0.0F);
		}
	}

	private Item getHideFor(EntityLivingBase mob)
	{
		Item[] hide = new Item[]{ComponentListMF.rawhideSmall, ComponentListMF.rawhideMedium, ComponentListMF.rawhideLarge};
		int size = getHideSizeFor(mob);
		if(mob.isChild())
		{
			size --;
		}
		
		if(size <= 0)
		{
			return null;
		}
		if(size > hide.length)
		{
			size = hide.length;
		}
		
		return hide[size-1];
	}
	private int getHideSizeFor(EntityLivingBase mob)
	{
		if(mob.getClass().getName().endsWith("EntityCow") || mob.getClass().getName().endsWith("EntityHorse"))
		{
			return 3;
		}
		if(mob.getClass().getName().endsWith("EntitySheep"))
		{
			return 2;
		}
		if(mob.getClass().getName().endsWith("EntityPig"))
		{
			return 1;
		}
		
		int size = mob.myEntitySize.ordinal()+1;
		if(size <= 1)
		{
			return 0;
		}
		if(size == 2)
		{
			return 1;
		}
		else if(size <= 4)
		{
			return 2;
		}
		return 3;
	}

	private boolean shouldAnimalDropHide(EntityLivingBase mob) 
	{
		if(mob.getClass().getName().endsWith("EntityWolf") || mob.getClass().getName().endsWith("EntityPig") || mob.getClass().getName().endsWith("EntitySheep") || mob.getClass().getName().endsWith("EntityCow") || mob.getClass().getName().endsWith("EntityHorse"))
		{
			return true;
		}
		if(mob instanceof EntityWolf || mob instanceof EntityCow || mob instanceof EntityPig || mob instanceof EntitySheep || mob instanceof EntityHorse)
		{
			return true;
		}
		return false;
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event)
	{
		Entity dropper = event.entity;
		
		if(ConfigExperiment.stickArrows && !dropper.worldObj.isRemote)
		{
			ArrayList<ItemStack> stuckArrows = (ArrayList<ItemStack>) ArrowEffectsMF.getStuckArrows(dropper);
			if(stuckArrows != null && !stuckArrows.isEmpty())
			{
				Iterator list = stuckArrows.iterator();
				
				while(list.hasNext())
				{
					ItemStack arrow = (ItemStack) list.next();
					dropper.entityDropItem(arrow, 0.0F);
				}
			}
		}
	}
	
	@SubscribeEvent
    public void spawnEntity(EntityJoinWorldEvent event)
    {
		if(event.entity.isDead)
		{
			event.setCanceled(true);
			return;
		}
		if(event.entity instanceof EntityItem && !(event.entity instanceof EntityItemUnbreakable))
		{
			EntityItem eitem = (EntityItem)event.entity;
			if(eitem.getEntityItem() != null)
			{
				if(eitem.getEntityItem().hasTagCompound() && eitem.getEntityItem().getTagCompound().hasKey("Unbreakable"))
				{
					EntityItem newEntity = new EntityItemUnbreakable(event.world, eitem);
					event.world.spawnEntityInWorld(newEntity);
					eitem.setDead();
				}
				if(eitem.getEntityItem().getUnlocalizedName().contains("dragon"))
				{
					EntityItem newEntity = new EntityItemUnbreakable(event.world, eitem);
					event.world.spawnEntityInWorld(newEntity);
					eitem.setDead();
				}
			}
		}
    }
	public void alterDrops(EntityLivingBase dropper, LivingDropsEvent event)
	{
		ArrayList<ItemStack> meats = new ArrayList<ItemStack>();
		
		if(event.drops != null && !event.drops.isEmpty())
		{
			Iterator<EntityItem> list = event.drops.iterator();
			
			while(list.hasNext())
			{
				EntityItem entItem = list.next();
				ItemStack drop = entItem.getEntityItem();
				boolean dropItem = true;
				
				if(drop.getItem() instanceof ItemFood)
				{
					entItem.setDead();
					
					if(!meats.isEmpty())
					{
						for(int a = 0; a < meats.size(); a ++)
						{
							ItemStack compare = meats.get(a);
							if(drop.isItemEqual(compare))
							{
								dropItem = false;
							}
						}
					}
					if(dropItem)
					{
						drop.stackSize = 1;
						if(event.lootingLevel > 0)
						{
							drop.stackSize += dropper.getRNG().nextInt(event.lootingLevel+1);
						}
						meats.add(drop.copy());
					}
				}
			}
			
			for(int a = 0; a < meats.size(); a ++)
			{
				ItemStack meat = meats.get(a);
				dropper.entityDropItem(meat, 0.0F);
			}
		}
	}
	
	@SubscribeEvent
	public void killEntity(LivingDeathEvent event)
	{
		//killsCount
		EntityLivingBase dead = event.entityLiving;
		EntityLivingBase hunter = null;
		ItemStack weapon = null;
		DamageSource source = event.source;
		
		if(source != null && source.getEntity() != null)
		{
			if(source.getEntity() instanceof EntityLivingBase)
			{
				hunter = (EntityLivingBase)source.getEntity();
				weapon = hunter.getHeldItem();
				if(hunter instanceof EntityPlayer)
				{
					addKill((EntityPlayer)hunter, dead);
				}
			}
		}
		if(weapon != null && (weapon.getItem() instanceof IHuntingItem))
		{
			if(((IHuntingItem)weapon.getItem()).canRetrieveDrops(weapon))
			{
				dead.getEntityData().setBoolean("hunterKill", true);
			}
		}
	}

	private void addKill(EntityPlayer hunter, EntityLivingBase dead) 
	{
		addKillTo(hunter, "killsCount");
		if(dead instanceof IMob)
		{
			addKillTo(hunter, "killsCountMob");
		}
		else if(dead instanceof IAnimals)
		{
			addKillTo(hunter, "killsCountAnimal");
		}
		if(dead instanceof EntityPlayer)
		{
			addKillTo(hunter, "killsCountPlayer");
		}
	}
	
	@SubscribeEvent
	public void useHoe(UseHoeEvent event)
	{
		Block block = event.world.getBlock(event.x, event.y, event.z);
		if(block != Blocks.farmland && FarmingHelper.didHoeFail(event.current, event.world, block == Blocks.grass))
		{
			event.entityPlayer.swingItem();
			event.current.damageItem(1, event.entityLiving);
			event.world.playAuxSFXAtEntity(event.entityPlayer, 2001, event.x, event.y, event.z, Block.getIdFromBlock(block) + (event.world.getBlockMetadata(event.x, event.y, event.z) << 12));
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void breakBlock(BreakEvent event)
	{
		Block block = event.block;
		Block base = event.world.getBlock(event.x, event.y-1, event.z);
		int meta = event.blockMetadata;
		
		if(base != null && base == Blocks.farmland && FarmingHelper.didHarvestRuinBlock(event.world, false))
		{
			event.world.setBlock(event.x, event.y-1, event.z, Blocks.dirt);
		}
		if(event.getPlayer() != null)
		{
			playerMineBlock(event);
		}
	}
	public void playerMineBlock(BlockEvent.BreakEvent event)
	{
		if(event.getPlayer().capabilities.isCreativeMode)return;
		
		ItemStack held = event.getPlayer().getHeldItem();
		Block broken = event.block;
		
		if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(event.getPlayer()) && ConfigStamina.affectMining)
		{
			float hardness = Math.max(event.block.getBlockHardness(event.world, event.x, event.y, event.z), 0.1F);
			float points = hardness*0.5F*ConfigStamina.miningSpeed;
			ItemWeaponMF.applyFatigue(event.getPlayer(), points, hardness*25F);
			if(points > 0 && !StaminaBar.isAnyStamina(event.getPlayer(), false))
			{
				event.getPlayer().addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 100, 1));
			}
		}
	}

	private void addKillTo(EntityPlayer hunter, String type) 
	{
		int kills = hunter.getEntityData().hasKey(type) ? hunter.getEntityData().getInteger(type) : 0;
		kills ++;
		hunter.getEntityData().setInteger(type, kills);
	}
	
	@SubscribeEvent
	public void setTooltip(ItemTooltipEvent event)
	{
		if(event.itemStack != null)
		{
			if(event.itemStack.getItem() instanceof ItemArmor)
			{
				addArmourRating(event.itemStack, event.entityPlayer, event.toolTip, event.showAdvancedItemTooltips);
			}
			if(ArmourCalculator.advancedDamageTypes && ArmourCalculator.getRatioForWeapon(event.itemStack) != null)
			{
				displayTraits(ArmourCalculator.getRatioForWeapon(event.itemStack), event.toolTip);
			}
			if(ToolHelper.shouldShowTooltip(event.itemStack))
			{
				showCrafterTooltip(event.itemStack, event.toolTip);
			}
			if(event.itemStack.hasTagCompound() && event.itemStack.getTagCompound().hasKey("MF_CraftedByName"))
			{
				String name = event.itemStack.getTagCompound().getString("MF_CraftedByName");
				boolean special = name.equals("Galactic_Hiker") || name.equals("tim4200");//Mod creators have highlights
				
				event.toolTip.add((special ? EnumChatFormatting.GREEN : "") + StatCollector.translateToLocal("attribute.mfcraftedbyname.name") + ": " + name + EnumChatFormatting.GRAY);
			}
			WeaponClass WC = WeaponClass.findClassForAny(event.itemStack);
			if(WC != null && RPGElements.isSystemActive)
			{
				event.toolTip.add(StatCollector.translateToLocal("weaponclass."+WC.name.toLowerCase()));
				float skillMod = RPGElements.getWeaponModifier(event.entityPlayer, WC.parentSkill) * 100F;
				if(skillMod > 100)
				event.toolTip.add(StatCollector.translateToLocal("rpg.skillmod") + ItemWeaponMF.decimal_format.format(skillMod-100) + "%");
				
			}
		}
	}
	
	private void displayTraits(float[] ratio, List<String> list)
	{
		int cutting = (int) (ratio[0] / (ratio[0]+ratio[1]) * 100F);
		int blunt = 100 - cutting;
		
		if(cutting > 0)
		{
			String s = StatCollector.translateToLocal("attribute.weapon.cutting");
			if(cutting < 100)
			{
				s += " " + cutting + "%";
			}
			list.add(s);
		}
		if(blunt > 0)
		{
			String s = StatCollector.translateToLocal("attribute.weapon.blunt");
			if(blunt < 100)
			{
				s += " " + blunt + "%";
			}
			list.add(s);
		}
	}

	public static void addArmourRating(ItemStack armour, EntityPlayer user, List list, boolean extra) 
    {
		list.add("");
		String AC = ArmourCalculator.getArmourClass(armour);
		if(AC != null)
		{
			list.add(StatCollector.translateToLocal("attribute.armour." + AC));
		}
		
		int slot = ((ItemArmor)armour.getItem()).armorType;
        String attatch = "";
        
        float rating = ArmourCalculator.getArmourRatingLevel(user, armour, slot);
        float equipped = ArmourCalculator.getArmourRatingLevel(user, user.getCurrentArmor(3-slot), slot);
        boolean percent = ArmourCalculator.usePercentage;
        
        if(equipped > 0 && rating != equipped)
        {
        	float d = rating-equipped;
        	if(d > 0)
        	{
        		attatch += EnumChatFormatting.DARK_GREEN;
        	}
        	if(d < 0)
        	{
        		attatch += EnumChatFormatting.RED;
        	}
        	String d2 = percent ? ItemWeaponMF.decimal_format.format(d) : (""+(int)d);
        	attatch += " (" + (d > 0 ? "+" : "") + d2 + (percent ? "%)" : ")");
        }
        if(percent)
        {
        	list.add(EnumChatFormatting.BLUE+StatCollector.translateToLocal("attribute.armour.protection") + " " + ItemWeaponMF.decimal_format.format(rating)+"%" + attatch);
        }
        else
        {
        	list.add(EnumChatFormatting.BLUE+StatCollector.translateToLocal("attribute.armour.protection") + " " + (int)rating + attatch);
        }
    }
	private void showCrafterTooltip(ItemStack tool, List<String> list)
	{
		String toolType = ToolHelper.getCrafterTool(tool);
		int tier = ToolHelper.getCrafterTier(tool);
		float efficiency = ToolHelper.getCrafterEfficiency(tool);
		
		list.add(StatCollector.translateToLocal("attribute.mfcrafttool.name") + ": " + StatCollector.translateToLocal("tooltype." + toolType));
		list.add(StatCollector.translateToLocal("attribute.mfcrafttier.name") + ": " + tier);
		list.add(StatCollector.translateToLocal("attribute.mfcrafteff.name") + ": " + efficiency);
	}

	
	@SubscribeEvent
	public void loadChunk(ChunkEvent.Load event)
	{
	}
	
	
	
	
	private void dropItem(World world, ItemStack itemstack, double x, double y, double z)
	{
		if (itemstack != null)
        {
            float f = EventManagerMF.rand .nextFloat() * 0.8F + 0.1F;
            float f1 = EventManagerMF.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = EventManagerMF.rand.nextFloat() * 0.8F + 0.1F;

            while (itemstack.stackSize > 0)
            {
                int j1 = EventManagerMF.rand.nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float)EventManagerMF.rand.nextGaussian() * f3;
                entityitem.motionY = (float)EventManagerMF.rand.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float)EventManagerMF.rand.nextGaussian() * f3;
                world.spawnEntityInWorld(entityitem);
            }
        }
	}
	
	public static String getRegisterName(Entity entity)
    {
        String s = EntityList.getEntityString(entity);

        if (s == null)
        {
            s = "generic";
        }

        return s;
    }
	
	@SubscribeEvent
	public void updateEntity(LivingUpdateEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		float lowHp = entity.getMaxHealth()/5F;
		int injury = getInjuredTime(entity);
		
		if(ConfigHardcore.critLimp && !(entity instanceof EntityPlayer && ((EntityPlayer)entity).capabilities.isCreativeMode))
		{
			if(entity.getHealth() <= lowHp || injury > 0)
			{
				if(entity.getRNG().nextInt(10) == 0 && entity.onGround && !entity.isRiding())
				{
					entity.motionX = 0F;
					entity.motionZ = 0F;
				}
				if(entity.ticksExisted % 15 == 0)
				{
					entity.limbSwing = 2.0F;
					float x = (float) (entity.posX + (rand.nextFloat()-0.5F)/4F);
					float y = (float) (entity.posY + entity.getEyeHeight() + (rand.nextFloat()-0.5F)/4F);
					float z = (float) (entity.posZ + (rand.nextFloat()-0.5F)/4F);
					entity.worldObj.spawnParticle("reddust", x, y, z, 0F, 0F, 0F);
				}
			}
			if(!entity.worldObj.isRemote && entity.getHealth() <= (lowHp/2) && entity.getRNG().nextInt(200) == 0)
			{
				entity.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 50));
			}
		}
		if(injury > 0 && !entity.worldObj.isRemote)
		{
			injury --;
			entity.getEntityData().setInteger(injuredNBT, injury);
		}
		if(StaminaBar.isSystemActive && StaminaBar.doesAffectEntity(entity))
		{
			StaminaMechanics.tickEntity(event.entityLiving);
		}
		tickHitSpeeds(event.entityLiving);
		
		if(event.entity.ticksExisted == 1 && event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		{
		}
		
	}
	public static void tickHitSpeeds(EntityLivingBase user)
	{
		int time = getHitspeedTime(user);
		if(time > 0)
		{
			time --;
			user.getEntityData().setInteger(hitspeedNBT, time);
		}
		
	}
	public static final String hitspeedNBT = "MF_HitCooldown";
	public static void setHitTime(EntityLivingBase user, int time)
	{
		user.getEntityData().setInteger(hitspeedNBT, time);
	}
	public static int getHitspeedTime(Entity entity)
	{
		if(entity != null && entity.getEntityData().hasKey(hitspeedNBT))
		{
			return entity.getEntityData().getInteger(hitspeedNBT);
		}
		return 0;
	}
	public static final String injuredNBT = "MF_Injured";
	public static int getInjuredTime(Entity entity)
	{
		if(entity.getEntityData().hasKey(injuredNBT))
		{
			return entity.getEntityData().getInteger(injuredNBT);
		}
		return 0;
	}
	private boolean isEntityMoving(EntityLivingBase entity)
	{
		return Math.hypot(entity.motionX, entity.motionZ) > 0.05F;
	}
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event)
	{
		EntityPlayer origin = event.original;
		EntityPlayer spawn = event.entityPlayer;
		if(origin != null && spawn != null)
		{
			EntityHelper.cloneNBT(origin, spawn);
		}
	}
	
    @SubscribeEvent
    public void pickupXP(PlayerPickupXpEvent event)
    {
    	if(event.orb.worldObj.isRemote)return;
    	ResearchLogic.addKnowledgeExperience(event.entityPlayer, event.orb.xpValue);
    }
    
    @SubscribeEvent
    public void startUseItem(PlayerUseItemEvent.Start event)
    {
    	EntityPlayer player = event.entityPlayer;
    	if(event.item.getItemUseAction() == EnumAction.block)
    	{
    		if((StaminaBar.isSystemActive && TacticalManager.shouldStaminaBlock  && !StaminaBar.isAnyStamina(player, false)) || !CombatMechanics.isParryAvailable(player))
    		{
    			event.setCanceled(true);
    		}
    	}
    }

	private boolean canItemBlock(ItemStack item)
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	@SubscribeEvent
	public void levelup(LevelupEvent event)
	{
		EntityPlayer player = event.thePlayer;
		if(!player.worldObj.isRemote)
		{
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new LevelupPacket(player, event.theSkill, event.theLevel).generatePacket());
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new SkillPacket(player, event.theSkill).generatePacket());
		}
	}
	@SubscribeEvent
	public void syncSkill(SyncSkillEvent event)
	{
		EntityPlayer player = event.thePlayer;
		if(!player.worldObj.isRemote)
		{
			((WorldServer)player.worldObj).getEntityTracker().func_151248_b(player, new SkillPacket(player, event.theSkill).generatePacket());
		}
	}
}
