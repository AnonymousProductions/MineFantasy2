package minefantasy.mf2.block.tileentity;

import java.util.Random;

import minefantasy.mf2.api.crafting.bomb.IBombComponent;
import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.api.rpg.RPGElements;
import minefantasy.mf2.api.rpg.SkillList;
import minefantasy.mf2.item.gadget.ItemBomb;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBombBench extends TileEntity implements IInventory
{
	private ItemStack[] inv = new ItemStack[5];
	private Random rand = new Random();
	
	public boolean tryCraft(EntityPlayer user)
	{
		boolean sticky = ResearchLogic.hasInfoUnlocked(user, KnowledgeListMF.stickybomb) && user.getHeldItem() != null && user.getHeldItem().getItem() == Items.slime_ball;
		if(!worldObj.isRemote && sticky && applySlime())
		{
			user.inventory.consumeInventoryItem(Items.slime_ball);
			return true;
		}
		ItemStack result = findResult();
		
		if(result != null && craftItem(result))
		{
			if(RPGElements.isSystemActive)
			{
				SkillList.engineering.addXP(user, 20);
			}
			for(int a = 0; a < 4; a++)
			{
				decrStackSize(a, 1);
			}
			if(rand.nextInt(24) == 0 && user.swingProgress == 0.0F)
			{
				ResearchLogic.modifyKnowledgePoints(user, 1);
			}
			return true;
		}
		return false;
	}
	private boolean applySlime()
	{
		ItemStack res = getStackInSlot(4);
		
		if(res != null && res.getItem() instanceof ItemBomb)
		{
			if(res.hasTagCompound() && !res.getTagCompound().hasKey("stickyBomb"))
			{
				res.getTagCompound().setBoolean("stickyBomb", true);
				return true;
			}
		}
		return false;
	}
	private boolean craftItem(ItemStack result)
	{
		if(inv[4] == null)
		{
			this.setInventorySlotContents(4, result);
			return true;
		}
		else
		{
			if(inv[4].isItemEqual(result) && (inv[4].stackSize + result.stackSize) <= inv[4].getMaxStackSize())
			{
				inv[4].stackSize++;
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	public void onInventoryChanged()
	{
	}
	
	private ItemStack findResult()
	{
		if(!isMatch(1, "powder") || !isMatch(3, "fuse"))
		{
			return null;
		}
		byte caseTier = -1;
		byte filling = 0;
		byte fuse = -1;
		byte powder = -1;
		Item design = null;
		String com0 = getComponentType(inv[0]);
		String com1 = getComponentType(inv[1]);
		String com2 = getComponentType(inv[2]);
		String com3 = getComponentType(inv[3]);
		if(com0 != null)
		{
			caseTier = getComponentTier(inv[0]);
			String type = com0;
			design = type.equalsIgnoreCase("bombcase") ? ToolListMF.bomb_custom : type.equalsIgnoreCase("minecase") ? ToolListMF.mine_custom : null;
		}
		if(com1 != null)
		{
			if(com1.equalsIgnoreCase("powder"))
			{
				powder = getComponentTier(inv[1]);
			}
			else
			{
				return null;
			}
		}
		else return null;
		if(com2 != null)
		{
			if(com2.equalsIgnoreCase("filling"))
			{
				filling = getComponentTier(inv[2]);
			}
			else
			{
				return null;
			}
		}
		if(com3 != null)
		{
			if(com3.equalsIgnoreCase("fuse"))
			{
				fuse = getComponentTier(inv[3]);
			}
			else
			{
				return null;
			}
		}
		else return null;
		
		if(design != null && fuse > -1 && powder > -1)
		{
			return ItemBomb.createExplosive(design, caseTier, filling, fuse, powder, 1, false);
		}
		return null;
	}
	
	private boolean isMatch(int slot, String type)
	{
		return isMatch(inv[slot], type);
	}
	public static boolean isMatch(ItemStack item, String type)
	{
		String component = getComponentType(item);
		return component != null && component.equalsIgnoreCase(type);
	}
	public static  String getComponentType(ItemStack item)
	{
		if(item != null && item.getItem() != null && item.getItem() instanceof IBombComponent)
		{
			return ((IBombComponent)item.getItem()).getComponentType();
		}
		return null;
	}
	public static  byte getComponentTier(ItemStack item)
	{
		if(item != null && item.getItem() != null && item.getItem() instanceof IBombComponent)
		{
			return ((IBombComponent)item.getItem()).getTier();
		}
		return (byte)0;
	}
	
	
	//INVENORY
	
	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num)
    {
		onInventoryChanged();
        if (this.inv[slot] != null)
        {
            ItemStack itemstack;

            if (this.inv[slot].stackSize <= num)
            {
                itemstack = this.inv[slot];
                this.inv[slot] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inv[slot].splitStack(num);

                if (this.inv[slot].stackSize == 0)
                {
                    this.inv[slot] = null;
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
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item)
	{
		onInventoryChanged();
		inv[slot] = item;
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
		return true;
	}
	
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		NBTTagList savedItems = nbt.getTagList("Items", 10);
        this.inv = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < savedItems.tagCount(); ++i)
        {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < this.inv.length)
            {
                this.inv[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList savedItems = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
	}
}
