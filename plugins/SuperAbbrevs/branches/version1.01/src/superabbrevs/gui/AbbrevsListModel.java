package superabbrevs.gui;

import java.util.Set;

import javax.swing.AbstractListModel;

import superabbrevs.collections.IndexedSortedSet;
import superabbrevs.model.Abbreviation;

/**
 * @author sune
 * Created on 28. januar 2007, 00:45
 *
 */
public class AbbrevsListModel extends AbstractListModel {
    
    /**
     * Creates a new instance of AbbrevsModel
     */
    public AbbrevsListModel(Set<Abbreviation> abbrevs) {
    	IndexedSortedSet<Abbreviation> indexedSortedSet = new IndexedSortedSet<Abbreviation>();
    	indexedSortedSet.addAll(abbrevs);
        this.abbrevs = indexedSortedSet;
    }
    
    public Object getElementAt(int index) {
        return abbrevs.get(index);
    }
    
    /**
     * Returns the number of abbreviations in the model.
     * @return the number of abbreviations in the model.
     */
    public int getSize() {
        return abbrevs.size();
    }
    
    public int add(String name) {
    	Abbreviation abbrev = new Abbreviation(name,"","");
        abbrevs.add(abbrev);
        int index = abbrevs.size()-1;
        fireIntervalAdded(this,index,index);
        return abbrevs.indexOf(abbrev);
    }
    
    /**
     * Returns the abbreviation at the specified index.
     * @param selection The index of the wanted abbreviation.
     * @return The abbreviation at the specified index.
     */
    public Abbreviation get(int selection) {
        return abbrevs.get(selection);
    }
    
    int remove(int selection) {
        abbrevs.remove(selection);
        fireIntervalRemoved(this,selection,selection);
        int newSelection = selection < abbrevs.size() ? 
            selection : selection - 1;
        return newSelection;
    }
    
    int update(int selection, String name) {
        Abbreviation selectedAbbrev = abbrevs.get(selection);
        if (!selectedAbbrev.getName().equals(name)) {
            selectedAbbrev.setName(name);
            return abbrevs.indexOf(selectedAbbrev);
        } else {
            return selection;
        }
    }
    
    public IndexedSortedSet<Abbreviation> getAbbrevs(){
        return abbrevs;
    }
    
    public boolean unsorted = false;
    IndexedSortedSet<Abbreviation> abbrevs;
}
