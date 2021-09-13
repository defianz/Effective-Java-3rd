import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

}
    