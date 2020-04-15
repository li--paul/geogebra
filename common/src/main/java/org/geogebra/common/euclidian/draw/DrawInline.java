package org.geogebra.common.euclidian.draw;

public interface DrawInline {
	/**
	 * Update editor from geo
	 */
	void updateContent();

	/**
	 * Send this to foreground
	 * @param x x mouse coordinates in pixels
	 * @param y y mouse coordinates in pixels
	 */
	void toForeground(int x, int y);

	/**
	 * Send this to background
	 */
	void toBackground();
}
