package minefantasy.mf2.block.crafting;

import java.util.Random;

import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRepairKit extends Block
{
	protected float repairLevel;
	protected float successRate;
	protected float breakChance;
	protected IIcon top, side, bottom;
	private String type;
	protected boolean isOrnate = false;
	protected float repairLevelEnchant = 0.0F;
	
	private String NAME;
	
	public BlockRepairKit(String name, float repairLevel, float rate, float breakChance)
	{
		super(Material.cloth);
		
		this.repairLevel = repairLevel;
		this.successRate = rate;
		this.breakChance = breakChance;
		this.type=name;
        setBlockBounds(1F/16F, 0F, 1F/16F, 15F/16F, 6F/16F, 15F/16F);
        
        //this.setBlockTextureName("minefantasy2:processor/"+"repair_"+name+"+top");
        name = "repair_"+name;
        NAME = name;
        GameRegistry.registerBlock(this, ItemBlockRepairKit.class, name);
        setUnlocalizedName("minefantasy2:processor/"+"repair_"+name+"+top");
		this.setStepSound(Block.soundTypeCloth);
		this.setHardness(1F);
		this.setResistance(0F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabGadget);
	}
	
	public String getName()
	{
		return NAME;
	}
	
	public BlockRepairKit setOrnate(float enc)
	{
		repairLevelEnchant = enc;
		isOrnate = true;
		return this;
	}

	@SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? this.top : (side == 0 ? this.bottom : this.side);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister reg)
    {
        this.side = reg.registerIcon("minefantasy2:processor/repair_"+ type + "_side");
        this.top = reg.registerIcon("minefantasy2:processor/repair_"+ type + "_top");
        this.bottom = reg.registerIcon("minefantasy2:processor/repair_"+ type + "_base");
    }
	
	@Override
    public boolean isFullCube()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    private Random rand = new Random();
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer user, EnumFacing side, float xOffset, float yOffset, float zOffset)
    {
    	if(world.isRemote)
    	{
    		return true;
    	}
    	ItemStack held = user.getHeldItem();
    	if(held != null && held.isItemDamaged() && held.getItem().isRepairable() && (!held.isItemEnchanted() || isOrnate))
    	{
    		if(rand.nextFloat() < successRate)
    		{
    			if(rand.nextFloat() < breakChance)
    			{
    				int repairAmount = (int)((float)held.getMaxDamage()*(repairLevel/2));
    				held.damageItem(repairAmount, user);
    				world.playAuxSFX(1020, pos, 0);
    				world.playSoundEffect(pos.getX()+0.5D, pos.getY()+0.3D, pos.getZ()+0.5D, "random.break", 1.0F, 1.0F);
    			}
    			else
    			{
    				float lvl = held.isItemEnchanted() ? repairLevelEnchant : repairLevel;
    				int repairAmount = (int)((float)held.getMaxDamage()*lvl);
    				held.setItemDamage(Math.max(0, held.getItemDamage() - repairAmount));
    				world.playAuxSFX(1021, pos, 0);
    			}
    			world.setBlockToAir(pos);
    			return true;
    		}
    		else
    		{
    			world.playSoundEffect(pos.getX()+0.5D, pos.getY()+0.3D, pos.getZ()+0.5D, "step.cloth", 0.5F, 0.5F);
    		}
    		return true;
    	}
        return false;
    }
}
