// https://itsakura.com/java-thread-runnable
// https://www.tohoho-web.com/java/thread.htm 
public class App {
    public static void main(String[] args) throws Exception {
        Counter cnt = new Counter();
		Color1 c1 = new Color1("red", cnt);
		Color1 c2 = new Color1("yellow", cnt);
		Color1 c3 = new Color1("blue", cnt);
		Color2 col1 = new Color2();

        Thread[] launch = {new Thread(c1), new Thread(c2), new Thread(c3)};
        for (Thread thread : launch) thread.start();
        try {
            for (Thread thread : launch) thread.join();;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		col1.display();
        System.out.println("CNT:" + cnt.count);
    }
}
class Color1 implements Runnable{
    private Counter cnt;
	private String col;
	Color1(String col, Counter cnt){
		this.col = col;
        this.cnt = cnt;
	}

	@Override
    public  void run(){
        for (int i = 0;i<4;i++){
            cnt.countUp(col);
        }
	}
}
class Counter {
    public int count;
    synchronized void countUp(String msg) {
        System.out.print("[");
        int n = count;            // カウンターを読み出して
        System.out.print(msg);
        System.out.print(".");
        System.out.print(n);
        count = n + 1;            // 加算して書き戻す
        System.out.println("]");
    }
}
class Color2 {
	void display(){
		System.out.println("オレンジ");
	}
}