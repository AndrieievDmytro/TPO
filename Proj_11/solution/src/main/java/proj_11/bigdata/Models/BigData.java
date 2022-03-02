package proj_11.bigdata.Models;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType( //
		namespace = "http://solution.dimas/big-data" //
)
public final class BigData {

	private static SecureRandom _random;
	private static final int DEFAULT_RAW_DATA_SIZE = 0x10000; // 64kB

	static {
		try {
			_random = SecureRandom.getInstanceStrong();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	public static List<BigData> generateDataList() {
		List<BigData> files = new ArrayList<>();
		for (int i = 0 ; i < 4 ; i++){
			String fileName = "fileName" + i;
			String keyWord = "keyWord";
			byte[] data = generateRawData(DEFAULT_RAW_DATA_SIZE);
			files.add(new BigData(data, fileName,keyWord));
		}
		return files;
	}

	public static BigData generate(){
		String fileName = "fileNameTest";
		String keyWord  = "keyWord";
		byte[] data = generateRawData(DEFAULT_RAW_DATA_SIZE);
		BigData newData = new BigData(data , fileName , keyWord);
		return newData;
	}

	private static byte[] generateRawData(int size) {
		byte[] data = new byte[size];
		_random.nextBytes(data);
		return data;
	}

	public String fileName;
	public byte[] rawData;
	public String keyWord; 

	public BigData() {
	}

	public BigData(byte[] rawData, String fileName, String keyWord) {
		this.fileName = fileName;
		this.rawData = rawData;
		this.keyWord = keyWord;
	}

	public String getFileName(){
		return this.fileName;
	}
	public byte[] getContent(){
		return this.rawData;
	}
	public String getKeyWord(){
		return this.keyWord;
	}
}