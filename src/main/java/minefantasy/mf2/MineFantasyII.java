package minefantasy.mf2;

import java.io.File;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.CustomArmourEntry;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.*;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.mechanics.worldGen.WorldGenMFBase;
import minefantasy.mf2.mechanics.worldGen.WorldGenPlants;
import minefantasy.mf2.network.CommonProxyMF;
import minefantasy.mf2.network.packet.PacketHandlerMF;
import minefantasy.mf2.recipe.BasicRecipesMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.init.Items;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Anonymous Productions
 */
@Mod(modid = MineFantasyII.MODID, name = MineFantasyII.NAME, dependencies = "required-after:Forge@[7.0,);required-after:FML@[5.0.5,)", version = MineFantasyII.VERSION)
public class MineFantasyII 
{
	public static final String MODID = "minefantasy2";
	public static final String NAME = "MineFantasyII";
	public static final String VERSION = "Alpha_2.1.3";
	public static final WorldGenMFBase worldGenManager = new WorldGenMFBase();
	
    @SidedProxy(clientSide = "minefantasy.mf2.network.ClientProxyMF", serverSide = "minefantasy.mf2.network.CommonProxyMF")
    public static CommonProxyMF proxy;
    public static PacketHandlerMF packetHandler;
	
    @Instance(MODID)
    public static MineFantasyII instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    	if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
    	{
    		new ConfigClient().setConfig(getCfg(event, "Client"));
    	}
		new ConfigArmour().setConfig(getCfg(event, "Armours"));
		new ConfigExperiment().setConfig(getCfg(event, "Specials"));
		new ConfigHardcore().setConfig(getCfg(event, "Hardcore"));
		new ConfigTools().setConfig(getCfg(event, "Tools"));
		new ConfigWeapon().setConfig(getCfg(event, "Weapons"));
		new ConfigStamina().setConfig(getCfg(event, "Stamina_System"));
		new ConfigItemRegistry().setConfig(getCfg(event, "Item_Registry"));
		new ConfigFarming().setConfig(getCfg(event, "Farming"));
		new ConfigWorldGen().setConfig(getCfg(event, "WorldGen"));
		new ConfigCrafting().setConfig(getCfg(event, "Crafting"));
		
		MineFantasyAPI.isInDebugMode = isDebug();
		MFLogUtil.log("API Debug mode updated: " + MineFantasyAPI.isInDebugMode);
		
		ToolListMF.init();
		
    	proxy.registerTickHandlers();
    	addModFlags();
    	ComponentListMF.init();
    	KnowledgeListMF.init();
    	BasicRecipesMF.init();
    	proxy.preInit();
    }

    @EventHandler
    public void load(FMLInitializationEvent evt)
    {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.registerMain();
        GameRegistry.registerWorldGenerator(worldGenManager, 0);
        
        packetHandler = new PacketHandlerMF();
        FMLEventChannel eventChannel;
        for(String channel:packetHandler.packetList.keySet())
        {
            eventChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(channel);
            eventChannel.register(packetHandler);
            packetHandler.channels.put(channel, eventChannel);
        }
    }
    
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent evt)
    {
    	CustomArmourEntry.registerItem(Items.leather_helmet, ArmourDesign.LEATHER);
    	CustomArmourEntry.registerItem(Items.leather_chestplate, ArmourDesign.LEATHER);
    	CustomArmourEntry.registerItem(Items.leather_leggings, ArmourDesign.LEATHER);
    	CustomArmourEntry.registerItem(Items.leather_boots, ArmourDesign.LEATHER);
    	
    	CustomArmourEntry.registerItem(Items.chainmail_helmet, ArmourDesign.MAIL);
    	CustomArmourEntry.registerItem(Items.chainmail_chestplate, ArmourDesign.MAIL);
    	CustomArmourEntry.registerItem(Items.chainmail_leggings, ArmourDesign.MAIL);
    	CustomArmourEntry.registerItem(Items.chainmail_boots, ArmourDesign.MAIL);
    	
    	CustomArmourEntry.registerItem(Items.golden_helmet, ArmourDesign.SOLID, 2.0F);
    	CustomArmourEntry.registerItem(Items.golden_chestplate, ArmourDesign.SOLID, 2.0F);
    	CustomArmourEntry.registerItem(Items.golden_leggings, ArmourDesign.SOLID, 2.0F);
    	CustomArmourEntry.registerItem(Items.golden_boots, ArmourDesign.SOLID, 2.0F);
    	
    	ConfigItemRegistry.readCustoms();
    	
    	for(BiomeGenBase biome : BiomeGenBase.getBiomeGenArray())
    	{
    		registerBiomeStuff(biome);
    	}
    }

    private void registerBiomeStuff(BiomeGenBase biome)
	{
    	if(WorldGenPlants.isBiomeInConstraint(biome, ConfigWorldGen.berryMinTemp, ConfigWorldGen.berryMaxTemp, ConfigWorldGen.berryMinRain, ConfigWorldGen.berryMaxRain))
		{
    		biome.addFlower(BlockListMF.berryBush, 0, 5);
    	}
	}

	private static Configuration getCfg(FMLPreInitializationEvent event, String name)
    {
    	return new Configuration(new File(event.getModConfigurationDirectory(), "MineFantasyII/"+name + ".cfg"));
    }
    
 

    
    
    private void addModFlags() 
	{
		isBGLoaded = setIsBGLoaded();
	}
	public static boolean isBGLoaded() 
	{
		return isBGLoaded;
	}
	public static boolean setIsBGLoaded() 
	{
		try 
		{
			Class.forName("mods.battlegear2.Battlegear");
			//return Loader.isModLoaded("battlegear2");
		} 
		catch (ClassNotFoundException e) 
		{
			return false;
		}
		return true;
	}
	
	public static boolean isDebug()
	{
		return ConfigExperiment.debug.equals("AU32-Db42-Acf6-Ggh9-9E8d");
	}
	
	private static boolean isBGLoaded;

	/**
	 * Determines if a player name is that of a MF modder
	 */
	public static boolean isNameModder(String name) 
	{
		return name.equals("Galactic_Hiker") || name.equals("tim4200");
	}
}