package com.bsod.mus1xtools;

import java.io.File;

public class Main {
	
	static String[] funciones = {"takeNumbersOut", "convert"};
	static String[] flags = {"full"};

	public static void main(String[] args) {
		if (args.length >= 2) {
			String funcion = args[0];
			String path = args[1];
			String flags = "";
			if (args.length == 3) {
				flags = args[2];
			}
			if (funcion.equals(funciones[0])) {
				takeNumbersOut(path, flags);
			} else if (funcion.equals(funciones[1])) {
				System.out.println("Not yet implemented cause I'm lazy.");
			} else  {
				printHeader();
				System.out.println("Unrecognized function.");
				printFunctions();
			}
		} else {
			printHeader();
			System.out.println("Wrong number of arguments.");
			printFunctions();
		}
		System.exit(0);
	}

	private static void takeNumbersOut(String path, String flag) {
		boolean fullScan = flag != null && !flag.isEmpty() && flag.equals(flags[0]);
		printHeader();
		System.out.println("Tryin to take numbers out from filenames in path " + path);
		System.out.println("Scan for internal folders: " + fullScan);
		File folder = new File(path);

		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0) {
				int totalfiles = files.length;
				System.out.println("Tryin to rename " + totalfiles + " files.");
				for (int i = 0; i < files.length; i++) {
					File file = files[i];
					if (fullScan) {
						if (file.isDirectory()) {
							System.out.println("Directory " + file.getName() + " found. Iterating into folder.");
							takeNumbersOut(file.getAbsolutePath(), flag);
						} else {
							process(file, i, path, totalfiles);
						}
					} else {
						process(file, i, path, totalfiles);
					}
				}
				System.out.println("Process finished!");
			} else {
				System.out.println("Folder is empty");
				System.exit(0);
			}
		} else {
			System.out.println("Indicated directory either doesn't exist or isn't a directory.");
			System.exit(0);
		}
	}
	
	private static void process(File file, int i, String path, int totalfiles) {
		String name = file.getName();
		StringBuilder auxName = new StringBuilder(name);
		char[] characters = name.toCharArray();
		for (int x = 0; x < characters.length; x++) {
			char c = characters[x];
			if (!Character.isLetter(c)) {
				auxName.deleteCharAt(0);
			} else {
				break;
			}
		}
		String newName = auxName.toString();
		if (!name.equals(newName)) {
			File newFile = new File(path, newName);
			if(file.renameTo(newFile)) {
				System.out.println("[" + (i + 1) + "/" + totalfiles + "] Old file " + name + " renamed to " + newName + ".");
			}
		} else {
			System.out.println("[" + (i + 1) + "/" + totalfiles + "] No need to rename the file.");
		}
	}
	
	private static void printHeader() {
		System.out.println("Mus1xTools v0.1 =");
		System.out.println("=================");
		System.out.println("By M4v3r1cX");
		System.out.println();
	}
	
	private static void printFunctions() {
		System.out.println("Available functions:");
		for (int i = 0; i < funciones.length; i++) {
			System.out.println(funciones[i] + " [path] [flags]");
		}
		System.out.println("Availble flags:");
		System.out.println("full - scans for internal folders");
	}
}
