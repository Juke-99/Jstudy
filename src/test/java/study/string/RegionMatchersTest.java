package study.string;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RegionMatchersTest {
    String compareStr, people, peoPle;
    
    
    @Before
    public void setUp() {
        compareStr = "What's a people.";
        people = "people";
        peoPle = "peoPle";
    }
    
    @Test
    public void regionMatchersTest() {
        assertThat(compareStr.regionMatches(true, 9, people, 0, 6)).isEqualTo(true);
        assertThat(compareStr.regionMatches(true, 9, peoPle, 0, 6)).isEqualTo(true);
        assertThat(compareStr.regionMatches(false, 9, peoPle, 0, 6)).isEqualTo(false);
    }
}
