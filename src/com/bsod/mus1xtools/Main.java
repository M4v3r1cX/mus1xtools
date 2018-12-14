package com.bsod.mus1xtools;

import java.io.File;

public class Main {
	
	static String[] funciones = {"takeNumbersOut"};

	public static void main(String[] args) {
		if (args.length == 2) {
			String funcion = args[0];
			String path = args[1];
			
			if (funcion.equals(funciones[0])) {
				takeNumbersOut(path);
			} else {
				printHeader();
				System.out.println("Unrecognized function.");
				System.out.println("Available functions: takeNumbersOut [path]");
			}
		} else {
			printHeader();
			System.out.println("Wrong number of arguments.");
			System.out.println("Available functions: takeNumbersOut [path]");
		}
		System.exit(0);
	}

	private static void takeNumbersOut(String path) {
		printHeader();
		System.out.println("Tryin to take numbers out from filenames.");
		File folder = new File(path);

		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0) {
				int totalfiles = files.length + 1;
				System.out.println("Tryin to rename " + totalfiles + " files.");
				for (int i = 0; i < files.length; i++) {
					String name = files[i].getName();
					StringBuilder auxName = new StringBuilder(name);
					char[] characters = name.toCharArray();
					for (int x = 0; x < characters.length; x++) {
						if (!Character.isLetter(characters[x])) {
							auxName.deleteCharAt(x);
						} else {
							break;
						}
					}
					String newName = auxName.toString();
					if (!name.equals(newName)) {
						File newFile = new File(path, newName);
						if(files[i].renameTo(newFile)) {
							System.out.println("[" + (i + 1) + "/" + totalfiles + "] Old file " + name + " renamed to " + newName + ".");
						}
					} else {
						System.out.println("[" + (i + 1) + "/" + totalfiles + "] No need to rename the file.");
					}
					System.out.println("Process finished!");
				}
			} else {
				System.out.println("Folder is empty");
				System.exit(0);
			}
		} else {
			System.out.println("Indicated directory either doesn't exist or isn't a directory.");
			System.exit(0);
		}
	}
	
	private static void printHeader() {
		System.out.println("Mus1xTools v0.1 =");
		System.out.println("=================");
		System.out.println("By M4v3r1cX");
		System.out.println();
	}
}
