package MultiThread;
//luong 1
class TongUoc extends Thread {

	int n;
	int Tong = 0;
//ham khoi tao lay gia tri n
	public TongUoc(int n) {
		this.n = n;
	}

	@Override
	public void run() {

		try {

			for (int i = 1; i < n; i++) {
				if (n % i == 0) {
					Tong = Tong + i;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public int getResult() {
		return Tong;
	}
}
//luong 2
class Can extends Thread {
	int n;
	double kq = 0.0;
//ham khoi tao lay gia tri n
	public Can(int n) {
		this.n = n;
	}

	public void run() {
		try {

			for (int i = 1; i <= n; i++) {
				kq += Math.sqrt(i);
			}
			Thread.sleep(10000);
		} catch (Exception e) {

			System.out.println(e.getMessage());// TODO: handle exception
		}
	}

	public double getResult() {
		return kq;
	}
}

public class Luong {
	TongUoc t1 = new TongUoc(10000);
	Can t2 = new Can(5);

	public Luong() {
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
			System.out.println("tong uoc la " + t1.getResult());
			System.out.println("TOng can" + t2.getResult());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {

		new Luong();
	}

}
