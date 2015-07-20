package minefantasy.mf2.config;

import minefantasy.mf2.api.archery.Arrows;
import minefantasy.mf2.api.helpers.ArmourCalculator;
import minefantasy.mf2.block.list.BlockListMF;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConfigClient extends ConfigurationBaseMF
{
	public static final String CATEGORY_GUI = "Gui/Hud Features";
	
	
	public static final String CATEGORY_AESTHETIC = "Aesthetics";
	
	public static boolean playBreath;
	public static boolean playHitsound;
	
	
	public static final String GUI_STAMINA = "Stamina Bar Positioning";
	
	public static int stam_xOrient;
	public static int stam_yOrient;
	public static int stam_xPos;
	public static int stam_yPos;
	public static int stam_direction;
	
	
	public static final String GUI_ARATING = "Armour Rating Positioning";
	
	public static int AR_xOrient;
	public static int AR_yOrient;
	public static int AR_xPos;
	public static int AR_yPos;

	
	public static final String GUI_ACOUNT = "Arrow Count Positioning";

	
	public static int AC_xOrient;
	public static int AC_yOrient;
	public static int AC_xPos;
	public static int AC_yPos;
	
	
	public static final String CATEGORY_BLOCK = "Block Render Ids";
	@Override
	protected void loadConfig()
	{
		playBreath = Boolean.parseBoolean(config.get(CATEGORY_AESTHETIC, "Make Breathe Sound", true, "[With Stamina System] Plays breath sounds when low on energy(sound may be annoying to some...)").getString());
		playHitsound = Boolean.parseBoolean(config.get(CATEGORY_AESTHETIC, "Make Hit Sound", true, "Plays sounds when hitting entities with different items").getString());
		
		Arrows.displayArrowCount = Boolean.parseBoolean(config.get(CATEGORY_GUI, "Count Arrows", true, "This option displays how many of your selected arrow are left. It works for all arrows with added support(MineFantasy arrows)").getString());
		//ArmourCalculator.usePercentage = Boolean.parseBoolean(config.get(CATEGORY_GUI, "Use Percentage Values", true, "When true: the armour rating counts the total percentage of damage resisted, when false it uses a special value").getString());
		
		stam_xOrient = Integer.parseInt(config.get(GUI_STAMINA, "X Orient", 1, "The orientation for the X axis (-1 = left, 0 = middle, 1 = right). Determines what point in the axis to snap to").getString());
		stam_yOrient = Integer.parseInt(config.get(GUI_STAMINA, "Y Orient", 1, "The orientation for the Y axis (-1 = top, 0 = middle, 1 = bottom). Determines what point in the axis to snap to").getString());
		stam_xPos = Integer.parseInt(config.get(GUI_STAMINA, "X Position", -82, "The Offset value away from the orient (-)left, (+)right").getString());
		stam_yPos = Integer.parseInt(config.get(GUI_STAMINA, "Y Position", -7, "The Offset value away from the orient (-)up, (+)down").getString());
		stam_direction = Integer.parseInt(config.get(GUI_STAMINA, "Metre Direction", -1, "The direction the metre goes down: -1 = left to right, 0 = middle, 1 = right to left (May have subtle flaws in altered directions 1 and 0)").getString());
		
		AR_xOrient = Integer.parseInt(config.get(GUI_ARATING, "X Orient", -1, "The orientation for the X axis (-1 = left, 0 = middle, 1 = right). Determines what point in the axis to snap to").getString());
		AR_yOrient = Integer.parseInt(config.get(GUI_ARATING, "Y Orient", -1, "The orientation for the Y axis (-1 = top, 0 = middle, 1 = bottom). Determines what point in the axis to snap to").getString());
		AR_xPos = Integer.parseInt(config.get(GUI_ARATING, "X Position", 4, "The Offset value away from the orient (-)left, (+)right").getString());
		AR_yPos = Integer.parseInt(config.get(GUI_ARATING, "Y Position", 4, "The Offset value away from the orient (-)up, (+)down").getString());
		
		AC_xOrient = Integer.parseInt(config.get(GUI_ACOUNT, "X Orient", -1, "The orientation for the X axis (-1 = left, 0 = middle, 1 = right). Determines what point in the axis to snap to").getString());
		AC_yOrient = Integer.parseInt(config.get(GUI_ACOUNT, "Y Orient", -1, "The orientation for the Y axis (-1 = top, 0 = middle, 1 = bottom). Determines what point in the axis to snap to").getString());
		AC_xPos = Integer.parseInt(config.get(GUI_ACOUNT, "X Position", 4, "The Offset value away from the orient (-)left, (+)right").getString());
		AC_yPos = Integer.parseInt(config.get(GUI_ACOUNT, "Y Position", 16, "The Offset value away from the orient (-)up, (+)down").getString());
		
		BlockListMF.anvil_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Anvil", -100).getString());
		BlockListMF.carpenter_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Carpenter", -101).getString());
		BlockListMF.bomb_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Bomb Bench", -102).getString());
		BlockListMF.tanner_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Tanning Rack", -103).getString());
		BlockListMF.forge_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Forge", -104).getString());
		BlockListMF.bellows_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "Bellows", -105).getString());
		BlockListMF.research_RI = Integer.parseInt(config.get(CATEGORY_BLOCK, "ResearchTable", -106).getString());
	}

}
