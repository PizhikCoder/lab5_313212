package org.example.core.comparators;

import org.example.core.models.MusicBand;

import java.util.Comparator;

/**
 * Compares elements by id.
 * Needed for default sorting.
 */
public class ModelsDefaultComparator implements Comparator<MusicBand> {

    @Override
    public int compare(MusicBand o1, MusicBand o2) {
        if ((o1).getId()-(o2).getId()<0){
            return -1;

        }
        if ((o1).getId()-(o2).getId()==0) {
            return 0;
        }
        return 1;
    }
}
