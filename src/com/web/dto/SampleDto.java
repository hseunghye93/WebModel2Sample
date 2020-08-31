package com.web.dto;

public class SampleDto {

	private int num; // pk
	private String writer; // 작성자
	private String title; // 제목
	private String contents; // 내용
	private int counts; // 조회수
	private int ref; //
	private int step; //
	private int depth; // 
	private String regDate; // 등록날짜
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "SampleDto [num=" + num + ", writer=" + writer + ", title=" + title + ", contents=" + contents
				+ ", counts=" + counts + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", regDate=" + regDate
				+ "]";
	}
}
