package dollasindave.daves.vibranium.block.custom;

import dollasindave.daves.vibranium.DavesVibranium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class HeartShapedHerbCropBlock extends CropBlock{
    public static final IntProperty AGE = IntProperty.of("age", 0, 5);
    public HeartShapedHerbCropBlock(Settings settings) {
        super(settings);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return DavesVibranium.HEART_SHAPED_HERB;
    }
    @Override
    public int getMaxAge() {
        return 5;
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
