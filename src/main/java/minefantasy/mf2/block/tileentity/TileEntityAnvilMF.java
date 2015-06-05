package minefantasy.mf2.block.tileentity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.crafting.anvil.CraftingManagerAnvil;
import minefantasy.mf2.api.crafting.anvil.IAnvil;
import minefantasy.mf2.api.crafting.anvil.ShapelessAnvilRecipes;
import minefantasy.mf2.api.crafting.exotic.SpecialForging;
import minefantasy.mf2.api.heating.Heatable;
import minefantasy.mf2.api.heating.IHotItem;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.container.ContainerAnvilMF;
import minefantasy.mf2.item.armour.ItemArmourMF;
import minefantasy.mf2.item.heatable.ItemHeated;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.network.packet.AnvilPacket;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.WorldServer;

public class TileEntityAnvilMF extends TileEntity implements IInventory, IAnvil
{
	public int tier;
	private ItemStack[] inventory;
	private Random rand = new Random();
	private int	ticksExisted;
	private ContainerAnvilMF syncAnvil;
	private InventoryCrafting craftMatrix;
	private String lastPlayerHit = "";
	private String toolTypeRequired = "hammer";
	private String researchRequired = "";
	private boolean outputHot = false;
	
	public TileEntityAnvilMF()
	{
		this(0, "Iron");
	}
	public TileEntityAnvilMF(int tier, String name)
	{
		inventory = new ItemStack[25];
		this.tier=tier;
		texName=name;
		setContainer(new ContainerAnvilMF(this));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		tier = nbt.getInteger("tier");
		
		NBTTagList savedItems = nbt.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < savedItems.tagCount(); ++i)
        {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < this.inventory.length)
            {
                this.inventory[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
        progress = nbt.getFloat("Progress");
        progressMax = nbt.getFloat("ProgressMax");
        resName = nbt.getString("ResultName");
        toolTypeRequired = nbt.getString("toolTypeRequired");
        researchRequired = nbt.getString("researchRequired");
        texName = nbt.getString("TextureName");
        outputHot = nbt.getBoolean("outputHot");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("tier", tier);
		
		NBTTagList savedItems = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
     
        nbt.setFloat("Progress", progress);
        nbt.setFloat("ProgressMax", progressMax);
        nbt.setString("ResName", resName);
        nbt.setString("toolTypeRequired", toolTypeRequired);
        nbt.setString("researchRequired", researchRequired);
        nbt.setString("TextureName", texName);
        nbt.setBoolean("outputHot", outputHot);
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num)
    {
		onInventoryChanged();
        if (this.inventory[slot] != null)
        {
            ItemStack itemstack;

            if (this.inventory[slot].stackSize <= num)
            {
                itemstack = this.inventory[slot];
                this.inventory[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[slot].splitStack(num);

                if (this.inventory[slot].stackSize == 0)
                {
                    this.inventory[slot] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return inventory[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		onInventoryChanged();
		inventory[slot] = item;
	}

	@Override
	public String getInventoryName()
	{
		return "gui.carpentermf.name";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer user)
	{
		return user.getDistance(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D) < 8D;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item)
	{
		return true;
	}
	@Override
	public void updateEntity()
	{
		++ticksExisted;
		super.updateEntity();
		if(!worldObj.isRemote)
		{
			if(ticksExisted % 20 == 0)
			{
				updateInv();
			}
			if(!canCraft() && ticksExisted > 1)
			{
				progress = progressMax = 0;
				this.resName = "";
				this.recipe = null;
			}
		}
	}
	
	public void onInventoryChanged()
	{
		updateInv();
	}
	
	public boolean tryCraft(EntityPlayer user)
	{
		if(user == null)return false;
		
		String toolType = ToolHelper.getCrafterTool(user.getHeldItem());
		int hammerTier = ToolHelper.getCrafterTier(user.getHeldItem());
		if(toolType.equalsIgnoreCase("hammer") || toolType.equalsIgnoreCase("hvyHammer"))
		{
			if(user.getHeldItem() != null)
			{
				user.getHeldItem().damageItem(1, user);
				if(user.getHeldItem().getItemDamage() >= user.getHeldItem().getMaxDamage())
				{
					user.destroyCurrentEquippedItem();
				}
			}
			if(worldObj.isRemote)
				return true;
			
			if(doesPlayerKnowCraft(user) && canCraft() && toolType.equalsIgnoreCase(toolTypeRequired) && tier >= anvilTierRequired && hammerTier >= hammerTierRequired)
			{
				worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "minefantasy2:block.anvilsucceed", 0.25F, 1.0F);
				float efficiency = ToolHelper.getCrafterEfficiency(user.getHeldItem());
				float toolEfficiency = efficiency;
				
				if(user.swingProgress > 0 && user.swingProgress <= 1.0)
				{
					efficiency *= (0.5F-user.swingProgress);
				}
				if(RPGElements.isSystemActive)
				{
					SkillList.metallurgy.addXP(user, (int)efficiency);
				}
				
				progress += Math.max(0.2F, efficiency);
				if(progress >= progressMax)
				{
					float xpGained = progressMax / toolEfficiency;
					MFLogUtil.logDebug("Completed Craft: KPE Gained: " + (int)xpGained);
					ResearchLogic.addKnowledgeExperience(user, xpGained);
					craftItem();
				}
			}
			else
			{
				worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "minefantasy2:block.anvilfail", 0.25F, 1.0F);
			}
			lastPlayerHit = user.getCommandSenderName();
			updateInv();
			
			return true;
		}
		updateInv();
		return false;
	}
	
	public boolean doesPlayerKnowCraft(EntityPlayer user)
	{
		return getResearchNeeded().isEmpty() || ResearchLogic.hasInfoUnlocked(user, getResearchNeeded());
	}
	private void craftItem()
	{
		if (this.canCraft())
        {
			ItemStack result = modifySpecials(recipe);
			if(result != null && result.getItem() instanceof ItemArmourMF)
			{
				result = modifyArmour(result);
			}
			int output = getSizeInventory()-1;
			
			int temp = this.averageTemp();
			boolean hot = outputHot && temp > 0;
			
			if(hot && this.inventory[output] == null)
			{
				MFLogUtil.logDebug("Hot Output");
				ItemStack out = ItemHeated.createHotItem(result, temp);
            	if(result.getMaxStackSize() == 1 && lastPlayerHit.length() > 0)
            	{
            		getNBT(out).setString("MF_CraftedByName", lastPlayerHit);
            	}
                this.inventory[output] = out;
				EntityPlayer lastHit = worldObj.getPlayerEntityByName(lastPlayerHit);
	            consumeResources();
			}
			else if (!hot)
			{
	            if (this.inventory[output] == null)
	            {
	            	if(result.getMaxStackSize() == 1 && lastPlayerHit.length() > 0)
	            	{
	            		getNBT(result).setString("MF_CraftedByName", lastPlayerHit);
	            	}
	                this.inventory[output] = result;
	            }
	            else if (this.inventory[output].getItem() == result.getItem())
	            {
	                this.inventory[output].stackSize += result.stackSize; // Forge BugFix: Results may have multiple items
	            }
	            EntityPlayer lastHit = worldObj.getPlayerEntityByName(lastPlayerHit);
	            consumeResources();
			}
        }
		onInventoryChanged();
		progress = 0;
	}
	private ItemStack modifyArmour(ItemStack result)
	{
		ItemArmourMF item = (ItemArmourMF)result.getItem();
		boolean canColour = item.canColour();
		int colour = item.defaultColour;
		for(int a = 0; a < getSizeInventory()-1; a++)
		{
			ItemStack slot = getStackInSlot(a);
			if(slot != null && slot.getItem() instanceof ItemArmor)
			{
				ItemArmor slotitem = (ItemArmor)slot.getItem();
				if(canColour && slotitem.hasColor(slot))
				{
					colour = slotitem.getColor(slot);
				}
				if(result.isItemStackDamageable())
				{
					result.setItemDamage(slot.getItemDamage());
				}
			}
		}
		if(canColour)
		{
			item.func_82813_b(result, colour);
		}
		return result;
	}
	private ItemStack modifySpecials(ItemStack result) 
	{
		boolean hasHeart = false;
		EntityPlayer player = worldObj.getPlayerEntityByName(lastPlayerHit);
		//DRAGONFORGE
		for(int x = -4; x <= 4; x++)
		{
			for(int y = -4; y <= 4; y++)
			{
				for(int z = -4; z <= 4; z++)
				{
					TileEntity tile = worldObj.getTileEntity(xCoord+x, yCoord+y, zCoord+z);
					if(player != null && ResearchLogic.hasInfoUnlocked(player, KnowledgeListMF.smeltDragonforge) && tile != null && tile instanceof TileEntityForge)
					{
						if(((TileEntityForge)tile).dragonHeartPower > 0)
						{
							hasHeart = true;
							((TileEntityForge)tile).dragonHeartPower = 0;
							worldObj.createExplosion(null, xCoord+x, yCoord+y, zCoord+z, 1F, false);
							break;
						}
					}
				}	
			}	
		}
		if(hasHeart)
		{
			Item DF = SpecialForging.getDragonCraft(result);
			if(DF != null)
			{
				return new ItemStack(DF, result.stackSize, result.getItemDamage());
			}
		}
		return result;
	}
	private int averageTemp() 
	{
		float totalTemp = 0;
		int itemCount = 0;
		for(int a = 0; a < getSizeInventory()-1; a++)
		{
			ItemStack item = getStackInSlot(a);
			if(item != null)
			{
				++itemCount;
				totalTemp += (float)Heatable.getTemp(item);
			}
		}
		if(totalTemp > 0 && itemCount > 0)
		{
			return (int)(totalTemp / itemCount);
		}
		return 0;
	}
	private NBTTagCompound getNBT(ItemStack item)
	{
		if(!item.hasTagCompound())
		{
			item.setTagCompound(new NBTTagCompound());
		}
		return item.getTagCompound();
	}
	private void dropItem(ItemStack itemstack)
	{
		if (itemstack != null)
        {
            float f = this.rand .nextFloat() * 0.8F + 0.1F;
            float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

            while (itemstack.stackSize > 0)
            {
                int j1 = this.rand.nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                EntityItem entityitem = new EntityItem(worldObj, xCoord + f, yCoord + f1, zCoord + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }

                float f3 = 0.05F;
                entityitem.motionX = (float)this.rand.nextGaussian() * f3;
                entityitem.motionY = (float)this.rand.nextGaussian() * f3 + 0.2F;
                entityitem.motionZ = (float)this.rand.nextGaussian() * f3;
                worldObj.spawnEntityInWorld(entityitem);
            }
        }
    }
	
	public boolean hasItems(EntityPlayer user, ItemStack[] items)
	{
		for(ItemStack check: items)
		{
			if(!hasItems(user, check))
			{
				return false;
			}
		}
		return true;
	}
	public boolean hasItems(EntityPlayer user, ItemStack item)
	{
		return hasItems(user, item, item.stackSize);
	}
	public boolean hasItems(EntityPlayer user, ItemStack item, int number)
	{
		int count = 0;
		for(int a = 0; a < user.inventory.getSizeInventory(); a++)
		{
			ItemStack slot = user.inventory.getStackInSlot(a);
			if(slot != null && slot.isItemEqual(item))
			{
				count += slot.stackSize;
			}
		}
		return count >= number;
	}
	public void syncData()
	{
		
		if(worldObj.isRemote)return;
		
		List<EntityPlayer> players = ((WorldServer)worldObj).playerEntities;
		for(int i = 0; i < players.size(); i++)
		{
			EntityPlayer player = players.get(i);
			((WorldServer)worldObj).getEntityTracker().func_151248_b(player, new AnvilPacket(this).generatePacket());
		}
	}
	public String resName = "<No Project Set>";
	public String getResultName()
	{
		if(!worldObj.isRemote && recipe != null && recipe.getDisplayName() != null)
		{
			resName = recipe.getDisplayName();
		}
		return resName;
	}
	public String getResearchNeeded()
	{
		return researchRequired;
	}
	public String getToolNeeded()
	{
		return toolTypeRequired;
	}
	public int getToolTierNeeded()
	{
		return this.hammerTierRequired;
	}
	public int getAnvilTierNeeded()
	{
		return this.anvilTierRequired;
	}
	public void consumeResources()
	{
		for(int slot = 0; slot < getSizeInventory()-1; slot++)
		{
			ItemStack item = getStackInSlot(slot);
			if(item != null && item.getItem() != null && item.getItem().getContainerItem(item) != null)
			{
				this.dropItem(item.getItem().getContainerItem(item));
			}
			this.decrStackSize(slot, 1);
		}
	}
	private boolean canFitResult(ItemStack result)
	{
		ItemStack resSlot = inventory[getSizeInventory()-1];
		if(resSlot != null && result != null)
		{
			if(outputHot)
			{
				return false;
			}
			if(!resSlot.isItemEqual(result))
			{
				return false;
			}
			if(resSlot.stackSize + result.stackSize > resSlot.getMaxStackSize())
			{
				return false;
			}
		}
		return true;
	}
	//CRAFTING CODE
	public ItemStack getResult()
    {
    	if(syncAnvil == null || craftMatrix == null)
    	{
    		return null;
    	}
    	
    	if(ticksExisted <= 1)
    		return null;
    	
    	for(int a = 0 ; a < getSizeInventory()-1 ; a ++)
    	{
    		craftMatrix.setInventorySlotContents(a, inventory[a]);
    	}
    	
    	return CraftingManagerAnvil.getInstance().findMatchingRecipe(this, craftMatrix);
    }
	public void updateInv()
    {
    	if(!worldObj.isRemote)
    	{
    		ItemStack oldRecipe = recipe;
    		recipe = getResult();
    		//syncItems();
    		
    		if (!canCraft() && progress > 0) 
            {
    			progress = 0;
                //quality = 100;
            }
    		if(recipe != null && oldRecipe != null && !recipe.isItemEqual(oldRecipe))
    		{
    			progress = 0;
    		}
    		if(progress > progressMax)progress = progressMax-1;
    		syncData();
    	}
    }
	public boolean canCraft() 
	{
		if(worldObj.isRemote)
		{
			//return canCraft == 1;
		}
		
		if(this.isMythicRecipe() && !this.isMythicReady())
		{
			return false;
		}
		
        if(progressMax > 0 && recipe != null && recipe instanceof ItemStack)
        {
        	return this.canFitResult(recipe);
        }
        return false;
    }
	
	private ItemStack recipe;
	public float progressMax;
	public float progress;
	private int hammerTierRequired;
	private int anvilTierRequired;
	
	@Override
	public void setForgeTime(int i)
	{
		progressMax = i;
	}
	@Override
	public void setHammerUsed(int i)
	{
		hammerTierRequired = i;
	}
	@Override
	public void setRequiredAnvil(int i)
	{
		anvilTierRequired = i;
	}
	@Override
	public void setHotOutput(boolean i)
	{
		outputHot = i;
	}
	public void setContainer(ContainerAnvilMF container)
	{
		syncAnvil = container;
		craftMatrix = new InventoryCrafting(syncAnvil, ShapelessAnvilRecipes.globalWidth, ShapelessAnvilRecipes.globalHeight);
	}
	public boolean shouldRenderCraftMetre()
	{
		return recipe != null;
	}
	public int getProgressBar(int i)
	{
		return (int)Math.ceil(i / progressMax * progress);
	}
	@Override
	public void setToolType(String toolType)
	{
		this.toolTypeRequired = toolType;
	}
	@Override
	public void setResearch(String research)
	{
		this.researchRequired = research;
	}
	
	private boolean isMythicRecipe()
	{
		return this.hammerTierRequired >= 6;//Ignotumite And Mithium
	}
	private boolean isMythicReady()
	{
		TileEntity tile = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
		if(tile != null && tile instanceof TileEntityBeacon)
		{
			TileEntityBeacon beacon = (TileEntityBeacon)tile;
			if(beacon.getLevels() > 0)
			{
				return true;
			}
		}
		return false;
	}
	public String texName = "";
	public String getTextureName()
	{
		return texName;
	}
}
