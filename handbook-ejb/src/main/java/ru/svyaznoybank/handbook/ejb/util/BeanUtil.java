package ru.svyaznoybank.handbook.ejb.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.svyaznoybank.handbook.ejb.dto.DictionaryDto;
import ru.svyaznoybank.handbook.ejb.dto.EntityDto;
import ru.svyaznoybank.handbook.jpa.domain.Identity;

/**
 * @author Roman Zaripov
 */
public final class BeanUtil {
	private static final Logger LOG = Logger.getLogger(BeanUtil.class.getName());	
	
	private static final Map<Class<?>, PropertyDescriptor[]> INTROSPECTOR_CACHE = new HashMap<>();
	
	/**
	 * Copies bean properties from source to target.
	 * @param source
	 * @param target
	 * @param properties Properties that should be ignored while copying.
	 */
	public static void copyProperties(Object source, Object target, boolean ignoreProperties, String... properties) {
		try {
			PropertyDescriptor[] targetPds = INTROSPECTOR_CACHE.get(target.getClass());
			if (targetPds == null) {
				targetPds = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
				INTROSPECTOR_CACHE.put(target.getClass(), targetPds);
			}
			
			PropertyDescriptor[] sourcePds = INTROSPECTOR_CACHE.get(source.getClass());
			if (sourcePds == null) {
				sourcePds = Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors();
				INTROSPECTOR_CACHE.put(source.getClass(), sourcePds);
			}
			
			List<String> ignoreList = Arrays.asList(properties);
			
			/*
			 * PropertyDescriptor may lose information about write methods after GC work.
			 * Bug JDK-7172854? http://bugs.java.com/bugdatabase/view_bug.do?bug_id=7172854
			 * Id property is critical, so copy it directly
			 */
			if (ignoreList.contains("id") ^ ignoreProperties) {
//				ignoreList.add("id");				
				if (source instanceof Identity) {
					Serializable id = ((Identity) source).getId();				
					if (id instanceof String && target instanceof DictionaryDto) {
						((DictionaryDto) target).setId((String) id);
					} else if (id instanceof Long && target instanceof EntityDto) {
						((EntityDto) target).setId((Long) id);
					}
				}
			}
			// end bug workaroud
	
			for (PropertyDescriptor targetPd : targetPds) {		
				Method writeMethod;
				if (
					targetPd != null 
					&& (writeMethod = targetPd.getWriteMethod()) != null 
					&& (properties.length == 0 || (ignoreList.contains(targetPd.getName()) ^ ignoreProperties))
				) {
					try {
						Object value = null;
						
						Method readMethod = null;
						for (PropertyDescriptor pd : sourcePds) {
							if (pd != null && pd.getName().equals(targetPd.getName()) && (readMethod = pd.getReadMethod()) != null) {								
//								if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
									readMethod.setAccessible(true);
//								}
								value = readMethod.invoke(source);
								
//								if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
									writeMethod.setAccessible(true);
//								}						
								writeMethod.invoke(target, value);

								break;
							}
						}					
					} catch (Throwable ex) {
						// Do nothing. Ignore failed property.
						LOG.log(Level.SEVERE, "Failed to process bean property [" + targetPd.getName() + "].", ex);
					}
				}
			}
		} catch (IntrospectionException ex) {
			LOG.log(Level.SEVERE, "Bean propery copying failed", ex);
		}
	}
	
	public static void copyProperties(Object source, Object target, String... ignoreProperties) {
		copyProperties(source, target, true, ignoreProperties);
	}
}