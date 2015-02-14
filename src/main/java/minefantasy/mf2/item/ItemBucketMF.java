package minefantasy.mf2.item;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBucketMF extends Item
{
    /** field for checking if the bucket has been filled. */
    private Block isFull;
    private static final String __OBFID = "CL_00000000";

    public ItemBucketMF(String name, Block filler)
    {
        this.maxStackSize = 1;
        this.isFull = filler;
        this.setCreativeTab(CreativeTabMF.tabGadget);
        
        setTextureName("minefantasy2:Tool/"+name);
		GameRegistry.registerItem(this, name, MineFantasyII.MODID);
		this.setUnlocalizedName(name);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack bucket, World world, EntityPlayer user)
    {
        boolean flag = this.isFull == Blocks.air;
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, user, flag);

        if (movingobjectposition == null)
        {
            return bucket;
        }
        else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                int x = movingobjectposition.blockX;
                int y = movingobjectposition.blockY;
                int z = movingobjectposition.blockZ;

                if (!world.canMineBlock(user, x, y, z))
                {
                    return bucket;
                }

                if (flag)
                {
                    if (!user.canPlayerEdit(x, y, z, movingobjectposition.sideHit, bucket))
                    {
                        return bucket;
                    }

                    Material material = world.getBlock(x, y, z).getMaterial();
                    int l = world.getBlockMetadata(x, y, z);

                    if (material == Material.water && l == 0)
                    {
                        world.setBlockToAir(x, y, z);
                        return this.createNewInstance(bucket, user, ToolListMF.bucketwood_water, 0);
                    }

                    if (material == Material.lava && l == 0)
                    {
                    	world.playSound(x+0.5, y+0.5, z+0.5, "random.fizz", 1.0F, 1.0F, true);
                        world.spawnParticle("largeSmoke", x+0.5, y+1F, z+0.5, 0F, 0.5F, 0F);
                    	return this.createNewInstance(bucket, user, Items.coal, 1);
                    }
                }
                else
                {
                    if (this.isFull == Blocks.air)
                    {
                        return new ItemStack(ToolListMF.bucketwood_empty);
                    }

                    if (movingobjectposition.sideHit == 0)
                    {
                        --y;
                    }

                    if (movingobjectposition.sideHit == 1)
                    {
                        ++y;
                    }

                    if (movingobjectposition.sideHit == 2)
                    {
                        --z;
                    }

                    if (movingobjectposition.sideHit == 3)
                    {
                        ++z;
                    }

                    if (movingobjectposition.sideHit == 4)
                    {
                        --x;
                    }

                    if (movingobjectposition.sideHit == 5)
                    {
                        ++x;
                    }

                    if (!user.canPlayerEdit(x, y, z, movingobjectposition.sideHit, bucket))
                    {
                        return bucket;
                    }

                    if (this.tryPlaceContainedLiquid(world, x, y, z) && !user.capabilities.isCreativeMode)
                    {
                        return new ItemStack(ToolListMF.bucketwood_empty);
                    }
                }
            }

            return bucket;
        }
    }

    private ItemStack createNewInstance(ItemStack bucket, EntityPlayer user, Item newstack, int m)
    {
        if (user.capabilities.isCreativeMode)
        {
            return bucket;
        }
        else if (--bucket.stackSize <= 0)
        {
            return new ItemStack(newstack, 1, m);
        }
        else
        {
            if (!user.inventory.addItemStackToInventory(new ItemStack(newstack, 1, m)))
            {
                user.dropPlayerItemWithRandomChoice(new ItemStack(newstack, 1, m), false);
            }

            return bucket;
        }
    }

    /**
     * Attempts to place the liquid contained inside the bucket.
     */
    public boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
    {
        if (this.isFull == Blocks.air)
        {
            return false;
        }
        else
        {
            Material material = world.getBlock(x, y, z).getMaterial();
            boolean flag = !material.isSolid();

            if (!world.isAirBlock(x, y, z) && !flag)
            {
                return false;
            }
            else
            {
                if (world.provider.isHellWorld && this.isFull == Blocks.flowing_water)
                {
                    world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l)
                    {
                        world.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }
                else
                {
                    if (!world.isRemote && flag && !material.isLiquid())
                    {
                        world.func_147480_a(x, y, z, true);
                    }

                    world.setBlock(x, y, z, this.isFull, 0, 3);
                }

                return true;
            }
        }
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity)
    {
        if(this == ToolListMF.bucketwood_empty && entity instanceof EntityCow)
        {
        	ItemStack newStack = createNewInstance(player.getHeldItem(), player, ToolListMF.bucketwood_water, 0);
        	player.setCurrentItemOrArmor(0, newStack);
        	return true;
        }
        return super.itemInteractionForEntity(itemstack, player, entity);
    }
}