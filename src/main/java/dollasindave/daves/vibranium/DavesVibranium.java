package dollasindave.daves.vibranium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dollasindave.daves.vibranium.block.custom.HeartShapedHerbCropBlock;

public class DavesVibranium implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "daves_vibranium";
	public static final Logger DAVESVIBRANIUM = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup WAKANDA_MOD = FabricItemGroupBuilder.build(
		new Identifier("davesvibranium", "general"),
		() -> new ItemStack(DavesVibranium.VIBRANIUM_INGOT));

	public static final Item VIBRANIUM_INGOT = new Item(new Item.Settings().group(DavesVibranium.WAKANDA_MOD));

	public static final Item VIBRANIUM_CAPSULE = new Item(new Item.Settings().group(DavesVibranium.WAKANDA_MOD));

	public static final Item VIBRANIUM_SHARD = new Item(new Item.Settings().group(DavesVibranium.WAKANDA_MOD));

	public static final Block VIBRANIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(50F, 1200F).sounds(BlockSoundGroup.METAL).requiresTool());

	public static final Block VIBRANIUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(5F,8.0F).sounds(BlockSoundGroup.STONE).requiresTool());

	public static final ArmorMaterial VIBRANIUM_ARMOR = new VibraniumArmor();

	public static final Block VIBRANIUM_WINDOW = new Block(FabricBlockSettings.copy(Blocks.GLASS).strength(25F, 1200F).nonOpaque().requiresTool());

	public static final Block HEART_SHAPED_HERB = new HeartShapedHerbCropBlock(FabricBlockSettings.copy(Blocks.WHEAT).strength(0F, 0F).nonOpaque());

	public static final Item HEART_SOUP = new Item(new Item.Settings().group(DavesVibranium.WAKANDA_MOD)
	.food(new FoodComponent.Builder().hunger(3).saturationModifier(1F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 1), 1).build()));
		
	private static ConfiguredFeature<?, ?> OVERWORLD_VIBRANIUM_ORE_CONFIGURED_FEATURE = new ConfiguredFeature
      (Feature.ORE, new OreFeatureConfig(
          OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
          DavesVibranium.VIBRANIUM_ORE.getDefaultState(),
          3)); // vein size
 
  public static PlacedFeature OVERWORLD_VIBRANIUM_ORE_PLACED_FEATURE = new PlacedFeature(
      RegistryEntry.of(OVERWORLD_VIBRANIUM_ORE_CONFIGURED_FEATURE),
      Arrays.asList(
          CountPlacementModifier.of(1), // number of veins per chunk
          SquarePlacementModifier.of(), // spreading horizontally
          HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-40))
      )); // height

	@Override
	public void onInitialize() {
		//oreGeneration
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
        new Identifier("daves_vibranium", "overworld_vibranium_ore"), OVERWORLD_VIBRANIUM_ORE_CONFIGURED_FEATURE);
    Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("daves_vibranium", "overworld_vibranium_ore"),
        OVERWORLD_VIBRANIUM_ORE_PLACED_FEATURE);
    BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU), GenerationStep.Feature.UNDERGROUND_ORES,
        RegistryKey.of(Registry.PLACED_FEATURE_KEY,
            new Identifier("daves_vibranium", "overworld_vibranium_ore")));
			//repalce .foundInOverworld() with .includeByKey(BiomeKeys.BIOME)
			//dont forget about vertical biomes
		//blocks
		Registry.register(Registry.BLOCK, new Identifier("davesvibranium", "vibranium_block"), VIBRANIUM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_block"), new BlockItem(VIBRANIUM_BLOCK, new Item.Settings().group(DavesVibranium.WAKANDA_MOD)));

		Registry.register(Registry.BLOCK, new Identifier("davesvibranium", "vibranium_ore"), VIBRANIUM_ORE);
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_ore"), new BlockItem(VIBRANIUM_ORE, new Item.Settings().group(DavesVibranium.WAKANDA_MOD)));

		Registry.register(Registry.BLOCK, new Identifier("davesvibranium", "vibranium_window"), VIBRANIUM_WINDOW);
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_window"), new BlockItem(VIBRANIUM_WINDOW, new Item.Settings().group(DavesVibranium.WAKANDA_MOD)));

		Registry.register(Registry.BLOCK, new Identifier("davesvibranium", "heart_shaped_herb"), HEART_SHAPED_HERB);
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "heart_shaped_herb"), new AliasedBlockItem(HEART_SHAPED_HERB, new Item.Settings().group(DavesVibranium.WAKANDA_MOD)));
		//items
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_ingot"), VIBRANIUM_INGOT);

		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_capsule"), VIBRANIUM_CAPSULE);

		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_shard"), VIBRANIUM_SHARD);

		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "heart_soup"), HEART_SOUP);
		//armor
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_helmet"), new BaseArmor(VIBRANIUM_ARMOR, EquipmentSlot.HEAD));
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_chestplate"), new BaseArmor(VIBRANIUM_ARMOR, EquipmentSlot.CHEST));
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_leggings"), new BaseArmor(VIBRANIUM_ARMOR, EquipmentSlot.LEGS));
		Registry.register(Registry.ITEM, new Identifier("davesvibranium", "vibranium_boots"), new BaseArmor(VIBRANIUM_ARMOR, EquipmentSlot.FEET));
	}
}
