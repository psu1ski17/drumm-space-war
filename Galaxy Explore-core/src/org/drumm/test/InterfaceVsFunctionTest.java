package org.drumm.test;

public class InterfaceVsFunctionTest {
	public static void main(String[] args) {
		int n = 1000;
		boolean test = false;
		InterfaceTest it = new InterfaceTest();
		long t1 = System.nanoTime();
		for (int i = 0; i < n; i++) {
			test=(it instanceof InterfaceTest);
		}
		long t2=System.nanoTime();

		for (int i = 0; i < n; i++) {
			test=it.isInterface();
		}
		long t3=System.nanoTime();
		System.out.println(t2-t1);
		System.out.println(t3-t2);
		System.out.println(test);

	}
	public static class InterfaceTest implements Interface{
		public boolean isInterface(){
			return true;
		}
	}
}
