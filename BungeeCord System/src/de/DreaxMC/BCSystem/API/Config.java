package de.DreaxMC.BCSystem.API;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Config {

	File f = null;
	
	public Config(File f) {
		this.f = f;
	}
	
	private static Configuration config = null;
	
	public void saveConfig() {
		try {
			if (!f.exists() || f.isDirectory()) {
				f.mkdirs();
				f.delete();
				f.createNewFile();
			}
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(
					config, f);
		} catch (IOException e) {
			System.out.println("Config can't be Saved");
		}

	}

	public Configuration getConfig() {

		try {
			if (!f.exists() || f.isDirectory()) {
				f.mkdirs();
				f.delete();
				f.createNewFile();
			}

			config = ConfigurationProvider.getProvider(YamlConfiguration.class)
					.load(f);

			return config;
		} catch (IOException e) {
			return null;
		}
	}
	
}
