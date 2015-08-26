/**
 * 
 */
package com.iss.ketan.imp;

/**
 * @author ketan
 * 
 */
public interface WebImportProcessStateIfc
{
	public static final int PROCESS_NOT_STARTED = 0;
	public static final int PROCESS_STAGE_FILE_READING = 1;
	public static final int PROCESS_STAGE_FILE_PARSING_1 = 2;
	public static final int PROCESS_STAGE_DATA_CONVERTING = 3;
	public static final int PROCESS_STAGE_DATA_PERSISTING = 4;
}
