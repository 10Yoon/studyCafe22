package com.studycafe.qna.domain;

//p641, 637
//글쓰기 요청시 사용하게 되는 클래스+유효성검사기능
public class WriteRequest {

	//필드
	private String writer;//작성자 정보
	/*String writer_id;//작성자id, 
	 String writer_name//작성자명)*/
	private String title;
	private String content;
	
	//생성자
	public WriteRequest() {}

	public WriteRequest(String writer, String title, String content) {
		this.writer=writer;
		this.title=title;
		this.content=content;
	}
	
	
	//메소드
	//getter
	public String getWriter() {
		return writer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	


//		//유효성 검사 - 필수입력체크
//		public void validate(Map<String, Boolean> errors) {
//			if(title==null || title.isEmpty()) {
//				errors.put("title", Boolean.TRUE);
//			}
//			if(content==null || content.isEmpty()) {
//				errors.put("content", Boolean.TRUE);
//			}
//		}


		@Override
		public String toString() {
			return "WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
		}


//		@Override
//		public String toString() {
//			return "WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + ", uploadFile="
//					+ uploadFile + "]";
//		}


		/*
		 * @Override public String toString() { return "WriteRequest [writer=" + writer
		 * + ", title=" + title + ", content=" + content + "]"; }
		 */
		
		
	
	
	
	
}
