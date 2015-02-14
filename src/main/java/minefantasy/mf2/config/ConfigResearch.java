package minefantasy.mf2.config;

import minefantasy.mf2.api.knowledge.ResearchLogic;
import minefantasy.mf2.mechanics.CombatMechanics;

public class ConfigResearch extends ConfigurationBaseMF
{
	public static final String CATEGORY_VARS = "Variables";
	
	@Override
	protected void loadConfig()
	{
		ResearchLogic.xpToKnowledge = Integer.parseInt(config.get(CATEGORY_VARS, "Knowledge Gain XP", 25, "How much 'XP' you need (from orbs and such) to gain a knowledge point").getString());
		ResearchLogic.startersPoints = Integer.parseInt(config.get(CATEGORY_VARS, "Starting Points", 10, "How many Knowledge points you start off with").getString());
	}

}
