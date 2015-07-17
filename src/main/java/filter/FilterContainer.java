package filter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by adam on 7/16/15.
 */

public class FilterContainer {
    Map<String, Set<String>> filters;

    public FilterContainer()
    {
        filters = new HashMap<String, Set<String>>();

    }

    //returns null on fail
    public Map<String, Filter> loadFilter(String filename)
    {

        String jsontext;
        try {
            jsontext = new Scanner(new File(filename)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        Response output = gson.fromJson(jsontext, Response.class);

        return output.response;

    }
}
