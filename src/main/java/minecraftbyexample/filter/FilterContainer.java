package minecraftbyexample.filter;

import com.google.gson.Gson;
import minecraftbyexample.MinecraftByExample;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by adam on 7/16/15.
 */

public class FilterContainer {

    static Map<String, Set<String>> filters = new HashMap<String, Set<String>>();
    static List<String> files = Arrays.asList("filter_1.json");

    //load filters
    public void loadFilters()
    {
        createDefaultFilters();

        System.out.println(loadFilter("filter_1.json").name);
        //System.out.println(loadFilter("filter_2.json").toString());


    }

    public void createDefaultFilters()
    {

        for (String file : files) {
            Path file_path = Paths.get(Loader.instance().getConfigDir().getPath(), MinecraftByExample.MODID, file);

            //FIXME: remove true before releasing
            if(true || !file_path.toFile().exists()) {

                InputStream resourceStream;

                // get stream from resource
                try {
                    ResourceLocation resourceLocation = new ResourceLocation(MinecraftByExample.MODID, "config/filters/" + file);
                    resourceStream = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }

                //copy the file
                try {
                    Files.copy(resourceStream, file_path);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    //returns null on fail
    public Filter loadFilter(String filename)
    {
        String base_dir = Loader.instance().getConfigDir() + "/" + MinecraftByExample.MODID + "/";

        String jsontext;
        try {
            jsontext = new Scanner(new File(base_dir + filename)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Filter output = gson.fromJson(jsontext, Filter.class);

        return output;
    }
}
