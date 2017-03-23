package mactest;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class NamingStreagyTest {
    public static void main(String[] args) {

        ImprovedNamingStrategy jkj = new ImprovedNamingStrategy();

        String out = jkj.columnName("irApplicant");

        System.out.println("" + out);
        System.out.println("" + jkj.tableName("javaAbc"));
    }
}
