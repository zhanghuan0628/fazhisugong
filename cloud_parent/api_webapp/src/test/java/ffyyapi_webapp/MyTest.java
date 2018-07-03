package ffyyapi_webapp;

public class MyTest {
	public static  String s ="1";
	public static void main(String[] args) {
//		Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
//		 
//		Short s = 1;
//		Float a1 = 1F, a2 = 1F, a3 = 150F, a4 = 150F;
//		Short s1 = 100, s2 = 100, s3 = 150, s4 = 150;
//        System.out.println(f1 == f2);
//        System.out.println(f3 == f4);
//        
//        System.out.println(s1 == s2);
//        System.out.println(s3 == s4);
//        
//        System.out.println(a1 == a2);
        System.out.println((3 << 5) -3);
        System.out.println((3 * 32) -3);
		
		getB(getA());
		s = new String();
		getB(s);
		//System.out.println(s);
		
		;
	}
	
	
	public static String getA(){
		s = "geta";
		System.out.println(s);
		return s;
	}
	public static void getB(String s){
		System.out.println(s);
	}
	
}
