package minefantasy.mf2.block.tileentity;

import java.util.Random;

import minefantasy.mf2.api.crafting.tanning.TanningRecipe;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.api.refine.BlastFurnaceRecipes;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.block.crafting.BlockTanningRack;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.container.ContainerTanner;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTanningRack extends TileEntity implements IInventory
{
	public ItemStack[] items = new ItemStack[2];
	public float progress;
	public float maxProgress;
	public String tex = "";
	public int tier = 0;
	public String toolType = "knife";
	public final ContainerTanner container;
	private int tempTicksExisted = 0;
	private Random rand = new Random();
	
	public TileEntityTanningRack()
	{
		this(0, "Basic");
	}
	public TileEntityTanningRack(int tier, String tex)
	{
		container = new ContainerTanner(this);
		this.tier=tier;
		this.tex=tex;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		++tempTicksExisted;
		if(tempTicksExisted == 10)
		{
			blockMetadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		}
	}
	public boolean interact(EntityPlayer player, boolean leftClick)
	{
		container.detectAndSendChanges();
		
		ItemStack held = player.getHeldItem();
		
		//Interaction
		if(items[1] != null && ToolHelper.getCrafterTool(held).equalsIgnoreCase(toolType))
		{
			if(ToolHelper.getCrafterTier(held) >= tier)
			{
				held.damageItem(1, player);
				if(held.getItemDamage() <= 0)
				{
					player.destroyCurrentEquippedItem();
				}
				
				float efficiency = ToolHelper.getCrafterEfficiency(held);
				if(player.swingProgress > 0 && player.swingProgress <= 1.0)
				{
					efficiency *= (0.5F-player.swingProgress);
				}
				
				if(efficiency > 0)
				progress += efficiency;
				worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "dig.cloth", 1.0F, 1.0F);
				if(progress >= maxProgress)
				{
					if(RPGElements.isSystemActive)
					{
						SkillList.leatherworking.addXP(player, 5);
					}
					progress = 0;
					setInventorySlotContents(0, items[1].copy());
					updateRecipe();
					if(isShabbyRack() && rand.nextInt(10) == 0 && !worldObj.isRemote)
					{
						for(int a = 0; a < rand.nextInt(10); a++)
						{
							ItemStack plank = new ItemStack(ComponentListMF.plank);
							worldObj.playSoundEffect(xCoord, yCoord, zCoord, "mob.zombie.woodbreak", 1.0F, 1.5F);
							dropItem(plank);
						}
						worldObj.setBlockToAir(xCoord, yCoord, zCoord);
						return true;
					}
				}
			}
			return true;
		}
		
		if(!leftClick && (ToolHelper.getCrafterTool(held).equalsIgnoreCase("nothing") || ToolHelper.getCrafterTool(held).equalsIgnoreCase("hands")))
		{
			//Item placement
			ItemStack item = items[0];
			if(item == null)
			{
				if(held != null && !(held.getItem() instanceof ItemBlock))
				{
					ItemStack item2 = held.copy();
					item2.stackSize = 1;
					setInventorySlotContents(0, item2);
					tryDecrMainItem(player);
					updateRecipe();
					worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "mob.horse.leather", 1.0F, 1.0F);
					return true;
				}
			}
			else
			{
				if(!player.inventory.addItemStackToInventory(item))
				{
					player.entityDropItem(item, 0.0F);
				}
				setInventorySlotContents(0, null);
				updateRecipe();
				worldObj.playSoundEffect(xCoord+0.5D, yCoord+0.5D, zCoord+0.5D, "mob.horse.leather", 1.0F, 1.0F);
				return true;
			}
		}
		return false;
	}

	private void tryDecrMainItem(EntityPlayer player) 
	{
		int held = player.inventory.currentItem;
		if(held >= 0 && held < 9)
		{
			player.inventory.decrStackSize(held, 1);
		}
	}
	public void updateRecipe()
	{
		TanningRecipe recipe = TanningRecipe.getRecipe(items[0]);
		if(recipe == null)
		{
			setInventorySlotContents(1, null);
			progress = maxProgress = tier = 0;
		}
		else
		{
			setInventorySlotContents(1, recipe.output);
			tier = recipe.tier;
			maxProgress = recipe.time;
			toolType = recipe.toolType;
		}
		progress = 0;
	}
	public boolean doesPlayerKnowCraft(EntityPlayer thePlayer)
	{
		return true;
	}
	public int getProgressBar(int i) 
	{
		if(maxProgress <= 0)return 0;
		return (int)((float)i / maxProgress * progress);
	}
	public String getResultName() 
	{
		if(items[1] != null)
		{
			return items[1].getDisplayName();
		}
		return "";
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		tex = nbt.getString("tex");
		tier = nbt.getInteger("tier");
		progress = nbt.getFloat("Progress");
        maxProgress = nbt.getFloat("maxProgress");
        toolType = nbt.getString("toolType");
		
		NBTTagList savedItems = nbt.getTagList("Items", 10);

        for (int i = 0; i < savedItems.tagCount(); ++i)
        {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < items.length)
            {
            	items[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("tex", tex);
		nbt.setInteger("tier", tier);
		nbt.setFloat("Progress", progress);
        nbt.setFloat("maxProgress", maxProgress);
        nbt.setString("toolType", toolType);
        
		NBTTagList savedItems = new NBTTagList();
		
        for (int i = 0; i < items.length; ++i)
        {
            if (items[i] != null)
            {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte)i);
                items[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
	}
	
	//INVENTORY
	public void onInventoryChanged() 
	{
	}
	
	@Override
	public int getSizeInventory()
	{
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return items[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num)
    {
		onInventoryChanged();
        if (this.items[slot] != null)
        {
            ItemStack itemstack;

            if (this.items[slot].stackSize <= num)
            {
                itemstack = this.items[slot];
                this.items[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.items[slot].splitStack(num);

                if (this.items[slot].stackSize == 0)
                {
                    this.items[slot] = null;
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
		return items[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		onInventoryChanged();
		items[slot] = item;
	}

	@Override
	public String getInventoryName()
	{
		return "tile.tanner.name";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
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
		return false;
	}
	
	private boolean isShabbyRack()
	{
		return worldObj.getBlock(xCoord, yCoord, zCoord) == BlockListMF.tanner;
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
}
