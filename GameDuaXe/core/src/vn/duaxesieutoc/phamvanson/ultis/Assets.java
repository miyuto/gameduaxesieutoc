package vn.duaxesieutoc.phamvanson.ultis;

public class Assets extends LevelAssets{
	private static Assets INSTANCE = null;
	private Assets() {
	}
	public static Assets getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Assets();
		}
		return INSTANCE;
	}
}
