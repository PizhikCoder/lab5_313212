package org.example.commands;

import org.example.core.Invoker;
import org.example.core.exceptions.CommandParamsException;
import org.example.core.exceptions.FileAccessException;
import org.example.core.exceptions.FileDoesNotExist;
import org.example.core.exceptions.RecursionLimitException;
import org.example.core.models.Coordinates;
import org.example.core.models.Location;
import org.example.core.models.MusicBand;

import java.util.HashMap;
import java.util.Map;
/**
 * The class contains an implementation of the group_counting_by_coordinates command
 */

public class GroupCountingByCoordinatesCommand  extends Command{
    private Invoker invoker;

    public GroupCountingByCoordinatesCommand(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public String execute(String... args) throws RecursionLimitException, FileAccessException, CommandParamsException, FileDoesNotExist {
        HashMap<Coordinates, Integer> counter = new HashMap<>();
        if (invoker.getModelsManager().getModels().size()==0){
            return "Collection is empty!";
        }
        for(MusicBand musicBand : invoker.getModelsManager().getModels()){
            if (counter.containsKey(musicBand.getCoordinates())){
                counter.put(musicBand.getCoordinates(), counter.get(musicBand.getCoordinates()) + Integer.valueOf(1));
            }
            else{
                counter.put(musicBand.getCoordinates(), Integer.valueOf(1));
            }
        }

        for (Map.Entry<Coordinates, Integer> entry : counter.entrySet()){
            invoker.getPrinter().print(String.format("Group of Coordinates: \n%s\nCount of elements: %s",
                    entry.getKey().toString(), counter.get(entry.getKey())));
        }

        return "Counting ended...";
    }

    @Override
    public String getCommandInfo() {
        return "Command \"group_counting_by_coordinates\": This command allows you to group the items in the collection by coordinates field, displaying the number of items in each group.";
    }
}
