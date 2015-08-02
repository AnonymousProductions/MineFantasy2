package minefantasy.mf2.network;

import minefantasy.mf2.api.helpers.ClientTickHandler;
import minefantasy.mf2.api.knowledge.InformationList;
import minefantasy.mf2.block.tileentity.TileEntityAnvilMF;
import minefantasy.mf2.block.tileentity.TileEntityBellows;
import minefantasy.mf2.block.tileentity.TileEntityBombBench;
import minefantasy.mf2.block.tileentity.TileEntityCarpenterMF;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import minefantasy.mf2.block.tileentity.TileEntityResearch;
import minefantasy.mf2.block.tileentity.TileEntityTanningRack;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFC;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.client.KnowledgePageRegistry;
import minefantasy.mf2.client.gui.GuiAnvilMF;
import minefantasy.mf2.client.gui.GuiBlastChamber;
import minefantasy.mf2.client.gui.GuiBlastHeater;
import minefantasy.mf2.client.gui.GuiBombBench;
import minefantasy.mf2.client.gui.GuiCarpenterMF;
import minefantasy.mf2.client.gui.GuiCrucible;
import minefantasy.mf2.client.gui.GuiForge;
import minefantasy.mf2.client.gui.GuiKnowledge;
import minefantasy.mf2.client.gui.GuiKnowledgeEntry;
import minefantasy.mf2.client.gui.GuiResearchBlock;
import minefantasy.mf2.client.render.AnimationHandlerMF;
import minefantasy.mf2.client.render.HudHandlerMF;
import minefantasy.mf2.client.render.RenderArrowMF;
//import minefantasy.mf2.client.render.RenderBombIcon;
//import minefantasy.mf2.client.render.RenderBow;
import minefantasy.mf2.client.render.RenderFireBlast;
import minefantasy.mf2.client.render.block.TileEntityBellowsRenderer;
import minefantasy.mf2.client.render.block.TileEntityBombBenchRenderer;
import minefantasy.mf2.client.render.block.TileEntityCarpenterRenderer;
import minefantasy.mf2.client.render.block.TileEntityForgeRenderer;
import minefantasy.mf2.client.render.block.TileEntityResearchRenderer;
import minefantasy.mf2.client.render.block.TileEntityTanningRackRenderer;
import minefantasy.mf2.entity.EntityArrowMF;
import minefantasy.mf2.entity.EntityBomb;
import minefantasy.mf2.entity.EntityFireBlast;
import minefantasy.mf2.entity.EntityMine;
import minefantasy.mf2.entity.EntityShrapnel;
import minefantasy.mf2.entity.EntitySmoke;
import minefantasy.mf2.item.archery.ItemBowMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.advanced.ItemScythe;
import minefantasy.mf2.item.tool.crafting.ItemSaw;
import minefantasy.mf2.item.weapon.ItemBattleaxeMF;
import minefantasy.mf2.item.weapon.ItemGreatswordMF;
import minefantasy.mf2.item.weapon.ItemHalbeardMF;
import minefantasy.mf2.item.weapon.ItemKatanaMF;
import minefantasy.mf2.item.weapon.ItemLance;
import minefantasy.mf2.item.weapon.ItemMaceMF;
import minefantasy.mf2.item.weapon.ItemSpearMF;
import minefantasy.mf2.item.weapon.ItemSwordMF;
import minefantasy.mf2.item.weapon.ItemWaraxeMF;
import minefantasy.mf2.item.weapon.ItemWarhammerMF;
import minefantasy.mf2.mechanics.ExtendedReachMF;
import minefantasy.mf2.mechanics.PlayerTickHandlerMF;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
/**
 * @author Anonymous Productions
 */
public class ClientProxyMF extends CommonProxyMF
{

	@Override
    public World getClientWorld()
    {
        return FMLClientHandler.instance().getClient().theWorld;
    }
	
	@Override
	public void Init()
	{
		registerEntityRenderer();
		
//		for(ItemBattleaxeMF axe: ToolListMF.battleaxes)
//			MinecraftForgeClient.registerItemRenderer(axe, new RenderHeavyWeapon().setBlunt());
//		
//		for(ItemWarhammerMF hammer: ToolListMF.warhammers)
//			MinecraftForgeClient.registerItemRenderer(hammer, new RenderHeavyWeapon().setBlunt());
//		
//		for(ItemGreatswordMF sword: ToolListMF.greatswords)
//			MinecraftForgeClient.registerItemRenderer(sword, new RenderHeavyWeapon().setGreatsword().setParryable());
//		
//		for(ItemKatanaMF sword: ToolListMF.katanas)
//			MinecraftForgeClient.registerItemRenderer(sword, new RenderHeavyWeapon().setGreatsword());
//		for(ItemLance spear: ToolListMF.lances)
//			MinecraftForgeClient.registerItemRenderer(spear, new RenderLance());

		
		//for(ItemBowMF bow: ToolListMF.bows)
			//MinecraftForgeClient.registerItemRenderer(bow, new RenderBow(false));
		
//		for(ItemSaw saw: ToolListMF.saws)
//			MinecraftForgeClient.registerItemRenderer(saw, new RenderSaw());
		
		//for(ItemScythe scythe: ToolListMF.scythes)
		//	MinecraftForgeClient.registerItemRenderer(scythe, new RenderHeavyWeapon().setBlunt());
		
		KnowledgePageRegistry.registerPages();
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
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		
				
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarpenterMF.class, new TileEntityCarpenterRenderer());
		
		//RenderingRegistry.registerBlockHandler(new RenderBombBench());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBombBench.class, new TileEntityBombBenchRenderer());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTanningRack.class, new TileEntityTanningRackRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityForge.class, new TileEntityForgeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBellows.class, new TileEntityBellowsRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityResearch.class, new TileEntityResearchRenderer());
	
	
		//ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("minecraftbyexample:mbe21_tesr_block", "inventory");
	    //final int DEFAULT_ITEM_SUBTYPE = 0;
	    //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemBlockSimple, 0, itemModelResourceLocation);
	}
	
	public void registerEntityRenderer()
	{
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		RenderItem ri = Minecraft.getMinecraft().getRenderItem();
				
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowMF.class, new RenderArrowMF(rm));
		//RenderingRegistry.registerEntityRenderingHandler(EntityBomb.class, new RenderBombIcon());//Switch to RenderBomb when syncing is fixed
		//RenderingRegistry.registerEntityRenderingHandler(EntityMine.class, new RenderMine());
		RenderingRegistry.registerEntityRenderingHandler(EntityShrapnel.class, new RenderSnowball(rm, ComponentListMF.shrapnel, ri));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireBlast.class, new RenderFireBlast(rm));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmoke.class, new RenderFireBlast(rm));
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
			BlockPos pos = new BlockPos(x, y, z);
	    	TileEntity tile = world.getTileEntity(pos);
			int meta = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
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
			if(tile != null && tile instanceof TileEntityBlastFH)
			{
				return new GuiBlastHeater(player.inventory, (TileEntityBlastFH) tile);
			}
			if(tile != null && tile instanceof TileEntityBlastFC)
			{
				return new GuiBlastChamber(player.inventory, (TileEntityBlastFC) tile);
			}
			if(tile != null && tile instanceof TileEntityCrucible)
			{
				return new GuiCrucible(player.inventory, (TileEntityCrucible) tile);
			}
			if(tile != null && tile instanceof TileEntityForge)
			{
				return new GuiForge(player.inventory, (TileEntityForge) tile);
			}
			if(tile != null && tile instanceof TileEntityResearch)
			{
				return new GuiResearchBlock(player.inventory, (TileEntityResearch) tile);
			}
			 return null;
		}
		if(ID == 1)
		{
			if(x == 0)
			{//GuiAchievements
				if(y >= 0)
				{
					return new GuiKnowledgeEntry(mc.currentScreen, InformationList.knowledgeList.get(y));
				}
				return new GuiKnowledge(player);
			}
		}
        return null;
    }

	/*
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
	*/
}
