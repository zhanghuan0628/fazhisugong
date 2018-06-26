package third_webapp;


public class Test {

    public static void main(String[] args) {
        String path = Test.class.getResource("/").getPath().replace("/C:", "C:");
        System.out.print(path);

    }

}
