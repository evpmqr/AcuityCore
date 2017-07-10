package com.acuity.api.rs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Zach on 8/21/2016.
 */
public class Timer {

	private static final Logger logger = LoggerFactory.getLogger(Timer.class);

	private long startTime = System.currentTimeMillis();
	private long duration = -1;

	public Timer(long duration) {
		this.duration = duration;
	}

	public Timer() {
	}

	public long getRuntime() {
		return System.currentTimeMillis() - startTime;
	}

	public long getTimeLeft() {
		long runtime = getRuntime();
		if (runtime > duration) {
			return 0;
		}
		return duration - runtime;
	}

	public void restart() {
		startTime = System.currentTimeMillis();
	}

	public boolean isComplete() {
		return getTimeLeft() <= 0;
	}

	public boolean isRunning() {
		return getTimeLeft() > 0;
	}

	public void restart(long duration) {
		this.duration = duration;
		restart();
	}

	public void onComplete(Runnable runnable) {
		new Thread(() -> {
			try {
				sleepUntilComplete();
				runnable.run();
			} catch (Throwable e) {
				logger.warn("Error during timer onComplete");
			}
		}).start();
	}

	public void sleepUntilComplete() {
		while (getTimeLeft() > 0) {
			Time.sleep(290);
		}
	}

	public String getFormattedTime() {
		return getFormattedTime(getRuntime());
	}

	public static String getFormattedTime(final long time) {
		long secs = time / 1000;
		return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, (secs % 60));
	}
}
