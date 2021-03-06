package com.sachinreddy.GeometricAcoustics;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.EFX10;

public class ReverbParameters 
{
	// min: 0.1f 	max: 10.0f
	public float decayTime;				
	public float density;
	public float diffusion;
	public float gain;
	public float gainHF;
	public float decayHFRatio;
	public float reflectionsGain;
	public float reflectionsDelay;
	public float lateReverbGain;
	public float lateReverbDelay;
	public float airAbsorptionGainHF;
	public float roomRolloffFactor;
	
	public static ReverbParameters getReverb0()
	{
		ReverbParameters r = new ReverbParameters();
		r.decayTime = 0.15f;
		r.density = 0.0f;
		r.diffusion = 1.0f;
		r.gain = 0.2f;
		r.gainHF = 0.99f;
		r.decayHFRatio = 0.6f;
		r.reflectionsGain = 2.5f;
		r.reflectionsDelay = 0.001f;
		r.lateReverbGain = 1.26f;
		r.lateReverbDelay = 0.011f;
		r.airAbsorptionGainHF = 0.994f;
		r.roomRolloffFactor = 0.16f;
		return r;
	}
	
	public static ReverbParameters getReverb1()
	{
		ReverbParameters r = new ReverbParameters();
		r.decayTime = 0.55f;
		r.density = 0.0f;
		r.diffusion = 1.0f;
		r.gain = 0.3f;
		r.gainHF = 0.99f;
		r.decayHFRatio = 0.7f;
		r.reflectionsGain = 0.2f;
		r.reflectionsDelay = 0.015f;
		r.lateReverbGain = 1.26f;
		r.lateReverbDelay = 0.011f;
		r.airAbsorptionGainHF = 0.994f;
		r.roomRolloffFactor = 0.15f;
		return r;
	}
	
	public static ReverbParameters getReverb2()
	{
		ReverbParameters r = new ReverbParameters();
		r.decayTime = 1.68f;
		r.density = 0.1f;
		r.diffusion = 1.0f;
		r.gain = 0.5f;
		r.gainHF = 0.99f;
		r.decayHFRatio = 0.7f;
		r.reflectionsGain = 0.0f;
		r.reflectionsDelay = 0.021f;
		r.lateReverbGain = 1.26f;
		r.lateReverbDelay = 0.021f;
		r.airAbsorptionGainHF = 0.994f;
		r.roomRolloffFactor = 0.13f;
		return r;
	}
	
	public static ReverbParameters getReverb3()
	{
		ReverbParameters r = new ReverbParameters();
		r.decayTime = 4.142f;
		r.density = 0.5f;
		r.diffusion = 1.0f;
		r.gain = 0.4f;
		r.gainHF = 0.89f;
		r.decayHFRatio = 0.7f;
		r.reflectionsGain = 0.0f;
		r.reflectionsDelay = 0.025f;
		r.lateReverbGain = 1.26f;
		r.lateReverbDelay = 0.021f;
		r.airAbsorptionGainHF = 0.994f;
		r.roomRolloffFactor = 0.11f;
		return r;
	}
}