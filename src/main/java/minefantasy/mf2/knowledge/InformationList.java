package minefantasy.mf2.knowledge;

import java.util.ArrayList;
import java.util.List;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.ToolListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonSerializableSet;

public class InformationList
{
	public static InformationPage smithing = new InformationPage("infoPage.smithing").registerInfoPage();
	
    /** Is the smallest column used to display a achievement on the GUI. */
    public static int minDisplayColumn;
    /** Is the smallest row used to display a achievement on the GUI. */
    public static int minDisplayRow;
    /** Is the biggest column used to display a achievement on the GUI. */
    public static int maxDisplayColumn;
    /** Is the biggest row used to display a achievement on the GUI. */
    public static int maxDisplayRow;
    /** Holds a list of all registered achievements. */
    public static List<InformationBase> knowledgeList = new ArrayList();
    /** Is the 'open inventory' achievement. */
    public static InformationBase gettingStarted = (new InformationBase("gettingStarted", 	0, 0, Items.book, (InformationBase)null, MineFantasyII.MODID)).registerStat().setUnlocked();
    public static InformationBase crucible = (new InformationBase("crucible", 					1, 2, Blocks.furnace, (InformationBase)null, MineFantasyII.MODID)).registerStat().setPage(smithing).setUnlocked().setSpecial();
    
    public static InformationBase smeltCopper = (new InformationBase("smeltCopper",  			2, 0, ComponentListMF.ingots[0], (InformationBase)null, MineFantasyII.MODID)).registerStat().setPage(smithing).setUnlocked();
    public static InformationBase smeltIron = (new InformationBase("smeltIron",  				2, 2, Items.iron_ingot, (InformationBase)null, MineFantasyII.MODID)).registerStat().setPage(smithing).setUnlocked();
    public static InformationBase smeltBronze = (new InformationBase("smeltBronze",  			2, 1, ComponentListMF.ingots[2], crucible, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    public static InformationBase blastfurn = (new InformationBase("blastfurn",  				3, 4, Blocks.furnace, crucible, MineFantasyII.MODID)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltSteel = (new InformationBase("smeltSteel",  				5, 4, ComponentListMF.ingots[4], blastfurn, MineFantasyII.MODID)).registerStat().setPage(smithing);
    public static InformationBase encrusted = (new InformationBase("encrusted",  				6, 3, ComponentListMF.diamond_shards, smeltSteel, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    public static InformationBase crucible2 = (new InformationBase("crucible2",  					6, 6, Blocks.furnace, smeltSteel, MineFantasyII.MODID)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltBlackSteel = (new InformationBase("smeltBlackSteel",	5, 7, ComponentListMF.ingots[7], crucible2, MineFantasyII.MODID)).registerStat().setPage(smithing);
    public static InformationBase smeltDragonforge = (new InformationBase("smeltDragonforge",	4, 8, ComponentListMF.ingots[8], smeltBlackSteel, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    public static InformationBase crucible3 = (new InformationBase("crucible3",  					8, 6, Blocks.furnace, crucible2, MineFantasyII.MODID)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltRedSteel = (new InformationBase("smeltRedSteel", 			7, 5, ComponentListMF.ingots[12], crucible3, MineFantasyII.MODID)).registerStat().setPage(smithing);
    public static InformationBase smeltBlueSteel = (new InformationBase("smeltBlueSteel", 		9, 5, ComponentListMF.ingots[14], crucible3, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    public static InformationBase mythic = (new InformationBase("mythic",  								8, 9, Blocks.furnace, crucible3, MineFantasyII.MODID)).registerStat().setPage(smithing).setSpecial();
    public static InformationBase smeltMithril = (new InformationBase("smeltMithril", 			7, 11,  ComponentListMF.ingots[16], mythic, MineFantasyII.MODID)).registerStat().setPage(smithing);
    public static InformationBase smeltAdamant = (new InformationBase("smeltAdamant", 			9, 11, ComponentListMF.ingots[15], mythic, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    public static InformationBase anvil = (new InformationBase("anvil", 								-1, 2, Blocks.anvil, (InformationBase)null, MineFantasyII.MODID)).registerStat().setPage(smithing).setUnlocked().setSpecial();
    public static InformationBase craftTools = (new InformationBase("craftTools", 				-3, 4, ToolListMF.picks[3], anvil, MineFantasyII.MODID)).registerStat().setPage(smithing);
    public static InformationBase craftAdvTools = (new InformationBase("craftAdvTools", 		-5, 4, ToolListMF.hvypicks[2], craftTools, MineFantasyII.MODID)).registerStat().setPage(smithing);
    
    /**
     * A stub functions called to make the static initializer for this class run.
     */
    public static void init() {}
}