package yj.core.webservice_test.components;

public class test {
    public static void main(String[] args) {
        String a = "50.0";
        if (a.substring(a.length()-2,a.length()).equals(".0")){
            a = a.substring(0,a.length()-2);
        }
        Long tmp = Long.valueOf(a);
        System.out.print(tmp);
    }
}
