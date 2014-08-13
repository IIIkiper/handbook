package ru.svyaznoybank.handbook.war.gwt.rest;

import ru.svyaznoybank.handbook.war.gwt.json.Dto;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.sencha.gxt.data.client.loader.HttpProxy;

public class RestProxy<C, T extends Dto> extends HttpProxy<C> {
	
	private final RequestBuilder postRB;
	
	private final String contentType;
	
	private RestProxy(String uri, String contentType) {
		super(new RequestBuilder(RequestBuilder.GET, uri));
		this.contentType = contentType;
		this.builder.setHeader("Accept", contentType);
		
		postRB = new RequestBuilder(RequestBuilder.POST, uri);
		postRB.setHeader("Content-Type", contentType);
		postRB.setHeader("Accept", contentType);
	}

	public RestProxy(String uri) {
		this(uri, "application/json");
	}
	
	public void post(AutoBean<T> autobean, RestCallback<T> callback, Class<T> clazz) {
		send(postRB, autobean, callback, clazz);
	}
	
	public void put(AutoBean<T> autobean, RestCallback<T> callback, Class<T> clazz) {
		RequestBuilder putRB = new RequestBuilder(RequestBuilder.PUT, initUrl + '/' + autobean.as().getId());
		putRB.setHeader("Content-Type", contentType);
		putRB.setHeader("Accept", contentType);
		send(putRB, autobean, callback, clazz);
	}
	
	public void delete(AutoBean<T> autobean, RequestCallback callback) {
		delete(autobean.as(), callback);
	}
	
	public void delete(Dto dto, final RequestCallback callback)  {
		try {
			RequestBuilder deleteRB = new RequestBuilder(RequestBuilder.DELETE, initUrl + '/' + dto.getId());
//			deleteRB.setHeader("Content-Type", contentType);
//			deleteRB.setHeader("Accept", contentType);
			deleteRB.sendRequest(null, new RequestCallback() {	
				@Override
				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
						callback.onResponseReceived(request, response);
					} else {
						callback.onError(request, new RuntimeException("Error occured. HTTP code [" + response.getStatusCode() + "]."));
					}
				}
				@Override
				public void onError(Request request, Throwable exception) {
					callback.onError(request, exception);
				}
			});
		} catch (Exception ex) {
			callback.onError(null, ex);
		}
	}
	
	// --- Private methods ---
	private void send(RequestBuilder rb, final AutoBean<T> autobean, final RestCallback<T> callback, final Class<T> clazz) {
		try {
			String json = AutoBeanCodex.encode(autobean).getPayload();
			rb.sendRequest(json, new RequestCallback() {			
				@Override
				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
						AutoBean<T> bean = AutoBeanCodex.decode(autobean.getFactory(), clazz, response.getText());
						callback.onResponseReceived(request, bean.as());
					} else /*if (response.getStatusCode() == 403)*/ {
						callback.onError(new RuntimeException("Error occured. HTTP code [" + response.getStatusCode() + "]."));
					}
				}				
				@Override
				public void onError(Request request, Throwable exception) {
					callback.onError(exception);
				}
			});
		} catch (Exception ex) {
			callback.onError(ex);
		}		
	}
}