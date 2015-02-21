package minefantasy.mf2.config;

import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.mechanics.CombatMechanics;

public class ConfigCrafting extends ConfigurationBaseMF
{
	public static final String CATEGORY_REFINING = "Refining";
	@Override
	protected void loadConfig()
	{
		TileEntityBlastFH.maxFurnaceHeight =  Integer.parseInt(config.get(CATEGORY_REFINING, "Max Blast Furnace Height", 16, "The max amount of chambers a blast furnace can read").getString());
	}

}
