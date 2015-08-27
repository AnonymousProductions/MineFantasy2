package minefantasy.mf2.api.heating;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TongsHelper 
{
	/**
	 * Determines if an item is held
	 */
	public static boolean hasHeldItem(ItemStack tongs)
	{
		NBTTagCompound nbt = getNBT(tongs);

		return nbt.hasKey("Held") && nbt.getBoolean("Held");
	}

	/**
	 * Empties the item
	 * 
	 * @return
	 */
	public static ItemStack clearHeldItem(ItemStack tongs, EntityLivingBase user) 
	{
		if (!user.worldObj.isRemote) 
		{
			NBTTagCompound nbt = getNBT(tongs);
			nbt.setBoolean("Held", false);
		}
		tongs.damageItem(1, user);

		return tongs;
	}

	/**
	 * Picks up an item
	 */
	public static boolean trySetHeldItem(ItemStack tongs, ItemStack item)
	{
		if (item == null || item.getItem() == null || !isHotItem(item) || item.getItem() instanceof ItemBlock) 
		{
			return false;
		}
		NBTTagCompound nbt = getNBT(tongs);
		nbt.setBoolean("Held", true);
		item.writeToNBT(nbt);

		return true;
	}

	/**
	 * Used to determine if an item burns you when held, and if tongs can pick
	 * it up
	 */
	public static boolean isHotItem(ItemStack item)
	{
		if (item.getItem() instanceof IHotItem)
		{
			return ((IHotItem) item.getItem()).isHot(item);
		}
		return false;
	}

	/**
	 * Determines if it can be cooled in a water source
	 */
	public static boolean isCoolableItem(ItemStack item) 
	{
		if (item.getItem() instanceof IHotItem)
		{
			return ((IHotItem) item.getItem()).isCoolable(item);
		}
		return false;
	}

	/**
	 * Gets the item picked up
	 */
	public static ItemStack getHeldItem(ItemStack tongs) {
		NBTTagCompound nbt = getNBT(tongs);
		if (nbt.hasKey("Held")) {
			if (nbt.getBoolean("Held")) {
				return ItemStack.loadItemStackFromNBT(nbt);
			}
		}
		return null;
	}

	/**
	 * Gets the item held from tongs
	 * 
	 * @param tongs
	 *            the itemstack used
	 */
	public static ItemStack getHeldItemTongs(ItemStack tongs) {
		NBTTagCompound nbt = getNBT(tongs);
		if (nbt.hasKey("Held")) {
			if (nbt.getBoolean("Held")) {
				return ItemStack.loadItemStackFromNBT(nbt);
			}
		}
		return null;
	}

	/**
	 * Gets a hot item
	 */
	public static ItemStack getHotItem(ItemStack item)
	{
		NBTTagCompound tag = getNBT(item);
		if (tag.hasKey(Heatable.NBT_ItemID) && tag.hasKey(Heatable.NBT_SubID)) 
		{
			Item metal = Item.getItemById(tag.getInteger(Heatable.NBT_ItemID));
			return new ItemStack(metal, 1, tag.getInteger(Heatable.NBT_SubID));
		}

		return null;
	}

	/**
	 * Used for getting the NBT for itemstacks, if none exists; it creates one
	 */
	public static NBTTagCompound getNBT(ItemStack item) {
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
		}
		return item.getTagCompound();
	}
	
	public static boolean isWaterSource(World world, int i, int j, int k) 
	{
		if(TongsHelper.isQuenced(world, i, j, k))
		{
			return true;
		}
		if (world.getBlock(i, j, k).getMaterial() == Material.water)
		{
			world.setBlockToAir(i, j, k);
			return true;
		}
		if (isCauldron(world, i, j, k)) {
			return true;
		}
		return false;
	}
	public static boolean isCauldron(World world, int x, int y, int z) {
		return world.getBlock(x, y, z) == Blocks.cauldron && world.getBlockMetadata(x, y, z) > 0;
	}
	public static boolean isQuenced(World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile != null && tile instanceof IQuenchBlock)
		{
			return ((IQuenchBlock)tile).quench();
		}
		return false;
	}
}
