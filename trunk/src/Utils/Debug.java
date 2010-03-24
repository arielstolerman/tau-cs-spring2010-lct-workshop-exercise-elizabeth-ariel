package Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Debug {
	private static final String LOG_FILE = "LCT_log.txt";
	private static BufferedWriter outputFile = null;
	private static BufferedWriter queryOutputFile = null;
	private static final boolean DEBUG_MODE = false;

	//Time
	private static final String DATE_FORMAT = "HH:mm:ss";
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	private static Calendar cal = null;

	public enum DebugOutput {
		STDOUT,
		STDERR,
		FILE
	}

	/**
	 * A method for logging.
	 * The usage is: Debug.log("This message goes to file..", DebugOutput.FILE);
	 * @param message: The massage explaining the current stage/problem.
	 * @param logger: The destination to write the message (STDOUT, STDERR, FILE)
	 */
	public static void log(String message, DebugOutput logger) {
		switch (logger) {
		case STDOUT : {
			if (DEBUG_MODE)
				System.out.println(message);
			break;
		}
		case STDERR : {
			if (DEBUG_MODE)
				System.err.println(message);    
			break;
		}
		case FILE : {
			try {
				if (outputFile == null) {
					outputFile = new BufferedWriter(new FileWriter(LOG_FILE));
				}
				cal = Calendar.getInstance();
				outputFile.write(sdf.format(cal.getTime())+" > "+message + "\r\n");
				outputFile.flush();
			} catch (IOException e) {
				System.err.println(e);
			}
			break;
		}
		}
	}


	/**
	 * calls log twice with two given loggers
	 * @param message
	 * @param logger1
	 * @param logger2
	 */
	public static void log(String message, DebugOutput logger1, DebugOutput logger2) {
		log(message, logger1);
		log(message, logger2);
	}

	/**
	 * Calls log with FILE as DebugOutput
	 */
	public static void log(String message){
		log(message, DebugOutput.FILE);
	}

	/**
	 * Closing file handles.
	 */
	public static void closeLog(){
		try {
			outputFile.close();
			queryOutputFile.close();
		} catch (IOException e) {}      
	}
}

