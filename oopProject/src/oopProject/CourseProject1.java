package oopProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CourseProject1 {
	static final int CLOSE_SELECTION = 0;
	static final int ADD_FILE_SELECTION = 1;
	static final int DELETE_FILE_SELECTION = 2;
	static final int SEARCH_FILE_SELECTION = 3;
	static final int LIST_AVAILABLE_FILES = 4;
	static final int MENU_SELECTION = 9;
	static final String FOLDER_ROOT = System.getProperty("user.dir") + "\\output";

	public static void main(String[] args) throws Exception {
		setupProject();
		Scanner in = new Scanner(System.in);
		int inputChoice;
		System.out.println("Welcome to Company Lockers Pvt. Ltd");
		System.out.println("Developer details: \n\t*Rahaf Emad AlRebeea");
		menu();
		while ((inputChoice = in.nextInt()) != CLOSE_SELECTION) {

			switch (inputChoice) {
			case ADD_FILE_SELECTION:
				addFileDriver();
				break;
			case DELETE_FILE_SELECTION:
				deleteFileDriver();
				break;
			case SEARCH_FILE_SELECTION:
				searchFileDriver();
				break;
			case LIST_AVAILABLE_FILES:
				listAllFiles();
				break;
			default:
				System.out.println("Invalid selection");
			}
			menu();
		}

		exitApp();

	}

	public static void menu() throws Exception {

		System.out.println(ADD_FILE_SELECTION + "- Add file to the application");
		System.out.println(DELETE_FILE_SELECTION + "- Delete a file from the application");
		System.out.println(SEARCH_FILE_SELECTION + "- Search for a file in the application");
		System.out.println(LIST_AVAILABLE_FILES + "- List all files in the application");
		System.out.println(MENU_SELECTION + "- Print Menu");
		System.out.println(CLOSE_SELECTION + "- Exit the application");
	}

	public static void exitApp() throws Exception {
		System.out.println("Shutting down, Bye Bye");
		System.exit(1);
	}

	private static void searchFileDriver() throws Exception {
		System.out.println("### Search File ###");
		System.out.print("Please enter the file name to find: ");
		Scanner in = new Scanner(System.in);
		String filename = in.next();
		in.close();
		if (searchFile(filename)) {
			System.out.println("File is Found");
			Scanner fin;
			try {
				fin = new Scanner(new File(FOLDER_ROOT + "\\" + filename + ".txt"));
				if (fin.hasNext()) {
					while (fin.hasNextLine()) {
						System.out.println(fin.nextLine());
					}
				} else {
					System.out.println("File is empty");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("File Not Found");
		}
	}

	public static boolean searchFile(String filename) throws Exception {
		File file = new File(FOLDER_ROOT + "\\" + filename + ".txt");
		return file.exists();
	}

	private static void deleteFileDriver() throws Exception {
		System.out.println("### Delete File ###");
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the file name to delete: ");
		String filename = in.next();
		if (deleteFile(filename)) {
			System.out.println("Deleted Successully");
		} else {
			System.out.println("File not found");
		}
		in.close();
	}

	public static boolean deleteFile(String filename) throws Exception {
		File file = new File(FOLDER_ROOT + "\\" + filename + ".txt");
		if (!file.exists()) {
			return false;
		}
		return file.delete();
	}

	private static void addFileDriver() throws Exception {
		System.out.println("### Add File ###");
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter the file name to add: ");
		String filename = in.next();
		if (addFile(filename)) {
			System.out.println("Added Successully");
		} else {
			System.out.println("Unsuccessful");
		}
		in.close();
	}

	public static boolean addFile(String filename) throws Exception {
		File file = new File(FOLDER_ROOT + "\\" + filename + ".txt");
		if (file.exists()) {
			return false;
		}

		try {
			return file.createNewFile();
		} catch (IOException e) {
			return false;
		}
	}

	private static void setupProject() throws Exception {
		File file = new File(FOLDER_ROOT);
		if (!file.exists()) {
			System.out.println("File Doesnt exists");
			file.mkdir();
		}
	}

	private static void listAllFiles() throws Exception {
		File file = new File(FOLDER_ROOT);
		if ((file.exists())) {
			String[] fileNames = file.list();
			if (fileNames == null) {
				return;
			}
			Arrays.sort(fileNames);
			for (String fn : fileNames) {
				System.out.println(fn.replace(".txt", ""));
			}
		}
	}
}
