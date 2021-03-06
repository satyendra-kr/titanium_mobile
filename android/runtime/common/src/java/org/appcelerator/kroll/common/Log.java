/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package org.appcelerator.kroll.common;

/**
 * API to send log output. Supported severity-levels include: 'debug', 'warn', 'info' and 'error'.
 * Refer to <a href="http://developer.android.com/reference/android/util/Log.html">Android Log documentation</a> for more information. 
 */
public class Log
{
	private static long lastLog = System.currentTimeMillis();
	private static long firstLog = lastLog;

	public static synchronized void checkpoint(String tag, String msg) {
		lastLog = System.currentTimeMillis();
		firstLog = lastLog;
		i(tag, msg);
	}

	protected String tag;
	protected boolean debug;
	public Log(String tag, boolean debug) {
		this.tag = tag;
		this.debug = debug;
	}
	
	/**
	 * Sends a 'debug' log, with thread name and time stamp pre-appended, ONLY when TiConfig.DEBUG is true.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the msg.
	 * @param msg  the msg to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 */
	public static int debug(String tag, String msg) {
		return debug(tag, msg, TiConfig.DEBUG);
	}
	
	/**
	 *  A convenience debugging method that only prints when debug is true
	 */
	public static int debug(String tag, String msg, boolean debug) {
		if (debug) {
			return d(tag, msg);
		}
		return 0;
	}
	
	public int debug(String msg) {
		return debug(tag, msg, debug);
	}
	
	public int debugFormat(String format, Object... args) {
		return debug(String.format(format, args));
	}
	
	/**
	 * Sends a 'verbose' log message, with the thread name and time stamp pre-appended.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int v(String tag, String msg) {
		msg = onThread(msg);
		return android.util.Log.v(tag, msg);
	}
	
	public int v(String msg) {
		return v(tag, msg);
	}
	
	/**
	 * Sends a 'verbose' log message, with the thread name and time stamp pre-appended, and log the exception.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @param t    the exception to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int v(String tag, String msg, Throwable t) {
		msg = onThread(msg);
		return android.util.Log.v(tag, msg, t);
	}

	public int v(String msg, Throwable t) {
		return v(tag, msg, t);
	}
	
	/**
	 * Sends a 'debug' log message, with the thread name and time stamp pre-appended.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int d(String tag, String msg) {
		msg = onThread(msg);
		return android.util.Log.d(tag, msg);
	}
	
	public int d(String msg) {
		return d(tag, msg);
	}
	
	/**
	 * Sends a 'debug' log message, with the thread name and time stamp pre-appended, and log the exception.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @param t    the exception to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int d(String tag, String msg, Throwable t) {
		msg = onThread(msg);
		return android.util.Log.d(tag, msg, t);
	}
	
	public int d(String msg, Throwable t) {
		return d(tag, msg, t);
	}

	/**
	 * Sends a 'info' log message, with the thread name and time stamp pre-appended.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int i(String tag, String msg) {
		msg = onThread(msg);
		return android.util.Log.i(tag, msg);
	}
	
	public int i(String msg) {
		return i(tag, msg);
	}
	
	/**
	 * Sends a 'info' log message, with the thread name and time stamp pre-appended, and log the exception. 
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to idenfity the source of the message.
	 * @param msg  the message to log.
	 * @param t    the exception to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int i(String tag, String msg, Throwable t) {
		msg = onThread(msg);
		return android.util.Log.i(tag, msg, t);
	}
	
	public int i(String msg, Throwable t) {
		return i(tag, msg, t);
	}

	/**
	 * Sends a 'warn' log message, with the thread name and time stamp pre-appended.
	 * For example, "(main) [298, 474] hello" --> "(thread-name) [elapsed time, total time] msg".
	 * This method is thread safe.
	 * @param tag  used to identify the source of the message.
	 * @param msg  the message to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int w(String tag, String msg) {
		msg = onThread(msg);
		return android.util.Log.w(tag, msg);
	}
	
	public int w(String msg) {
		return w(tag, msg);
	}
	
	/**
	 * Sends a 'warn' log message, with the thread name and time stamp pre-appended, and log the exception.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of message.
	 * @param msg  the message to log.
	 * @param t    an exception to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int w(String tag, String msg, Throwable t) {
		msg = onThread(msg);
		return android.util.Log.w(tag, msg, t);
	}
	
	public int w(String msg, Throwable t) {
		return w(tag, msg, t);
	}

	/**
	 * Sends a 'error' log message, with the thread name and time stamp pre-appended.
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of message.
	 * @param msg  the message to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int e(String tag, String msg) {
		msg = onThread(msg);
		return android.util.Log.e(tag, msg);
	}
	
	public int e(String msg) {
		return e(tag, msg);
	}
	
	/**
	 * Sends a 'error' log message, with the thread name and time stamp pre-appended, and log the exception
	 * For more information regarding formatting, refer to {@link #w(String, String)}.
	 * This method is thread safe.
	 * @param tag  used to identify the source of message.
	 * @param msg  the message to log.
	 * @param t    the exception to log.
	 * @return     an integer that is dependent on the content and tag of the log. 
	 *             Two different msgs would have two different return values.
	 * @module.api
	 */
	public static int e(String tag, String msg, Throwable t) {
		msg = onThread(msg);
		return android.util.Log.e(tag, msg, t);
	}
	
	public int e(String msg, Throwable t) {
		return e(tag, msg, t);
	}

	private static synchronized String onThread(String msg)
	{
		long currentMillis = System.currentTimeMillis();
		long elapsed = currentMillis - lastLog;
		long total = currentMillis - firstLog;
		lastLog = currentMillis;

		StringBuilder sb = new StringBuilder(256);
		sb.append("(").append(Thread.currentThread().getName()).append(") [")
			.append(elapsed).append(",").append(total).append("] ")
			.append(msg)
		;
		String s = sb.toString();
		sb.setLength(0);
		return s;
	}
}
