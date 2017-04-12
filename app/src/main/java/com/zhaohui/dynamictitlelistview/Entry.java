package com.zhaohui.dynamictitlelistview;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Entry implements Comparable<Entry>{
	
	private String title;
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(@NonNull Entry o) {
		return title.compareTo(o.getTitle());
	}
}
