//? fabric {
package com.ordana.would.fabric.data.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import com.ordana.would.fabric.ModCompostable;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.util.ExtraCodecs;

public class NeoForgeDataMapProvider implements DataProvider {
    private final FabricPackOutput output;

    public NeoForgeDataMapProvider(FabricPackOutput output) {
        this.output = output;
    }

    private static final List<Consumer<BiConsumer<String, JsonElement>>> SUBMITTERS = List.of(
            NeoForgeDataMapProvider::compostables
    );

    private static void compostables(BiConsumer<String, JsonElement> consumer) {
        ModCompostable.register();
        Codec<Float> chance = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").codec();
        Codec<Map<Identifier, Float>> mapCodec = Codec.unboundedMap(Identifier.CODEC, chance).fieldOf("values").codec();
        consumer.accept("compostables", mapCodec.encodeStart(JsonOps.INSTANCE, ModCompostable.COMPOSTABLES).getOrThrow());
    }


    private static void strippables(BiConsumer<String, JsonElement> consumer) {
        ModCompostable.register();
        Codec<Float> chance = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").codec();
        Codec<Map<Identifier, Float>> mapCodec = Codec.unboundedMap(Identifier.CODEC, chance).fieldOf("values").codec();
        consumer.accept("compostables", mapCodec.encodeStart(JsonOps.INSTANCE, ModCompostable.COMPOSTABLES).getOrThrow());
    }

    private static void collect(BiConsumer<String, JsonElement> consumer) {
        for (final var submitter : SUBMITTERS) {
            submitter.accept(consumer);
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        final var elements = new HashMap<String, JsonElement>();

        collect((name, elem) -> {
            if (elements.put(name, elem) != null) {
                throw new IllegalArgumentException("An element with name " + name + " has already been added.");
            }
        });

        final var paths = this.output.createPathProvider(PackOutput.Target.DATA_PACK, "data_maps/item");

        return CompletableFuture.allOf(
                elements.entrySet().stream().map(x ->
                                DataProvider.saveStable(
                                        cache,
                                        x.getValue(),
                                        paths.json(Identifier.fromNamespaceAndPath("neoforge", x.getKey()))
                                )
                        )
                        .toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public String getName() {
        return "Would Compostables";
    }
}
//?}