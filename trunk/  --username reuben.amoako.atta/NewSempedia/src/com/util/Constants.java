/**
 * 
 */
package com.util;

/**
 * @author tauseefsyed
 *
 */
public class Constants {
	public final static String DATA_RETRIEVAL_EXCEPTION = "Could not fetch the requested data...seems like we lost a few packets in transport";
	public final static String DATA_INSERTION_EXCEPTION = "Ooops!!.... something happened, we couldnot insert your class.";
	public final static String DATA_DUPLICATION_EXCEPTION = "Seems there is something like this already existing, can't insert it for you!";
	public final static String DATA_DELETION_EXCEPTION = "Ooops!!.... something happened, we couldnot delete your class details.";
	public final static String DATABASE_DRIVER_CLASS = "db.driverClassName";
	public final static String DATABASE_URL = "db.url";
	public final static String DATABASE_USERNAME = "db.username";
	public final static String DATABASE_PASSWORD = "db.password";
	
	public final static String DATA_RETRIEVE_EXCEPTION = "Could not fetch the requested data... seems like we lost a few packets in transport.";
	public final static String DATA_INSERT_EXCEPTION = "Ooops!!.... something happened, we couldnot insert your Object.";
	public final static String DATA_DELETE_EXCEPTION = "Ooops!!.... something happened, we couldnot delete your Object details.";
	public final static String DATA_UPDATE_EXCEPTION = "Ooops!!.... something happened, we couldnot update your Object details.";
	public final static String SEARCH_NO_RESULT ="No objects found for your search on "; 
	public final static String SEARCH_ONE_RESULT ="One object found for search on ";
	public final static String SEARCH_MANY_RESULT =" objects found for search on ";
	
	//Constants added for the Image size used in the if condition and image size limit for the user message
	public final static Integer IMAGE_SIZE = 204800;
	public final static Integer IMAGE_SIZE_LIMIT = (IMAGE_SIZE/1024);
}
