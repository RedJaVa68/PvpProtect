package com.crimsonempires.RedJaVa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ConfigLoader {
	String dirPath = "plugins" + File.separator + "PvpProtect";
	String cmdPath = dirPath + File.separator + "cmdban.txt";
	String arenaPath = dirPath + File.separator + "arenas.txt";
	
	public ConfigLoader(){
		
	}
	public void initConfigs(){
		File dirFile = new File(this.dirPath);
		if(!dirFile.exists()){
			try{
				dirFile.mkdirs();
			}catch(Exception e){}
		}
		File cmdFile = new File(this.cmdPath);
		if(!cmdFile.exists()){
			try{
				cmdFile.createNewFile();
			}catch(Exception e){}
		}
		File arenaFile = new File(this.arenaPath);
		if(!arenaFile.exists()){
			try{
				arenaFile.createNewFile();
			}catch(Exception e){}
		}
	}
	public List<String> getCommandConfig(){
		return getConfig(this.cmdPath);
	}
	public List<String> getArenaConfig(){
		return getConfig(this.arenaPath);
	}
	public List<String> getConfig(String path){
		initConfigs();
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try{
			reader = new BufferedReader(new FileReader(path));
			String line;
			while((line = reader.readLine()) != null){
				list.add(line);
			}
		}catch(Exception e){}
		finally{
			try{
				reader.close();
			}catch(Exception e){}
		}
		return list;
	}
	public void writeToConfig(String[] text, String path){
		initConfigs();
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(path));
			for(int i = 0; i < text.length; i ++){
				if(i != 0)
					writer.newLine();
				writer.write(text[i]);
			}
		}
		catch(Exception e){}
		finally{
			try{
				writer.close();
			}catch(Exception e){}
		}
	}
	public String getArenaPath(){
		return this.arenaPath;
	}
	public String getCommandPath(){
		return this.cmdPath;
	}
}
