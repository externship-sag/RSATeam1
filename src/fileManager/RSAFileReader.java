package fileManager;

import java.io.File;

/*
 * Interface class to implement read functionality for multiple file types.
 */
public interface RSAFileReader {
	String getFileData(File file);
}
