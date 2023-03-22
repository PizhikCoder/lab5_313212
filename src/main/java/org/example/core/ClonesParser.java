package org.example.core;

import org.example.core.models.MusicBand;
import org.example.core.models.MusicBandClone;

import java.util.ArrayDeque;

/**
 * Contains logic for work with model-clones.
 */
public class ClonesParser {
    /**
     * Convert models array to models-clones.
     * @param musicBands
     * @return
     */
    public static MusicBandClone[] toClones(MusicBand[] musicBands){
        MusicBandClone[] musicBandClones = new MusicBandClone[musicBands.length];
        for(int i = 0; i<musicBands.length; i++){
            musicBandClones[i] = new MusicBandClone(musicBands[i]);
        }
        return musicBandClones;
    }

    /**
     * Convert from models-clones to models.
     * @param musicBandsClones
     * @return
     */
    public static MusicBand[] toOrigs(MusicBandClone[] musicBandsClones){
        MusicBand[] musicBands = new MusicBand[musicBandsClones.length];
        for(int i = 0; i<musicBandsClones.length; i++){
            MusicBand musicBand = new MusicBand(musicBandsClones[i]);
            musicBands[i] = musicBand;
        }
        return musicBands;
    }

    /**
     * Convert models deque to models array.
     * @param deque
     * @return
     */
    public static MusicBand[] dequeToArray(ArrayDeque<MusicBand> deque){
        ArrayDeque<MusicBand> dequeClone = new ArrayDeque<>(deque);
        MusicBand[] array = new MusicBand[deque.size()];
        for(int i = 0; i < array.length; i++){
            array[i] = dequeClone.pop();
        }
        return array;
    }

    /**
     * Convert models array to models deque.
     * @param array
     * @return
     */
    public static ArrayDeque<MusicBand> arrayToDeque(MusicBand[] array){
        ArrayDeque<MusicBand> deque = new ArrayDeque<>();
        for(int i = 0; i < array.length; i++){
            deque.add(array[i]);
        }
        return deque;
    }
}
