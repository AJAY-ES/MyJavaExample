import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class Test {

	static Scanner sc = new Scanner(System.in);
	static final File dir = new File("img");
	static final String[] EXTENSIONS = new String[] { "jpeg", "png", "jpg" // and other formats you need
	};
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith("." + ext)) {
					return (true);
				}
			}
			return (false);
		}
	};

	public static void main(String[] args) {
		System.out.print("Enter integer key: ");
		int key = sc.nextInt();
                if (dir.isDirectory()) {
			for (File f : dir.listFiles(IMAGE_FILTER)) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(f);
					System.out.println("image:" + f.getName());
                                        cryptIt(f, key); 
					 
				} catch (IOException e) {
					// handle errors here
				}
			}
		}
	}

	public static void  cryptIt(File f, int key) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(f);
		byte data[] = new byte[fis.available()];
		fis.read(data);
		data[0] = (byte) (data[0] ^ key);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(data);
		fos.close();
		fis.close();
		System.out.println("Done");
	}
 

}
