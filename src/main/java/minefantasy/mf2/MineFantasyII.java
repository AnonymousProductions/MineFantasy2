package minefantasy.mf2;

import java.io.File;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.armour.ArmourDesign;
import minefantasy.mf2.api.armour.CustomArmourEntry;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigArmour;
import minefantasy.mf2.config.ConfigClient;
import minefantasy.mf2.config.ConfigCrafting;
import minefantasy.mf2.config.ConfigExperiment;
import minefantasy.mf2.config.ConfigFarming;
import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.config.ConfigItemRegistry;
import minefantasy.mf2.config.ConfigStamina;
import minefantasy.mf2.config.ConfigTools;
import minefantasy.mf2.config.ConfigWeapon;
import minefantasy.mf2.config.ConfigWorldGen;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.gadget.ItemMine;
import minefantasy.mf2.item.list.ArmourListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.knowledge.KnowledgeCostRegistry;
import minefantasy.mf2.knowledge.KnowledgeListMF;
import minefantasy.mf2.mechanics.worldGen.WorldGenMFBase;
import minefantasy.mf2.mechanics.worldGen.WorldGenPlants;
import minefantasy.mf2.network.CommonProxyMF;
import minefantasy.mf2.recipe.BasicRecipesMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @author Anonymous Productions
 */
@Mod(modid = MineFantasyII.MODID, name = MineFantasyII.NAME, dependencies = "required-after:Forge@[7.0,);required-after:FML@[5.0.5,)", version = MineFantasyII.VERSION)
public class MineFantasyII 
{
	public static final String MODID = "minefantasy2";
	public static final String NAME = "MineFantasyII";
	public static final String VERSION = "Alpha_2.1.8";
	public static final WorldGenMFBase worldGenManager = new WorldGenMFBase();
	
    @SidedProxy(clientSide = "minefantasy.mf2.network.ClientProxyMF", serverSide = "minefantasy.mf2.network.CommonProxyMF")
    public static CommonProxyMF proxy;
    //public static PacketHandlerMF packetHandler;
	
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
		
		//MinecraftForge.EVENT_BUS.register(ModelBakeEventHandlerTongs.instance);
		
		MineFantasyAPI.isInDebugMode = isDebug();
		MFLogUtil.log("API Debug mode updated: " + MineFantasyAPI.isInDebugMode);
		
    	proxy.registerTickHandlers();
    	addModFlags();
    	
    	proxy.preInit();
    	//EnumHelper.addArmorMaterial("MF Armour Base","minefantasy2:textures/models/armour/Leather/"+"hide_layer_1", 0, new int[]{2, 6, 5, 2}, 0);
    	//EnumHelper.addArmorMaterial("MF Armour Base","minefantasy2:textures/models/armour/Mail/"+"adamantium_mail_layer_1", 0, new int[]{2, 6, 5, 2}, 0);
    	//EnumHelper.addArmorMaterial("MF Armour Base","minefantasy2:textures/models/armour/Padding/"+"padded_layer_1_cloth", 0, new int[]{2, 6, 5, 2}, 0);
    	//EnumHelper.addArmorMaterial("MF Armour Base","minefantasy2:textures/models/armour/Plate/"+"adamantium_plate_layer_1", 0, new int[]{2, 6, 5, 2}, 0);
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
    	ToolListMF.init(event);
    	ComponentListMF.init(event);
    	KnowledgeListMF.init();
    	BasicRecipesMF.init();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent evt)
    {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.registerMain();
        GameRegistry.registerWorldGenerator(worldGenManager, 0);
        
        //packetHandler = new PacketHandlerMF();
        FMLEventChannel eventChannel;
        /**for(String channel:packetHandler.packetList.keySet())
        {
            eventChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(channel);
            eventChannel.register(packetHandler);
            packetHandler.channels.put(channel, eventChannel);
        }*/
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
    	KnowledgeCostRegistry.init();
    }

    private void registerBiomeStuff(BiomeGenBase biome)
	{
    	if(WorldGenPlants.isBiomeInConstraint(biome, ConfigWorldGen.berryMinTemp, ConfigWorldGen.berryMaxTemp, ConfigWorldGen.berryMinRain, ConfigWorldGen.berryMaxRain))
		{
    		//addFlower(IBlockState state, int weight)
    		biome.addFlower(BlockListMF.berryBush.getStateFromMeta(0), 5);
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
		return name.equals("Galactic_Hiker") || name.equals("tim4200")||name.equals("cleverpanda714");
	}
}