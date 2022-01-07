package com.rohfl.logansquaresample.model;

import java.util.List;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ResponseGetQuoteModel{

	@JsonField(name ="authorSlug")
	private String authorSlug;

	@JsonField(name ="author")
	private String author;

	@JsonField(name ="length")
	private int length;

	@JsonField(name ="dateModified")
	private String dateModified;

	@JsonField(name ="_id")
	private String id;

	@JsonField(name ="content")
	private String content;

	@JsonField(name ="dateAdded")
	private String dateAdded;

	@JsonField(name ="tags")
	private List<String> tags;

	public String getAuthorSlug(){
		return authorSlug;
	}

	public String getAuthor(){
		return author;
	}

	public int getLength(){
		return length;
	}

	public String getDateModified(){
		return dateModified;
	}

	public String getId(){
		return id;
	}

	public String getContent(){
		return content;
	}

	public String getDateAdded(){
		return dateAdded;
	}

	public List<String> getTags(){
		return tags;
	}
}