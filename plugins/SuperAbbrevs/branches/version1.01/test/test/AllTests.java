package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import superabbrevs.collections.IndexedSortedSetTest;
import superabbrevs.gui.controls.ModesComboBoxTest;
import superabbrevs.model.AbbrevTest;
import trie.BackwardsTrieTest;

@RunWith(Suite.class)
@SuiteClasses({
  IndexedSortedSetTest.class,
  ModesComboBoxTest.class,
  AbbrevTest.class,
  BackwardsTrieTest.class
})
public class AllTests {

}
