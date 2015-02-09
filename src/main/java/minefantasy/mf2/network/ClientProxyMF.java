package minefantasy.mf2.network;

import minefantasy.mf2.block.crafting.BlockAnvilMF;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.*;
import minefantasy.mf2.client.gui.*;
import minefantasy.mf2.client.render.*;
import minefantasy.mf2.client.render.block.TileEntityAnvilMFRenderer;
import minefantasy.mf2.entity.*;
import minefantasy.mf2.item.archery.ItemBowMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.advanced.ItemScythe;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import minefantasy.mf2.item.weapon.*;
import minefantasy.mf2.mechanics.ExtendedReachMF;
import minefantasy.mf2.mechanics.PlayerTickHandlerMF;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
/**
 * @author Anonymous Productions
 */
public class ClientProxyMF extends CommonProxyMF implements ISimpleBlockRenderingHandler
{

	@Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
	
	@Override
	public void preInit()
	{
		registerEntityRenderer();
		
		for(ItemBattleaxeMF axe: ToolListMF.battleaxes)
			MinecraftForgeClient.registerItemRenderer(axe, new RenderHeavyWeapon().setBlunt());
		
		for(ItemWarhammerMF hammer: ToolListMF.warhammers)
			MinecraftForgeClient.registerItemRenderer(hammer, new RenderHeavyWeapon().setBlunt());
		
		for(ItemGreatswordMF sword: ToolListMF.greatswords)
			MinecraftForgeClient.registerItemRenderer(sword, new RenderHeavyWeapon().setGreatsword().setParryable());
		
		for(ItemKatanaMF sword: ToolListMF.katanas)
			MinecraftForgeClient.registerItemRenderer(sword, new RenderHeavyWeapon().setGreatsword());
		
		for(ItemSpearMF spear: ToolListMF.spears)
			MinecraftForgeClient.registerItemRenderer(spear, new RenderSpear());
		for(ItemLance spear: ToolListMF.lances)
			MinecraftForgeClient.registerItemRenderer(spear, new RenderLance());
		for(ItemHalbeardMF spear: ToolListMF.halbeards)
			MinecraftForgeClient.registerItemRenderer(spear, new RenderSpear(true));
		
			MinecraftForgeClient.registerItemRenderer(ToolListMF.spearStone, new RenderSpear());
			MinecraftForgeClient.registerItemRenderer(ToolListMF.spearTraining, new RenderSpear());
		
		for(ItemSwordMF sword: ToolListMF.swords)
			MinecraftForgeClient.registerItemRenderer(sword, new RenderSword());
		
		MinecraftForgeClient.registerItemRenderer(ToolListMF.swordTraining, new RenderSword());
		
		for(ItemBowMF bow: ToolListMF.bows)
			MinecraftForgeClient.registerItemRenderer(bow, new RenderBow(false));
		
		for(ItemSaw saw: ToolListMF.saws)
			MinecraftForgeClient.registerItemRenderer(saw, new RenderSaw());
		
		for(ItemScythe scythe: ToolListMF.scythes)
			MinecraftForgeClient.registerItemRenderer(scythe, new RenderHeavyWeapon().setBlunt());
	}
	
	@Override
	public void registerMain() 
	{
		super.registerMain();
	}
	
	@Override
	public void registerTickHandlers()
	{
		super.registerTickHandlers();
		FMLCommonHandler.instance().bus().register(new PlayerTickHandlerMF());
		FMLCommonHandler.instance().bus().register(new AnimationHandlerMF());
		FMLCommonHandler.instance().bus().register(new ExtendedReachMF());
		MinecraftForge.EVENT_BUS.register(new HudHandlerMF());
		
		TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(TileEntityAnvilMF.class, new TileEntityAnvilMFRenderer());
	}
	
	public void registerEntityRenderer()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowMF.class, new RenderArrowMF());
		RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderBombIcon());//Switch to RenderBomb when syncing is fixed
		RenderingRegistry.registerEntityRenderingHandler(EntityMine.class, new RenderMine());
		RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new RenderSnowball(ComponentListMF.shrapnel));
	}
	
	@Override
    public EntityPlayer getClientPlayer()
	{
        return Minecraft.getMinecraft().thePlayer;
    }
	@Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
		Minecraft mc = Minecraft.getMinecraft();
		if(ID == 0)
		{
	    	TileEntity tile = world.getTileEntity(x, y, z);
			int meta = world.getBlockMetadata(x, y, z);
			
			if(tile != null && tile instanceof TileEntityAnvilMF)
			{
				return new GuiAnvilMF(player.inventory, (TileEntityAnvilMF) tile);
			}
			if(tile != null && tile instanceof TileEntityCarpenterMF)
			{
				return new GuiCarpenterMF(player.inventory, (TileEntityCarpenterMF) tile);
			}
			if(tile != null && tile instanceof TileEntityBombBench)
			{
				return new GuiBombBench(player.inventory, (TileEntityBombBench) tile);
			}
			 return null;
		}
		if(ID == 1)
		{
			if(x == 0)
			{//GuiAchievements
				return new GuiKnowledge(player);
			}
		}
        return null;
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		if(block instanceof BlockAnvilMF)
		{
			BlockAnvilMF anvil = (BlockAnvilMF)block;
			 new TileEntityAnvilMFRenderer().renderModelAt(((BlockAnvilMF) block).material.name, 0, 0.0D, 0.0D, 0.0D, 0.0F, 0);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block,
			int modelId, RenderBlocks renderer)
	{
		return modelId == renderID;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		// TODO Auto-generated method stub
		return modelId == renderID;
	}

	@Override
	public int getRenderId()
	{
		// TODO Auto-generated method stub
		return renderID;
	}
}
