package minefantasy.mf2.block.tileentity;

import java.util.List;

import minefantasy.mf2.api.heating.IQuenchBlock;
import minefantasy.mf2.block.decor.BlockTrough;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.network.packet.TroughPacket;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
public class TileEntityTrough extends TileEntity implements IQuenchBlock
{
	public int fill;
	private String tex;
	
	public TileEntityTrough()
	{
		this("Basic");
	}
	public TileEntityTrough(String tex)
	{
		this.tex = tex;
	}
	@Override
	public boolean quench() 
	{
		if(fill > 0)
		{
			--fill;
			return true;
		}
		return false;
	}
	
	public boolean interact(EntityPlayer user, ItemStack held) 
	{
		if(fill >= getCapacity())
		{
			return false;
		}
		if(held != null)
		{
			if(held.getItem() == Items.water_bucket)
			{
				user.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
				addCapacity(12);
				return true;
			}
		}
		return false;
	}
	private int ticksExisted;
	
	@Override
	public void updateEntity()
	{
		++ticksExisted;
		if(ticksExisted == 20 || ticksExisted % 100 == 0)
		{
			syncData();
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("fill", fill);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		fill = nbt.getInteger("fill");
	}
	private void addCapacity(int i) 
	{
		int cap = getCapacity();
		fill = Math.min(cap, fill+i);
		syncData();
	}
	public int getCapacity()
	{
		if(worldObj != null)
		{
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(block instanceof BlockTrough)
			{
				return ((BlockTrough)block).capacity;
			}
		}
		return 16;
	}
	public String getName()
	{
		if(worldObj != null)
		{
			Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(block instanceof BlockTrough)
			{
				return ((BlockTrough)block).name;
			}
		}
		return tex;
	}
	
	public void syncData()
	{
		if(worldObj.isRemote)return;
		
		List<EntityPlayer> players = ((WorldServer)worldObj).playerEntities;
		for(int i = 0; i < players.size(); i++)
		{
			EntityPlayer player = players.get(i);
			((WorldServer)worldObj).getEntityTracker().func_151248_b(player, new TroughPacket(this).generatePacket());
		}
	}
	
}
