package ru.svyaznoybank.handbook.war.gwt;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageBundle extends ClientBundle {
	
	@Source("img/users.png")
	ImageResource users();
	
	@Source("img/clear.png")
	ImageResource clear();
	
	@Source("img/search.png")
	ImageResource search();
	
	@Source("img/money.png")
	ImageResource money();
	
	@Source("img/document.png")
	ImageResource document();
	
	@Source("img/cart.png")
	ImageResource cart();
	
	@Source("img/calculator.png")
	ImageResource calculator();
	
	@Source("img/list.png")
	ImageResource list();
	
	@Source("img/offer.png")
	ImageResource offer();
	
	@Source("img/log-upload.png")
	ImageResource logUpload();
	
	@Source("img/log.png")
	ImageResource log();
	
	@Source("img/list-upload.png")
	ImageResource listUpload();
	
	@Source("img/user-upload.png")
	ImageResource userUpload();
	
	@Source("img/offer-upload.png")
	ImageResource offerUpload();
	
	@Source("img/add.png")
	ImageResource add();
	
	@Source("img/edit.png")
	ImageResource edit();
	
	@Source("img/delete.png")
	ImageResource delete();
	
	@Source("img/user-suit.png")
	ImageResource client();
	
	@Source("img/vcard.png")
	ImageResource vcard();
	
	@Source("img/user-add.png")
	ImageResource userAdd();
	
	@Source("img/user-delete.png")
	ImageResource userDelete();
}