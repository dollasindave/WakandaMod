package dollasindave.daves.vibranium;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;

public class DavesVibraniumClientMod implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DavesVibranium.VIBRANIUM_WINDOW, RenderLayer.getTranslucent());
        class myBlock extends Block{
            public myBlock() {
                super(Settings.of(Material.GLASS).nonOpaque());
            }
        }
    }
}
