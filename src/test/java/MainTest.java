import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.*;

class MainTest {

    @BeforeEach
    public void before() {
        System.out.println("Before===========================");
    }

    @Test
    @DisplayName("시작")
    public void Hello() throws Exception {
        // given
        Object obj = new Object();
        Object obj2 = new Object();

        //when

        //then
        assertThat(obj.getClass()).isEqualTo(obj2.getClass());
    }

    @Test
    public void Clone() throws Exception {
        // given
        Tdd_test test = new Tdd_test();
        Object obj = new Object();
        //when
        Object obj2 = test.clone();
        //then
        System.out.println(obj2.getClass());

        int[] tt = new int[10];
        int[] tt2 = tt.clone();
        // 배열은 Clone 이 가능하다.
        // 하지만 커스텀 객체를 Clonable을 구현하여 clone 할 경우, 원본 인스턴스의 배열 필드를 똑같이 참조한다.

    }

    @Test
    public void Comparable() throws Exception {
        // given
        int compare = Integer.compare(10, 10);
        //when
        System.out.println("compare = " + compare);

        String hi = "HIHIHI";
      BigInteger a = new BigInteger("100");


        //then
    }

    class Tdd_test implements Cloneable {
        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    @Test
    public void extend() throws Exception {
        // given
        Object a = new Object();
        Object b = new Object();

        //when
        extend_sub test = new extend_sub(1L,2L);
        test.hi();
        //then
    }

    public class extend_sub extends extend_super{
        private Long id_sub;

        public extend_sub(Long id, Long id_sub) {
            super(id);
            this.id_sub = id_sub;
        }


        public void hi(){
            System.out.println("this = ");
        }
    }

    class extend_super {
        private Long id;

        public extend_super(Long id) {
            this.id = id;
        }

        public void hi(){
            System.out.println("hi");
        }
    }

    @Test
    public void abstract_test() throws Exception {
        // given
        abstract_sub a = new abstract_sub_sub();
        a.hi();
        a.hi2();


        //when

        new AbstractList<>(){

            @Override
            public int size() {
                return 0;
            }

            @Override
            public Object get(int index) {
                return null;
            }
        };
        //then
    }

    class abstract_sub_sub extends abstract_sub {

    }

    abstract class abstract_sub extends abstract_super implements interface_super, Map.Entry {
        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }

        @Override
        public void hi(){
            System.out.println("hi2");
        }
    }

    abstract class abstract_super {

        public void hi(){
            System.out.println("hi");
        }
    }

    interface interface_super {
        public void hi();

        default void hi2(){
            System.out.println("hi default");
        }
    }


    @Test
    public void generic() throws Exception {
        // given
        List<Integer> li = new ArrayList<>();
        List<String> ls = new ArrayList<>();

        List<Object> tt = new ArrayList<>();
        List<Object> tt2 = new ArrayList<>();


        List<abstract_sub_sub> la = new ArrayList<>();
        List<?> ttt = new ArrayList<>();


        Set<abstract_sub> aa = new HashSet<>();
        Comparator<Object> objectComparator = Collections.reverseOrder();

        String[] strings = {"aa","bb","cc"};
        UnaryOperator<String> sameString = identityFunction();
        for (String string : strings) {
            System.out.println(sameString.apply(string));
        }

        tt.add("ggasdfa");

        //when
        String[] hi = ttt("a","b","b","c");
        //then
    }


    private static UnaryOperator<Object> IDENTIFY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction(){
        return (UnaryOperator<T>) IDENTIFY_FN;
    }


    static <T> T[] ttt(T... arg){
        for (T s: arg) {
            System.out.println("s = " + s);
        }
        return arg;
    }



    @Test
    public void favorite_test() throws Exception {
        // given
        Favorite f = new Favorite();
        f.putFavorite(String.class,"Java");
        f.putFavorite(Integer.class,0xcafebabe);
        f.putFavorite(Class.class,Favorite.class);
        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        //when
        System.out.printf("%s %x %s%n",favoriteString, favoriteInteger, favoriteClass.getName());
        //then
    }

    public class Favorite{
        private final Map<Class<?>, Object> favorites;

        Favorite(){
            favorites = new HashMap<>();
        }

        public <T> void putFavorite(Class<T> t, T o){
            favorites.put(t,o);
        }

        public <T> T getFavorite(Class<T> t){
            return t.cast(favorites.get(t));
        }
    }

    @Test
    public void ENUM() throws Exception {
        // given
        System.out.println("plant.flower.leaf = " + plant.flower.leaf);
        //when

        for (plant value : plant.values()) {
            System.out.println("value = " + value.leaf);
            System.out.println("value = " + value.toString());
            System.out.println("value = " + value);
        }
        //then
        System.out.println("Operation.PLUS.apply(10,2) = " + Operation.PLUS.apply(10, 2));
        EnumSet<Operation> plus = EnumSet.of(Operation.PLUS, Operation.MINUS);
    }

    public enum Operation {
        PLUS {
            public double apply(double a, double b){
                return a + b;
            }
        },
        MINUS {
            public double apply(double a, double b){
                return a - b;
            }
        },
        DEVIDE  {
            public double apply(double a, double b){
                return a / b;
            }
        },
        TIMES {
            public double apply(double a, double b){
                return a * b;
            }
        };

        public abstract double apply(double a, double b);
    }

    public enum plant {

        flower(5),
        tree(100),
        glass(10);

        private final int leaf;

        plant(int leaf){
            this.leaf = leaf;
        }
    }

}
    
