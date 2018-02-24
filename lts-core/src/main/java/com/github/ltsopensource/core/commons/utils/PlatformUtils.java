package com.github.ltsopensource.core.commons.utils;

import com.google.common.base.Suppliers;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Robert HG (254963746@qq.com) on 4/6/16.
 */
public class PlatformUtils {

	private static final Supplier<String> OPERATING_SYSTEM = Suppliers.memoize(() -> {
        String name = Objects.toString(System.getProperty("os.name"), "").toLowerCase().trim();
        if (name.startsWith("linux")) {
            return "linux";
        }
        if (name.startsWith("mac os x")) {
            return "osx";
        }
        if (name.startsWith("win")) {
            return "windows";
        }
        return name.replaceAll("\\W+", "_");
    })::get;

	private static final boolean IS_WINDOWS;
	private static final boolean IS_OSX;
	private static final boolean IS_LINUX;

	static {
		IS_WINDOWS = "windows".equals(OPERATING_SYSTEM.get());
		IS_OSX = "osx".equals(OPERATING_SYSTEM.get());
		IS_LINUX = "linux".equals(OPERATING_SYSTEM.get());
	}

	private static String getOperatingSystem() {
		return OPERATING_SYSTEM.get();
	}

	public static boolean isWindows() {
		return IS_WINDOWS;
	}

	public static boolean isOSX() {
		return IS_OSX;
	}

	public static boolean isLinux() {
		return IS_LINUX;
	}

}
