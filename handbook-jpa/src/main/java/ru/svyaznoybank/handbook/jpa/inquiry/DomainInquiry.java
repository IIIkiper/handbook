package ru.svyaznoybank.handbook.jpa.inquiry;

import ru.svyaznoybank.handbook.jpa.domain.Identity;

public abstract class DomainInquiry<T extends Identity> extends Inquiry {
	
	private T example;

	// --- Getters / Setters ---
	public T getExample() {
		return example;
	}
	public void setExample(T example) {
		this.example = example;
	}
}