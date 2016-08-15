import com.serzhanto.Solution;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.*;

public class SolutionTest {
    private static List<String> listTree;
    private static Solution list;

    @BeforeClass
    public static void init() throws CloneNotSupportedException {
        listTree = new Solution();
        for (int i = 1; i < 16; i++) {
            listTree.add(String.valueOf(i));
        }
        list = ((Solution) listTree).clone();

        list.remove("2");
        list.remove("9");
        assertEquals("[1, 3, 4, 7, 8, 10, 15]", list.toString());

        list.add("16");
        list.add("17");
        list.add("18");
        list.add("19");
        list.add("20");
        assertEquals("[1, 3, 4, 7, 8, 10, 15, 16, 17, 18, 19, 20]", list.toString());

        list.remove("18");
        list.remove("20");
        assertEquals("[1, 3, 4, 7, 8, 10, 15, 16, 17, 19]",list.toString());

        list.add("21");
        list.add("22");
        list.add("23");
        list.add("24");
        list.add("25");
        list.add("26");
        list.add("27");
        list.add("28");
        list.add("29");
        list.add("30");
        list.add("31");
        list.add("32");
        assertEquals("[1, 3, 4, 7, 8, 10, 15, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32]", list.toString());

    }

    @Test
    public void getParentTest(){
        assertEquals("1", list.getParent("3"));
        assertEquals("1", list.getParent("4"));
        assertEquals("3", list.getParent("8"));
        assertNull(list.getParent(null));
        assertEquals("7", list.getParent("15"));
        assertEquals("7", list.getParent("16"));
        assertEquals("4", list.getParent("10"));
        assertEquals("8", list.getParent("17"));
        assertEquals("10", list.getParent("19"));
        assertEquals("10", list.getParent("21"));
        assertEquals("15", list.getParent("22"));
        assertEquals("15", list.getParent("23"));
        assertEquals("16", list.getParent("24"));
        assertEquals("16", list.getParent("25"));
        assertEquals("17", list.getParent("26"));
        assertEquals("17", list.getParent("27"));
        assertEquals("19", list.getParent("28"));
        assertEquals("19", list.getParent("29"));
        assertEquals("21", list.getParent("30"));
        assertEquals("21", list.getParent("31"));
        assertEquals("22", list.getParent("32"));
        assertEquals(22, list.size());

    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        Solution sol = list.clone();
        Solution add = new Solution();
        add.addAll(sol);
        assertEquals(sol, add);

    }

    @Test
    public void iteratorTest(){
        Iterator<String> itr = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (itr.hasNext()) {
            String a = itr.next();
            sb.append(a + " ");
        }
        assertEquals("1 3 4 7 8 10 15 16 17 19 21 22 23 24 25 26 27 28 29 30 31 32 ", sb.toString());

        Iterator<String> itr2 = list.iterator();
        while (itr2.hasNext()) {
            if (itr2.next().contains("31")) {
                itr2.remove();
            }
        }
        assertEquals("[1, 3, 4, 7, 8, 10, 15, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 32]", list.toString());

    }

    @Test
    public void serializationTest() throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("file");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();

        FileInputStream fis = new FileInputStream("file");
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<String> list2 = (List<String>) ois.readObject();
        ois.close();
        fis.close();

        assertEquals(list, list2);

    }

    @Test
    public void clearTest(){
        listTree.clear();
        assertTrue(listTree.isEmpty());

    }
}
