package minefantasy.mf2.network;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.container.ContainerAnvilMF;
import minefantasy.mf2.container.ContainerBombBench;
import minefantasy.mf2.container.ContainerCarpenterMF;
import minefantasy.mf2.entity.EntityArrowMF;
import minefantasy.mf2.entity.EntityBomb;
import minefantasy.mf2.entity.EntityMine;
import minefantasy.mf2.entity.EntityShrapnel;
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
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
/**
 * @author Anonymous Productions
 */
public class CommonProxyMF implements IGuiHandler 
{
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
    	TileEntity tile = world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
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
		}
		else
		{
			EntityRegistry.registerModEntity(EntityArrowMF.class, "arrowMF", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityBomb.class, "bombMF", IDBase, MineFantasyII.instance, 64, 20, true);IDBase ++;
			EntityRegistry.registerModEntity(EntityShrapnel.class, "shrapnel_mf", IDBase, MineFantasyII.instance, 16, 20, true);IDBase ++;
		}
		EntityRegistry.registerModEntity(EntityMine.class, "landmineMF", IDBase, MineFantasyII.instance, 16, 10, true);IDBase ++;
		registerTileEntities();
	}

	protected void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAnvilMF.class, "MF_Anvil");
		GameRegistry.registerTileEntity(TileEntityCarpenterMF.class, "MF_CarpenterBench");
		GameRegistry.registerTileEntity(TileEntityBombBench.class, "MF_BombBench");
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
}