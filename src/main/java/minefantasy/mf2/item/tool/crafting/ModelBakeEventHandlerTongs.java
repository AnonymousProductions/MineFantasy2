package minefantasy.mf2.item.tool.crafting;

import net.minecraft.client.resources.model.IBakedModel;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBakeEventHandlerTongs {
	public static final ModelBakeEventHandlerTongs instance = new ModelBakeEventHandlerTongs();

	  private ModelBakeEventHandlerTongs() {};

	  // Called after all the other baked models have been added to the modelRegistry
	  // Allows us to manipulate the modelRegistry before BlockModelShapes caches them.
	  @SubscribeEvent
	  public void onModelBakeEvent(ModelBakeEvent event)
	  {
	    // Find the existing mapping for ChessboardSmartItemModel - we added it in StartupClientOnly.initClientOnly(), which
	    //   caused it to be loaded from resources (model/items/mbe15_item_chessboard.json) just like an ordinary item
	    // Replace the mapping with our ISmartBlockModel, using the existing mapped model as the base for the smart model.
	    Object object =  event.modelRegistry.getObject(TongsSmartItemModel.modelResourceLocation);
	    if (object instanceof IBakedModel) {
	      IBakedModel existingModel = (IBakedModel)object;
	      TongsSmartItemModel customModel = new TongsSmartItemModel(existingModel);
	      event.modelRegistry.putObject(TongsSmartItemModel.modelResourceLocation, customModel);
	    }
	  }

}
