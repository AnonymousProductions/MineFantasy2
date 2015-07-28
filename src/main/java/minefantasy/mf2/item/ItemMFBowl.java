package minefantasy.mf2.item;

import java.util.List;
import java.util.Random;

import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMFBowl extends ItemComponentMF 
{
	public ItemMFBowl(String name)
	{
		super(name, 0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);

        if (movingobjectposition == null)
        {
            return item;
        }
        else
        {
        	if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int i = movingobjectposition.getBlockPos().getX();
                int j = movingobjectposition.getBlockPos().getY();
                int k = movingobjectposition.getBlockPos().getZ();
                
                BlockPos ijk = new BlockPos(i,j,k);
                
                if (!world.canMineBlockBody(player, ijk))
                {
                    return item;
                }

                if (!player.canPlayerEdit(ijk, movingobjectposition.sideHit, item))
                {
                    return item;
                }

                if (isWaterSource(world, ijk))
                {
                	gather(item, world, player);
                }
            }

            return item;
        }
    }
    
	private  Random rand = new Random();
    private void gather(ItemStack item, World world, EntityPlayer player)
    {
        player.swingItem();
        if (!world.isRemote) 
        {
            world.playSoundAtEntity(player, "random.splash", 0.125F + rand.nextFloat()/4F, 0.5F + rand.nextFloat());
            item.stackSize--;
            EntityItem resultItem = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(FoodListMF.bowl_water_salt));
            world.spawnEntityInWorld(resultItem);
        }
	}
    
    private boolean isWaterSource(World world, BlockPos pos)
    {
    	BiomeGenBase biome = world.getBiomeGenForCoords(pos);
    	if(biome == BiomeGenBase.ocean || biome == BiomeGenBase.deepOcean || biome == BiomeGenBase.beach)
    	{
    		return true;
    	}
    	MFLogUtil.logDebug("Biome = " + biome.biomeName);
		if(world.getBlockState(pos).getBlock().getMaterial() == Material.water && world.getBlockState(pos.subtract(new Vec3i(0,1,0))) == Blocks.sand)
		{
			return true;
		}
		return false;
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item parItem, CreativeTabs parTab, 
          List parListSubItems)
    {
        parListSubItems.add(new ItemStack(this, 1));
     }
}
