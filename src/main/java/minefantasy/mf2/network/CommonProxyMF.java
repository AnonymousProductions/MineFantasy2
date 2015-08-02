package minefantasy.mf2.network;

import java.util.Random;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.refine.ISmokeHandler;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityBellows;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.block.tileentity.TileEntityChimney;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import minefantasy.mf2.block.tileentity.TileEntityResearch;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFC;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.container.ContainerAnvilMF;
import minefantasy.mf2.container.ContainerBlastChamber;
import minefantasy.mf2.container.ContainerBlastHeater;
import minefantasy.mf2.container.ContainerBombBench;
import minefantasy.mf2.container.ContainerCarpenterMF;
import minefantasy.mf2.container.ContainerCrucible;
import minefantasy.mf2.container.ContainerForge;
import minefantasy.mf2.container.ContainerResearch;
import minefantasy.mf2.entity.EntityArrowMF;
import minefantasy.mf2.entity.EntityBomb;
import minefantasy.mf2.entity.EntityFireBlast;
import minefantasy.mf2.entity.EntityItemUnbreakable;
import minefantasy.mf2.entity.EntityMine;
import minefantasy.mf2.entity.EntityShrapnel;
import minefantasy.mf2.entity.EntitySmoke;
import minefantasy.mf2.hunger.HungerSystemMF;
import minefantasy.mf2.item.archery.ArrowFireFlint;
import minefantasy.mf2.item.archery.ArrowFirerMF;
import minefantasy.mf2.mechanics.ArrowHandlerMF;
import minefantasy.mf2.mechanics.CombatMechanics;
import minefantasy.mf2.mechanics.EventManagerMF;
import minefantasy.mf2.mechanics.MonsterUpgrader;
import minefantasy.mf2.mechanics.PlayerTickHandlerMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
/**
 * @author Anonymous Productions
 */
public class CommonProxyMF implements IGuiHandler, ISmokeHandler
{
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	BlockPos pos = new BlockPos(x,y,z);
    	TileEntity tile = world.getTileEntity(pos);
		int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		
		if(tile != null && tile instanceof TileEntityAnvilMF)
		{
			return new ContainerAnvilMF(player.inventory, (TileEntityAnvilMF) tile);
		}
		if(tile != null && tile instanceof TileEntityCarpenterMF)
		{
			return new ContainerCarpenterMF(player.inventory, (TileEntityCarpenterMF) tile);
		}
		if(tile != null && tile instanceof TileEntityBombBench)
		{
			return new ContainerBombBench(player.inventory, (TileEntityBombBench) tile);
		}
		if(tile != null && tile instanceof TileEntityBlastFH)
		{
			return new ContainerBlastHeater(player.inventory, (TileEntityBlastFH) tile);
		}
		if(tile != null && tile instanceof TileEntityBlastFC)
		{
			return new ContainerBlastChamber(player.inventory, (TileEntityBlastFC) tile);
		}
		if(tile != null && tile instanceof TileEntityCrucible)
		{
			return new ContainerCrucible(player.inventory, (TileEntityCrucible) tile);
		}
		if(tile != null && tile instanceof TileEntityForge)
		{
			return new ContainerForge(player.inventory, (TileEntityForge) tile);
		}
		if(tile != null && tile instanceof TileEntityResearch)
		{
			return new ContainerResearch(player.inventory, (TileEntityResearch) tile);
		}
        return null;
    }

    public World getClientWorld()
    {
        return null;
    }

    private int IDBase;
	public void registerMain() 
	{
		Arrows.addHandler(new ArrowFireFlint());
		Arrows.addHandler(new ArrowFirerMF());
		
		if(ConfigExperiment.dynamicArrows)
		{
			EntityRegistry.registerModEntity(EntityArrowMF.class, "arrowMF", IDBase, MineFantasyII.instance, 64, 1, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityBomb.class, "bombMF", IDBase, MineFantasyII.instance, 64, 1, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityShrapnel.class, "shrapnel_mf", IDBase, MineFantasyII.instance, 16, 1, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityFireBlast.class, "fire_blast", IDBase, MineFantasyII.instance, 64, 2, true);IDBase ++;
			EntityRegistry.registerModEntity(EntitySmoke.class, "smoke_mf", IDBase, MineFantasyII.instance, 64, 2, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityItemUnbreakable.class, "special_eitem_mf", IDBase, MineFantasyII.instance, 64, 2, true);IDBase ++;
		}
		else
		{
			EntityRegistry.registerModEntity(EntityArrowMF.class, "arrowMF", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityBomb.class, "bombMF", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityShrapnel.class, "shrapnel_mf", IDBase, MineFantasyII.instance, 16, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityFireBlast.class, "fire_blast", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntitySmoke.class, "smoke_mf", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityItemUnbreakable.class, "special_eitem_mf", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
		}
		EntityRegistry.registerModEntity(EntityMine.class, "landmineMF", IDBase, MineFantasyII.instance, 16, 10, true);IDBase ++;
		registerTileEntities();
		SmokeMechanics.handler = this;
	}

	protected void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAnvilMF.class, "MF_Anvil");
		GameRegistry.registerTileEntity(TileEntityCarpenterMF.class, "MF_CarpenterBench");
		GameRegistry.registerTileEntity(TileEntityBombBench.class, "MF_BombBench");
		GameRegistry.registerTileEntity(TileEntityBlastFC.class, "MF_BlastChamber");
		GameRegistry.registerTileEntity(TileEntityBlastFH.class, "MF_BlastHeater");
		GameRegistry.registerTileEntity(TileEntityCrucible.class, "MF_Crucible");
		GameRegistry.registerTileEntity(TileEntityChimney.class, "MF_Chimney");
		GameRegistry.registerTileEntity(TileEntityTanningRack.class, "MF_Tanner");
		GameRegistry.registerTileEntity(TileEntityForge.class, "MF_Forge");
		GameRegistry.registerTileEntity(TileEntityBellows.class, "MF_Bellows");
		GameRegistry.registerTileEntity(TileEntityResearch.class, "MF_Research");
	}


	public void preInit()
	{
	}

	public void registerTickHandlers()
	{
		FMLCommonHandler.instance().bus().register(new PlayerTickHandlerMF());
		FMLCommonHandler.instance().bus().register(new HungerSystemMF());
		MinecraftForge.EVENT_BUS.register(new EventManagerMF());
		MinecraftForge.EVENT_BUS.register(new CombatMechanics());
		MinecraftForge.EVENT_BUS.register(new MonsterUpgrader());
		MinecraftForge.EVENT_BUS.register(new ArrowHandlerMF());
	}

	public EntityPlayer getClientPlayer()
	{
		return null;
	}
	
	public static int renderID = -1;
	private static Random rand = new Random();
	@Override
	public void spawnSmoke(World world, double x, double y, double z, int value) 
	{
		for(int a = 0; a < value; a++)
		{
			float sprayRange = 0.005F;
			float sprayX = (rand.nextFloat()*sprayRange) - (sprayRange/2);
			float sprayZ = (rand.nextFloat()*sprayRange) - (sprayRange/2);
			float height = 0.001F;
			if(rand.nextInt(2) == 0)
			{
				EntitySmoke smoke = new EntitySmoke(world ,x, y-0.5D, z, sprayX, height, sprayZ);
				world.spawnEntityInWorld(smoke);
			}
		}
	}

	public void Init() {
		// TODO Auto-generated method stub
		
	}
}