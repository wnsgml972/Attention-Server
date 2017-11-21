package com.HIFLY.Attention.voiceCore;


import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import com.HIFLY.Attention.debuger.Debuger;


public class VoiceRecvThread extends Thread {
	private final int SERVER_UDP_PORT;
	
	private DatagramSocket datagramSocket;		
	private byte audioBuffer[];
	private AudioFormat audioFormat;
	private DataLine.Info info;
	private SourceDataLine voiceSourceDataLine = null;
	
	public VoiceRecvThread(int bufsize, int SERVER_UDP_PORT) {
		this.SERVER_UDP_PORT = SERVER_UDP_PORT;
		System.out.println("udp port : " + SERVER_UDP_PORT);
		audioBuffer = new byte[bufsize];
		try {
			datagramSocket = new DatagramSocket(SERVER_UDP_PORT);	
			
		} catch (Exception e) {
			Debuger.printError(e);
		}
	}

	public void run() {		
		try {
			audioFormat = new AudioFormat(44100, 16, 1, true, false);
			info = new DataLine.Info(SourceDataLine.class, audioFormat);
			voiceSourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
			// prepare audio output
			voiceSourceDataLine.open(audioFormat, audioBuffer.length + 100);
			voiceSourceDataLine.start();
		} catch (LineUnavailableException e) {			
			voiceSourceDataLine.drain();
			voiceSourceDataLine.stop();
			voiceSourceDataLine.close();
			Debuger.printError(e);
			return;
		}
		while (true) {
			try {
				datagramSocket.receive(new DatagramPacket(audioBuffer, audioBuffer.length));
				// output wave form repeatedly
				
				voiceSourceDataLine.write(audioBuffer, 0, audioBuffer.length);
				
			} catch (Exception e) {
				// shut down audio
				voiceSourceDataLine.drain();
				voiceSourceDataLine.stop();
				voiceSourceDataLine.close();				
				Debuger.printError(e);				
				return;
			}
		}
	}
}
