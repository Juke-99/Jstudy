package study.list;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleListsTest {
    SimpleLists list = new SimpleLists();
    ArrayList<String> arrayList = new ArrayList<>();
    
    @Before
    public void setup() {
        arrayList.add("one");
        arrayList.add("two");
        arrayList.add("three");
    }
    
    /**
     * unmodifiableList of Collections class is read-only static method, so cannot use modifiable method 
     * such add and remove method.
     * */
    @Test
    public void createUnmodifiableListErrorTest() {
        List<String> unmodifyList = list.createUnmodifiableList(arrayList);
        
        assertThatThrownBy(() -> {
            unmodifyList.add("oooo");  
        }).isInstanceOf(UnsupportedOperationException.class);
        
        assertThatThrownBy(() -> {
            unmodifyList.remove(2);
        }).isInstanceOf(UnsupportedOperationException.class);
    }
}
