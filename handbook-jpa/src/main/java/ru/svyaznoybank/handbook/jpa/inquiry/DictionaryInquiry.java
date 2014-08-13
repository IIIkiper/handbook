package ru.svyaznoybank.handbook.jpa.inquiry;

public class DictionaryInquiry extends Inquiry {

	private Long userId;

	// --- Getters / Setters ---
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}