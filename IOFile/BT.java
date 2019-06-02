import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class BT {
	static int lv = 0;
	static boolean check = false;

	public static double size(String path) {
		double sumSize = 0;

		File fi = new File(path);
		if (!fi.exists())
			return 0;
		File[] list = fi.listFiles();
		if (fi.isDirectory())
			check = true;
		if (list != null)
			for (File f : list) {
				lv++;
				if (f.isDirectory()) {

					sumSize += size(f.getAbsolutePath());
					System.out.println(hien() + f.getAbsolutePath() + " " + sumSize + " byte");
					check = true;
				} else {
					sumSize += f.length();
					System.out.println(hien() + f.getAbsolutePath() + " " + sumSize + " byte");
					check = true;
				}
				lv--;
			}
		return sumSize;
	}

	public static String hien() {
		String temp = " ";
		for (int i = 0; i < lv; i++) {
			if (check == true) {
				temp += "|---";
			}
		}
		return temp;
	}

	public static void main(String args[]) {
		String folder = "G://test";
		size(folder);
		new BT();
	}

}