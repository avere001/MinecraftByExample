package minecraftbyexample.mbe30_inventory_basic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * User: brandon3055
 * Date: 06/01/2015
 *
 * GuiInventoryBasic is a simple gui that does nothing but draw a background image and a line of text on the screen
 * everything else is handled by the vanilla container code
 */
@SideOnly(Side.CLIENT)
public class GuiInventoryBasic extends GuiContainer {

	// This is the resource location for the background image for the GUI
	private static final ResourceLocation texture = new ResourceLocation("minecraftbyexample", "textures/gui/chest.png");
	private TileEntityInventoryBasic tileEntityInventoryBasic;

	static int test = 1;

	public GuiInventoryBasic(InventoryPlayer invPlayer, TileEntityInventoryBasic tile) {
		super(new ContainerBasic(invPlayer, tile));
		tileEntityInventoryBasic = tile;
		// Set the width and height of the gui.  Should match the size of the texture!
		xSize = 176;
		ySize = 221;
	}

	// draw the background for the GUI - rendered first
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture of our custom container
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		drawRect(5, 5, 200, 200, 0xFF00FFFF);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
	// renders relative to the top left corner of the background
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
		fontRendererObj.drawString(tileEntityInventoryBasic.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
		//Minecraft.getMinecraft().thePlayer.sendChatMessage(Integer.toString(test));

		test++;
	}
}
