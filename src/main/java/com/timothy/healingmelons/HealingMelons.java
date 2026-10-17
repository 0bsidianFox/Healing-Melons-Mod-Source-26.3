package com.timothy.healingmelons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealingMelons implements ModInitializer {
    public static final String MOD_ID = "healing-melons";
    public static final Logger LOGGER =
            LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(
                    Items.GLISTERING_MELON_SLICE,
                    builder -> {
                        builder.set(
                                DataComponents.FOOD,
                                new FoodProperties.Builder()
                                        .nutrition(2)
                                        .saturationModifier(0.3F)
                                        .alwaysEdible()
                                        .build()
                        );

                        builder.set(
                                DataComponents.CONSUMABLE,
                                Consumables.defaultFood()
                                        .onConsume(
                                                new ApplyStatusEffectsConsumeEffect(
                                                        new MobEffectInstance(
                                                                MobEffects.REGENERATION,
                                                                100,
                                                                0
                                                        ),
                                                        1.0F
                                                )
                                        )
                                        .build()
                        );
                    }
            );
        });

        LOGGER.info("Healing Melons initialized.");
    }
}