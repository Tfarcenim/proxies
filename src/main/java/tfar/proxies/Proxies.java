package tfar.proxies;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import tfar.proxies.item.EntityProxyItem;
import tfar.proxies.item.ProxyBlockItem;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Proxies.MODID)
public class Proxies
{
    // Directly reference a log4j logger.

    public static final String MODID = "proxies";

    public Proxies() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        bus.addListener(this::setup);
        // Register the doClientStuff method for modloading
        bus.addListener(this::doClientStuff);
        bus.addGenericListener(Block.class,this::blocks);
        bus.addGenericListener(Item.class,this::items);
        bus.addGenericListener(TileEntityType.class,this::blockentities);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void blocks(RegistryEvent.Register<Block> e) {
        register(ModBlocks.ITEM_PROXY,"item_proxy",e.getRegistry());
        register(ModBlocks.ENTITY_PROXY,"entity_proxy",e.getRegistry());
    }

    private void items(RegistryEvent.Register<Item> e) {
        register(new ProxyBlockItem(ModBlocks.ITEM_PROXY,new Item.Properties().group(ItemGroup.REDSTONE)),"item_proxy",e.getRegistry());
        register(new EntityProxyItem(ModBlocks.ENTITY_PROXY,new Item.Properties().group(ItemGroup.REDSTONE)),"entity_proxy",e.getRegistry());
    }

    private void blockentities(RegistryEvent.Register<TileEntityType<?>> e) {
        register(ModBlockEntites.ITEM_PROXY,"item_proxy",e.getRegistry());
        register(ModBlockEntites.ENTITY_PROXY,"entity_proxy",e.getRegistry());
    }

    private static <T extends IForgeRegistryEntry<T>> void register(T obj, String name, IForgeRegistry<T> registry) {
        registry.register(obj.setRegistryName(new ResourceLocation(MODID, name)));
    }

}
