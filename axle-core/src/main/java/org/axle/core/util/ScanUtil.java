package org.axle.core.util;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * axle-core
 * <description>help for scanning class path</description>
 * @author Johnny Liu
 * @date 2018-10-01
 */
public class ScanUtil {
	
	/**
	 * axle-core
	 * <description>handle the target class</description>
	 * @author Johnny Liu
	 * @date 2018-10-01
	 */
	@FunctionalInterface
	public static interface ScanInvoker{
		public void invoke(String typeStr) throws Exception;
	}
	
	/**
	 * axle-core
	 * <description>scan the incoming url</description>
	 * @author Johnny Liu
	 * @date 2018-10-01
	 */
	@FunctionalInterface
	public static interface Scanner{
		public void scan(URL url) throws Exception;
	}
	
	/**
	 * scan the incoming url.
	 * @param url - incoming url
	 * @param s0  - scan class file
	 * @param s1  - scan jar file
	 * @throws Exception
	 */
	public static void doScan(URL url,Scanner s0,Scanner s1) throws Exception {
		String protocol = url.getProtocol().toLowerCase();
		switch(protocol) {
		case R.protocol.FILE:
			s0.scan(url);
			break;
		case R.protocol.JAR:
			s1.scan(url);
			break;
			default:
				throw new IllegalArgumentException("unknown argument");
		}
	}
	
	/**
	 * scan the specified package.
	 * @param basePkg - the specified package
	 * @param invoker - handle the target class
	 * @param s0	  - scan class file
	 * @param s1	  - scan jar file
	 * @throws Exception
	 */
	public static void doScan(String basePkg,ScanInvoker invoker,Scanner s0,Scanner s1) throws Exception {
		String toRecUrl = basePkg.replace(".", "/");
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(toRecUrl);
		while(urls.hasMoreElements()) doScan(urls.nextElement(),s0,s1);
	}
	
	/**
	 * scan the default package.
	 * @param invoker
	 * @throws Exception
	 */
	public static void doScan(ScanInvoker invoker) throws Exception {
		doScan(R.pkg.DEFAULT_SCANNER_PKG,invoker,(url) -> {
			File file = new File(url.getFile());
			for(File f : file.listFiles()) {
				String rawPath = f.getPath();
				String tmpPath = rawPath.substring(rawPath.indexOf(R.clazz.CLASSES)+8);
				invoker.invoke(tmpPath);
			}
		},(url) ->{
			JarFile jarFile = ((JarURLConnection)url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if(support(entry.getName())) {
					String typeStr = entry.getName().replace('/', '.');
					invoker.invoke(typeStr);
				}
			}
		});
	}
	
	public static boolean support(String name) {
		String prefix = R.pkg.DEFAULT_SCANNER_PKG.replace('.', '/');
		return name.startsWith(prefix) && name.endsWith("class") && name.indexOf("$") == -1;
	}
	
}
