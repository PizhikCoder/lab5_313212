package org.example.core.managers;

import org.example.core.ClonesParser;
import org.example.core.Invoker;
import org.example.core.comparators.ModelsDefaultComparator;
import org.example.core.models.Coordinates;
import org.example.core.models.MusicBand;
import org.example.core.models.MusicGenre;
import org.example.core.models.Person;
import org.example.interfaces.IPrinter;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * Contains tools to manage your collection.
 */
public class ModelsManager {

    private ArrayList<Long> usedIDs;
    private ArrayDeque<MusicBand> models;
    private Invoker invoker;
    private IPrinter printer;
    private String creationDate;

    public ModelsManager(ArrayDeque<MusicBand> models, Invoker invoker){
        this.invoker = invoker;
        this.models = models;
        printer = invoker.getPrinter();
        getModelsIDs();
        creationDate = ZonedDateTime.now().toLocalDate().toString();
        printer.print("ModelsManager created.");
    }

    public String getCreationDate() {
        return creationDate;
    }

    private void getModelsIDs(){
        usedIDs = new ArrayList<>();
        for(MusicBand i : models){
            usedIDs.add(i.getId());
        }
    }

    public ArrayList<Long> getUsedIDs() {
        return usedIDs;
    }

    /**
     * Creates new model with random ID.
     * @param data data Map for model's constructor.
     * @return new model.
     */
    public MusicBand createModel(Map<String, Object> data){
        printer.print("Starting object creating...");
        return new MusicBand(
                generateId(),
                (String)data.get("name"),
                (Coordinates) data.get("coordinates"),
                (int)data.get("numberOfParticipants"),
                (MusicGenre)data.get("genre"),
                (Person) data.get("frontMan")
                );
    }

    /**
     * Creates new model with custom ID.
     * @param data data Map for model's constructor.
     * @param id Desired id for the model.
     * @return new model.
     */
    public MusicBand createModel(Map<String, Object> data, long id){
        printer.print("Starting object creating...");
        return new MusicBand(
                id,
                (String)data.get("name"),
                (Coordinates) data.get("coordinates"),
                (int)data.get("numberOfParticipants"),
                (MusicGenre)data.get("genre"),
                (Person) data.get("frontMan")
        );
    }

    /**
     * Add models ArrayDeque to the collection.
     * @param queue Models collection.
     */
    public void addModels(ArrayDeque<MusicBand> queue){
        models.addAll(queue);
        sort();
    }
    /**
     * Add model to the collection.
     * @param model Model object.
     */
    public void addModels(MusicBand model){
        models.add(model);
        sort();
    }

    /**
     * Get model from the collection by ID and recreate it.
     * @param id Model id.
     * @param data new model data.
     */
    public void updateModel(long id, HashMap<String, Object> data){
        MusicBand model = findModelById(id);
        model.setName((String)data.get("name"));
        model.setCoordinates((Coordinates) data.get("coordinates"));
        model.setNumberOfParticipants((int)data.get("numberOfParticipants"));
        model.setGenre((MusicGenre)data.get("genre"));
        model.setFrontMan((Person) data.get("frontMan"));
    }

    /**
     * Find model in the collection by id.
     * @param id model id.
     * @return object of model.
     */
    public MusicBand findModelById(Long id){
        if (models.size()==0){
            printer.print("Collection is empty!");
            return null;
        }
        for(MusicBand musicBand : models){
            if (musicBand.getId()==id){
                return musicBand;
            }
        }
        printer.print("Can not find element with this id.");
        return null;
    }

    /**
     * create new id for creating/loading model.
     * @return new ID.
     */
    private long generateId(){
        Random rnd = new Random();
        long id = rnd.nextLong(Long.MAX_VALUE);
        while(usedIDs.contains(id)){
            id = rnd.nextLong();
        }
        usedIDs.add(id);
        return id;
    }

    /**
     * remove all elements from the collection.
     */
    public void removeAll(){
        for(MusicBand musicBand: models){
            removeById(musicBand.getId());
        }
    }

    /**
     * Makes the default sorting.
     */
    public void sort(){
        MusicBand[] modelsArr = ClonesParser.dequeToArray(models);
        Arrays.sort(modelsArr, new ModelsDefaultComparator());
        models = ClonesParser.arrayToDeque(modelsArr);
    }

    /**
     * Remove model from the collection by model id.
     * @param id model id.
     */
    public void removeById(long id){
        MusicBand musicBand = findModelById(id);
        if (musicBand != null){
            models.remove(musicBand);
            printer.print(String.format("Model %s successfully removed.", id));
        }
        else printer.print("Model does not exist!");
    }

    public ArrayDeque<MusicBand> getModels() {
        return models;
    }
}
