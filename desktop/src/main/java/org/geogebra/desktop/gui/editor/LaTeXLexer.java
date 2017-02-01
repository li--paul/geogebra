/* The following code was generated by JFlex 1.4.3 on 26/12/10 12:23 */

//CHECKSTYLE:OFF

/* 
 GeoGebra - Dynamic Mathematics for Everyone
 http://www.geogebra.org

 This file is part of GeoGebra.

 This program is free software; you can redistribute it and/or modify it 
 under the terms of the GNU General Public License as published by 
 the Free Software Foundation.

 */

package org.geogebra.desktop.gui.editor;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

import org.geogebra.common.util.Charsets;

/**
 * This class is a scanner generated by <a href="http://www.jflex.de/">JFlex</a>
 * 1.4.3 on 26/12/10 12:23 from the specification file <tt>latex.jflex</tt>
 */
public final class LaTeXLexer extends Lexer implements LaTeXLexerConstants {

	/** This character denotes the end of file */
	public static final int YYEOF = -1;

	/** initial size of the lookahead buffer */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** lexical states */
	public static final int YYINITIAL = 0;

	/**
	 * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
	 * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l at the
	 * beginning of a line l is of the form l = 2*k, k a non negative integer
	 */
	private static final int ZZ_LEXSTATE[] = { 0, 0 };

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\12\1\7\2\0\1\1\22\0\1\14\3\0\1\5\1\13"
			+ "\1\4\11\0\12\11\7\0\32\10\1\2\1\6\1\2\1\3\1\3"
			+ "\1\0\32\10\1\2\1\0\1\2\uff82\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 = "\1\0\2\1\1\2\1\3\1\4\1\5\1\1\1\6"
			+ "\1\7\1\1\1\10\1\11\1\12\1\0\1\12";

	private static int[] zzUnpackAction() {
		int[] result = new int[16];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do {
				result[j++] = value;
			} while (--count > 0);
		}
		return j;
	}

	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = {
			"Unkown internal scanner error", "Error: could not match input",
			"Error: pushback value was too large" };

	/** the input device */
	private java.io.Reader zzReader;

	/** the current state of the DFA */
	private int zzState;

	/** the current lexical state */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is the source of
	 * the yytext() string
	 */
	private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

	/** the textposition at the last accepting state */
	private int zzMarkedPos;

	/** the current text position in the buffer */
	private int zzCurrentPos;

	/** startRead marks the beginning of the yytext() string in the buffer */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read from
	 * input
	 */
	private int zzEndRead;

	/** number of newlines encountered up to the start of the matched text */
	private int yyline;

	/** the number of characters up to the start of the matched text */
	private int yychar;

	/** zzAtEOF == true <=> the scanner is at the EOF */
	private boolean zzAtEOF;

	/* user code: */
	public int end;

	private Document doc;
	private Element elem;

	public LaTeXLexer() {
	}

	public LaTeXLexer(Document doc) {
		this();
		setDocument(doc);
	}

	@Override
	public void setDocument(Document doc) {
		this.doc = doc;
		this.elem = doc.getDefaultRootElement();
	}

	@Override
	public void setRange(int p0, int p1) {
		start = p0;
		end = p1;
		String str = "";
		try {
			str = doc.getText(p0, p1 - p0);
		} catch (BadLocationException e) {
		}
		yyreset(new StringReader(str));
	}

	@Override
	public int yychar() {
		return yychar;
	}

	@Override
	public int scan() throws IOException {
		return yylex();
	}

	@Override
	public int getKeyword(int pos, boolean strict) {
		Element line = elem.getElement(elem.getElementIndex(pos));
		int end1 = line.getEndOffset();
		int tok = -1;
		start = line.getStartOffset();
		int startL = start;
		int s = -1;

		try {
			yyreset(new StringReader(doc.getText(start, end1 - start)));
			if (!strict) {
				pos++;
			}

			while (startL < pos && s != startL) {
				s = startL;
				tok = yylex();
				startL = start + yychar + yylength();
			}

			return tok;
		} catch (Exception e) {
			return LexerConstants.DEFAULT;
		}
	}

	/**
	 * Creates a new scanner There is also a java.io.InputStream version of this
	 * constructor.
	 * 
	 * @param in
	 *            the java.io.Reader to read input from.
	 */
	public LaTeXLexer(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner. There is also java.io.Reader version of this
	 * constructor.
	 * 
	 * @param in
	 *            the java.io.Inputstream to read input from.
	 * @throws UnsupportedEncodingException
	 */
	public LaTeXLexer(java.io.InputStream in)
			throws UnsupportedEncodingException {
		this(new java.io.InputStreamReader(in, Charsets.UTF_8));
	}

	/**
	 * Unpacks the compressed character translation table.
	 * 
	 * @param packed
	 *            the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 52) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do {
				map[j++] = value;
			} while (--count > 0);
		}
		return map;
	}

	/**
	 * Refills the input buffer.
	 * 
	 * @return <code>false</code>, iff there was new input.
	 * 
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	private boolean zzRefill() throws java.io.IOException {

		/* first: make room (if you can) */
		if (zzStartRead > 0) {
			System.arraycopy(zzBuffer, zzStartRead, zzBuffer, 0,
					zzEndRead - zzStartRead);

			/* translate stored positions */
			zzEndRead -= zzStartRead;
			zzCurrentPos -= zzStartRead;
			zzMarkedPos -= zzStartRead;
			zzStartRead = 0;
		}

		/* is the buffer big enough? */
		if (zzCurrentPos >= zzBuffer.length) {
			/* if not: blow it up */
			char newBuffer[] = new char[zzCurrentPos * 2];
			System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
			zzBuffer = newBuffer;
		}

		/* finally: fill the buffer with new input */
		int numRead = zzReader.read(zzBuffer, zzEndRead,
				zzBuffer.length - zzEndRead);

		if (numRead > 0) {
			zzEndRead += numRead;
			return false;
		}
		// unlikely but not impossible: read 0 characters, but not at end of
		// stream
		if (numRead == 0) {
			int c = zzReader.read();
			if (c == -1) {
				return true;
			}
			zzBuffer[zzEndRead++] = (char) c;
			return false;
		}

		// numRead < 0
		return true;
	}

	/**
	 * Closes the input stream.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null) {
			zzReader.close();
		}
	}

	/**
	 * Resets the scanner to read from a new input stream. Does not close the
	 * old reader.
	 * 
	 * All internal variables are reset, the old input stream <b>cannot</b> be
	 * reused (internal buffer is discarded and lost). Lexical state is set to
	 * <tt>ZZ_INITIAL</tt>.
	 * 
	 * @param reader
	 *            the new input stream
	 */
	public final void yyreset(java.io.Reader reader) {
		zzReader = reader;
		zzAtEOF = false;
		zzEndRead = zzStartRead = 0;
		zzCurrentPos = zzMarkedPos = 0;
		yyline = yychar = 0;
		zzLexicalState = YYINITIAL;
	}

	/**
	 * Returns the current lexical state.
	 */
	public final int yystate() {
		return zzLexicalState;
	}

	/**
	 * Enters a new lexical state
	 * 
	 * @param newState
	 *            the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Returns the character at position <tt>pos</tt> from the matched text.
	 * 
	 * It is equivalent to yytext().charAt(pos), but faster
	 * 
	 * @param pos
	 *            the position of the character to fetch. A value from 0 to
	 *            yylength()-1.
	 * 
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Returns the length of the matched text region.
	 */
	@Override
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Reports an error that occured while scanning.
	 * 
	 * In a wellformed scanner (no or only correct usage of yypushback(int) and
	 * a match-all fallback rule) this method will only be called with things
	 * that "Can't Possibly Happen". If this method is called, something is
	 * seriously wrong (e.g. a JFlex bug producing a faulty scanner etc.).
	 * 
	 * Usual syntax/scanner level error handling should be done in error
	 * fallback rules.
	 * 
	 * @param errorCode
	 *            the code of the errormessage to display
	 */
	private static void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

	/**
	 * Pushes the specified amount of characters back into the input stream.
	 * 
	 * They will be read again by then next call of the scanning method
	 * 
	 * @param number
	 *            the number of characters to be read again. This number must
	 *            not be greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength()) {
			zzScanError(ZZ_PUSHBACK_2BIG);
		}

		zzMarkedPos -= number;
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of
	 * input is encountered or an I/O-Error occurs.
	 * 
	 * @return the next token
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	public int yylex() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		while (true) {
			zzMarkedPosL = zzMarkedPos;

			yychar += zzMarkedPosL - zzStartRead;

			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = ZZ_LEXSTATE[zzLexicalState];

			zzForAction: {
				while (true) {

					if (zzCurrentPosL < zzEndReadL) {
						zzInput = zzBufferL[zzCurrentPosL++];
					} else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						}
						zzInput = zzBufferL[zzCurrentPosL++];
					}
					zzInput = zzCMapL[zzInput];

					boolean zzIsFinal = false;
					boolean zzNoLookAhead = false;

					zzForNext: {
						switch (zzState) {
						case 0:
							switch (zzInput) {
							case 1:
							case 7:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 2;
								break zzForNext;
							case 2:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 3;
								break zzForNext;
							case 3:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 4;
								break zzForNext;
							case 4:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 5;
								break zzForNext;
							case 5:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 6;
								break zzForNext;
							case 6:
								zzIsFinal = true;
								zzState = 7;
								break zzForNext;
							case 9:
								zzIsFinal = true;
								zzState = 8;
								break zzForNext;
							case 10:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 9;
								break zzForNext;
							case 11:
								zzIsFinal = true;
								zzState = 10;
								break zzForNext;
							case 12:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 11;
								break zzForNext;
							default:
								zzIsFinal = true;
								zzState = 1;
								break zzForNext;
							}

						case 1:
							switch (zzInput) {
							case 0:
							case 8:
							case 11:
								zzIsFinal = true;
								break zzForNext;
							default:
								break zzForAction;
							}

						case 7:
							switch (zzInput) {
							case 7:
								break zzForAction;
							default:
								zzIsFinal = true;
								zzState = 12;
								break zzForNext;
							}

						case 8:
							switch (zzInput) {
							case 9:
								zzIsFinal = true;
								break zzForNext;
							default:
								break zzForAction;
							}

						case 10:
							switch (zzInput) {
							case 0:
							case 8:
							case 11:
								zzIsFinal = true;
								break zzForNext;
							case 1:
								zzIsFinal = true;
								zzState = 13;
								break zzForNext;
							case 7:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 15;
								break zzForNext;
							default:
								zzState = 14;
								break zzForNext;
							}

						case 12:
							switch (zzInput) {
							case 8:
								zzIsFinal = true;
								break zzForNext;
							default:
								break zzForAction;
							}

						case 13:
							switch (zzInput) {
							case 1:
								zzIsFinal = true;
								break zzForNext;
							case 7:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 15;
								break zzForNext;
							default:
								zzState = 14;
								break zzForNext;
							}

						case 14:
							switch (zzInput) {
							case 1:
								zzIsFinal = true;
								zzState = 13;
								break zzForNext;
							case 7:
								zzIsFinal = true;
								zzNoLookAhead = true;
								zzState = 15;
								break zzForNext;
							default:
								break zzForNext;
							}

						default:
							// if this is ever reached, there is a serious bug
							// in JFlex
							zzScanError(ZZ_UNKNOWN_ERROR);
							break;
						}
					}

					if (zzIsFinal) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if (zzNoLookAhead) {
							break zzForAction;
						}
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 8: {
				return LexerConstants.WHITE;
			}
			case 11:
				break;
			case 3: {
				return LaTeXLexerConstants.SUBSUP;
			}
			case 12:
				break;
			case 4: {
				return LaTeXLexerConstants.AMP;
			}
			case 13:
				break;
			case 7: {
				return LexerConstants.TAB;
			}
			case 14:
				break;
			case 2: {
				return LaTeXLexerConstants.OPENCLOSE;
			}
			case 15:
				break;
			case 10: {
				return LaTeXLexerConstants.COMMENTS;
			}
			case 16:
				break;
			case 6: {
				return LaTeXLexerConstants.NUMBER;
			}
			case 17:
				break;
			case 1: {
				return LexerConstants.DEFAULT;
			}
			case 18:
				break;
			case 9: {
				return LaTeXLexerConstants.COMMAND;
			}
			case 19:
				break;
			case 5: {
				return LaTeXLexerConstants.DOLLAR;
			}
			case 20:
				break;
			default:
				if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
					zzAtEOF = true;
					{
						return LaTeXLexerConstants.EOF;
					}
				}
				zzScanError(ZZ_NO_MATCH);
			}
		}
	}

}
