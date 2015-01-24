package minefantasy.mf2.block.basic;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockMythicOre extends BlockOreMF
{
	public BlockMythicOre(String name)
	{
		super(name, 4, 2);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
		if (rand.nextInt(20) == 0 && world.isRemote)
        {
			world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "minefantasy2:block.mythicore", 2.5F, rand.nextFloat() * 0.4F + 0.5F, true);
        }
    }
}
