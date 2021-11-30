package pl.grzywniak.xcraftrayx.customwither;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config
{
	private final static File directory = new File("plugins/CustomWither/");
	private final static File config = new File("plugins/CustomWither/Config.yml"); //Time in lobby to start game
	
	//Config variables
	static double baseHP;
	static double thunderAtMinHP;
	static double thunderAtMaxHP;
	static double startRespMobsAtMinHP;
	static double startRespMobsAtMaxHP;
	
	static void createDefaultFile()
    {
        if (!directory.exists())
        {
            directory.mkdirs();
        }
        
        if (!config.exists())
        {
            try
            {
                config.createNewFile();
               
                BufferedWriter writer = new BufferedWriter(new FileWriter(config));
               
                writer.write("Config:");                      writer.newLine();
                writer.write("  baseHP: 1500");               writer.newLine();
                writer.write("  thunderAtMinHP: 500");        writer.newLine();
                writer.write("  thunderAtMaxHP: 1000");       writer.newLine();
                writer.write("  startRespMobsAtMinHP: 0");    writer.newLine();
                writer.write("  startRespMobsAtMaxHP: 1000"); writer.newLine();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        getVariables();
    }
	
	private static void getVariables()
	{
		FileConfiguration conf = YamlConfiguration.loadConfiguration(config);
		
		baseHP = conf.getDouble("Config.baseHP");
		thunderAtMinHP = conf.getDouble("Config.thunderAtMinHP");
		thunderAtMaxHP = conf.getDouble("Config.thunderAtMaxHP");
		startRespMobsAtMinHP = conf.getDouble("Config.startRespMobsAtMinHP");
		startRespMobsAtMaxHP = conf.getDouble("Config.startRespMobsAtMaxHP");
	}
}