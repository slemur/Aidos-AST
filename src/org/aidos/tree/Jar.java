package org.aidos.tree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

import org.aidos.tree.adapter.ClassLoadAdapter;
import org.aidos.tree.encoding.ClassEncoder;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;

/**
 * This class is used to load and store files from a jar file.
 * @author `Discardedx2.
 */
public class Jar {

	/**
	 * The logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(Jar.class.getName());
	/**
	 * The loaded class files.
	 */
	private Map<String, ClassFile> classFiles = new HashMap<String, ClassFile>();
	/**
	 * The jar file to load.
	 */
	private JarFile jarFile;

	/**
	 * Constructs a new Jar.
	 * @param jarFile The jar file to load.
	 */
	public Jar(JarFile jarFile) {
		this.jarFile = jarFile;
	}

	/**
	 * Loads a jar from a specified path.
	 * @param path The path of the jar to load.
	 * @return A new loaded jar.
	 */
	public static Jar load(String path) {
		Jar loader = null;
		LOGGER.info("Parsing bytecode into a class map using objectweb asm.");
		try {
			loader = new Jar(new JarFile(path));
			Enumeration<JarEntry> entries = loader.getJarFile().entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				String name = zipEntry.getName();
				if (!name.endsWith(".class")) {
					continue;
				}
				ClassReader reader = new ClassReader(loader.jarFile.getInputStream(zipEntry));
				ClassFile cfn = new ClassFile(Opcodes.V1_6);
				ClassLoadAdapter loadAdapter = new ClassLoadAdapter(loader, cfn);
				reader.accept(loadAdapter, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
				loader.classFiles.put(name.replace(".class", ""), cfn);
			}
			LOGGER.info("Parsed "+loader.classFiles.size()+" classes into memory.");
			return loader;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

	/**
	 * Outputs modified {@link ClassFile}s to a jar file.
	 * @param jarPath The path to the jar that we should output to.
	 * @param files The files to output.
	 * @throws FileNotFoundException If the jar was unable to be created/overwritten.
	 * @throws IOException If we failed to write to the jar.
	 */
	public void write(String jarPath, Map<String, ClassFile> files) throws FileNotFoundException, IOException {
		JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarPath));
		try {
			for (ClassFile file : files.values()) {
				ClassWriter encoder = new ClassEncoder(file, ClassWriter.COMPUTE_FRAMES);
				ClassReader reader = new ClassReader(encoder.toByteArray());
				reader.accept(new CheckClassAdapter(encoder, true), 0);
				byte[] bytes = encoder.toByteArray();
				jos.putNextEntry(new JarEntry(file.getName().concat(".class")));
				jos.write(bytes);
			}
		} finally { 
			jos.close();
		}
	}

	/**
	 * Gets the loaded jar file.
	 * @return The jar file.
	 */
	public JarFile getJarFile() {
		return jarFile;
	}

	/**
	 * Gets the loaded class files.
	 * @return The class files.
	 */
	public Map<String, ClassFile> getClassFiles() {
		return classFiles;
	}
}
