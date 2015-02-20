package minefantasy.mf2.block.tileentity.blastfurnace;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlastFC extends TileEntity implements IInventory, ISidedInventory
{
	protected ItemStack[] items = new ItemStack[2];
	private int smokeStorage;
	public int ticksExisted;
	public boolean isBuilt = false;
	public int fireTime;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		++ticksExisted;
		int dropFrequency = 5;
		
		if(!worldObj.isRemote && ticksExisted % dropFrequency == 0)
		{
			TileEntity neighbour = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
			if(neighbour != null && neighbour instanceof TileEntityBlastFC && !(neighbour instanceof TileEntityBlastFH))
			{
				interact((TileEntityBlastFC)neighbour);
			}
		}
		if(ticksExisted % 200 == 0)
		{
			updateBuild();
		}
	}
	protected void interact(TileEntityBlastFC tile)
	{
		if(!tile.isBuilt)return;
		
		for(int a = 0; a < getSizeInventory(); a++)
		{
			ItemStack mySlot = getStackInSlot(a);
			
			if(mySlot != null && canShare(mySlot, a))
			{
				ItemStack theirSlot = tile.getStackInSlot(a);
				if(theirSlot == null)
				{
					ItemStack copy = mySlot.copy();
					copy.stackSize = 1;
					tile.setInventorySlotContents(a, copy);
					this.decrStackSize(a, 1);
				}
				else if(theirSlot.isItemEqual(mySlot))
				{
					if((theirSlot.stackSize) < getMaxStackSizeForDistribute())
					{
						theirSlot.stackSize++;
						this.decrStackSize(a, 1);
						tile.setInventorySlotContents(a, theirSlot);
					}
				}
			}
		}
	}
	
	private boolean canShare(ItemStack mySlot, int a) 
	{
		if(a == 1)return isInput(mySlot);
		return isCoal(mySlot);
	}
	public void updateBuild()
	{
		isBuilt = getIsBuilt();
	}
	protected boolean getIsBuilt() 
	{
		return (isFirebrick(-1, 0, 0) && isFirebrick(1, 0, 0) && isFirebrick(0, 0, -1) && isFirebrick(0, 0, 1));
	}
	protected boolean isFirebrick(int x, int y, int z)
	{
		Block block = worldObj.getBlock(xCoord+x, yCoord+y, zCoord+z);
		if(block != null)
		{
			return block == BlockListMF.firebricks;
		}
		return false;
	}
	protected boolean isAir(int x, int y, int z)
	{
		return worldObj.isAirBlock(xCoord+x, yCoord+y, zCoord+z);
	}
	private int getMaxStackSizeForDistribute()
	{
		return 1;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("fireTime", fireTime);
		nbt.setBoolean("isBuilt", isBuilt);
		nbt.setInteger("ticksExisted", ticksExisted);
		nbt.setInteger("StoredSmoke", smokeStorage);
		NBTTagList savedItems = new NBTTagList();

        for (int i = 0; i < this.items.length; ++i)
        {
            if (this.items[i] != null)
            {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte)i);
                this.items[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		fireTime = nbt.getInteger("fireTime");
		isBuilt = nbt.getBoolean("isBuilt");
		ticksExisted = nbt.getInteger("ticksExisted");
		smokeStorage = nbt.getInteger("StoredSmoke");
		NBTTagList savedItems = nbt.getTagList("Items", 10);
        this.items = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < savedItems.tagCount(); ++i)
        {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < this.items.length)
            {
                this.items[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
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
		return "gui.bombcraftmf.name";
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
		if(slot == 0)
		{
			return isCoal(item);
		}
		return isInput(item);
	}
	public static boolean isCoal(ItemStack item)
	{
		return true;
	}
	public static boolean isFlux(ItemStack item)
	{
		return true;
	}
	public static boolean isInput(ItemStack item)
	{
		return getResult(item) != null;
	}
	protected static ItemStack getResult(ItemStack input)
	{
		if(FurnaceRecipes.smelting().getSmeltingResult(input) != null && FurnaceRecipes.smelting().getSmeltingResult(input).getItem() == Items.iron_ingot)
		{
			return new ItemStack(ComponentListMF.ingots[3]);
		}
		return null;
	}
	@Override
	public int[] getAccessibleSlotsFromSide(int side) 
	{
		return side == 1 ? new int[]{0, 1} : new int[]{};
	}
	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side)
	{
		return isItemValidForSlot(slot, item);
	}
	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side)
	{
		return false;
	}
}
