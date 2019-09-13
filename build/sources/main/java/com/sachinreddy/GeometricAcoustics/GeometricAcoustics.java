package com.sachinreddy.GeometricAcoustics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;
import org.lwjgl.openal.EFX10;

import paulscode.sound.SoundSystemConfig;

public class GeometricAcoustics 
{		
	private static final String logPrefix = "[GEOMETRIC ACOUSTICS]";
	//
	private static int auxFXSlot0;
	private static int auxFXSlot1;
	private static int auxFXSlot2;
	private static int auxFXSlot3;
	//
	private static int reverb0;
	private static int reverb1;
	private static int reverb2;
	private static int reverb3;
	//
	private static SoundCategory lastSoundCategory;
	private static String lastSoundName;
		
	// ------------------------------------------------- //
	
	public static void initialize()
	{
		log("Initializing Geometric Acoustics...");
		setupReverb();
	}
	
	private static void setupReverb()
	{
		ALCcontext currentContext = ALC10.alcGetCurrentContext();
		ALCdevice currentDevice = ALC10.alcGetContextsDevice(currentContext);
		
		if (ALC10.alcIsExtensionPresent(currentDevice, "ALC_EXT_EFX"))
			log("EFX Extension recognized.");
		else
			return;
		
		// Create auxiliary effect slots
		auxFXSlot0 = EFX10.alGenAuxiliaryEffectSlots();
		EFX10.alAuxiliaryEffectSloti(auxFXSlot0, EFX10.AL_EFFECTSLOT_AUXILIARY_SEND_AUTO, AL10.AL_TRUE);
		auxFXSlot1 = EFX10.alGenAuxiliaryEffectSlots();
		EFX10.alAuxiliaryEffectSloti(auxFXSlot1, EFX10.AL_EFFECTSLOT_AUXILIARY_SEND_AUTO, AL10.AL_TRUE);
		auxFXSlot2 = EFX10.alGenAuxiliaryEffectSlots();
		EFX10.alAuxiliaryEffectSloti(auxFXSlot2, EFX10.AL_EFFECTSLOT_AUXILIARY_SEND_AUTO, AL10.AL_TRUE);
		auxFXSlot3 = EFX10.alGenAuxiliaryEffectSlots();
		EFX10.alAuxiliaryEffectSloti(auxFXSlot3, EFX10.AL_EFFECTSLOT_AUXILIARY_SEND_AUTO, AL10.AL_TRUE);	
		
		// Create effect objects
		reverb0 = EFX10.alGenEffects();
		EFX10.alEffecti(reverb0, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_EAXREVERB);
		reverb1 = EFX10.alGenEffects();
		EFX10.alEffecti(reverb1, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_EAXREVERB);
		reverb2 = EFX10.alGenEffects();
		EFX10.alEffecti(reverb2, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_EAXREVERB);
		reverb3 = EFX10.alGenEffects();
		EFX10.alEffecti(reverb3, EFX10.AL_EFFECT_TYPE, EFX10.AL_EFFECT_EAXREVERB);
		
		// Set the reverb parameters and apply them to the effect and effectslot
		setReverbParameters(ReverbParameters.getReverb0(), auxFXSlot0, reverb0);	
		setReverbParameters(ReverbParameters.getReverb1(), auxFXSlot1, reverb1);		
		setReverbParameters(ReverbParameters.getReverb2(), auxFXSlot2, reverb2);	
		setReverbParameters(ReverbParameters.getReverb3(), auxFXSlot3, reverb3);
		
		log("Reverb parameters setup.");
	}
	
	// ------------------------------------------------- //
	
	public static void setLastSoundCategory(SoundCategory sc)
	{
		lastSoundCategory = sc;
	}
	
	public static void setLastSoundName(String name)
	{
		lastSoundName = name;
	}
	
	// ------------------------------------------------- //
	
	public static void onPlaySound(float posX, float posY, float posZ, int sourceID)
	{
		log("[SOUND PLAYED]: Source ID: " + sourceID + " | (" + posX + ", " + posY + ", " + posZ + ") | Sound category: " + lastSoundCategory.toString() + " | Sound name: " + lastSoundName);
	}
	
	// ------------------------------------------------- //
	
	protected static void setReverbParameters(ReverbParameters r, int auxFXSlot, int reverbSlot)
	{
		//Set default parameters
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_DECAY_TIME, r.decayTime);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_DENSITY, r.density);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_DIFFUSION, r.diffusion);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_GAIN, r.gain);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_GAINHF, r.gainHF);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_DECAY_HFRATIO, r.decayHFRatio);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_REFLECTIONS_GAIN, r.reflectionsGain);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_REFLECTIONS_DELAY, r.reflectionsDelay);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_LATE_REVERB_GAIN, r.lateReverbGain);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_LATE_REVERB_DELAY, r.lateReverbDelay);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_AIR_ABSORPTION_GAINHF, r.airAbsorptionGainHF);
		EFX10.alEffectf(reverbSlot, EFX10.AL_REVERB_ROOM_ROLLOFF_FACTOR, r.roomRolloffFactor);
		EFX10.alEffecti(reverbSlot, EFX10.AL_REVERB_DECAY_HFLIMIT, AL10.AL_TRUE);
		
		//Attach updated effect object
		EFX10.alAuxiliaryEffectSloti(auxFXSlot, EFX10.AL_EFFECTSLOT_EFFECT, reverbSlot);
	}
	
	// ------------------------------------------------- //
	
	protected static void log(String message)
	{
		System.out.println(logPrefix + ": " + message);
	}
	
}
