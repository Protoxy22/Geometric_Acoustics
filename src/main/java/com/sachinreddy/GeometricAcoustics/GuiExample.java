package com.sachinreddy.GeometricAcoustics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiExample extends Gui
{
	private final ResourceLocation bar = new ResourceLocation(GeometricAcousticsCore.modid, "textures/gui/hpbar.png");
	private final int tex_width = 102, tex_height = 8, bar_width = 100, bar_height = 6;
	
	String guiText = "Hello world!";
	
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.renderEngine.bindTexture(bar);
            float oneUnit = (float)bar_width / mc.thePlayer.getMaxHealth();
            int currentWidth = (int)(oneUnit * mc.thePlayer.getHealth());
            drawTexturedModalRect(0, 0, 0, 0, tex_width, tex_height);
            drawTexturedModalRect(1, 0, 1, tex_height, currentWidth, tex_height);
            //
			ScaledResolution scaled = new ScaledResolution(mc);
			int width = scaled.getScaledWidth();
			int height = scaled.getScaledHeight();
			drawCenteredString(mc.fontRendererObj, guiText, width/2, height/2, Integer.parseInt("FFAA00", 16));
        }
    }
	
}
