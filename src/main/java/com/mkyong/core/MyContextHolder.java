package com.mkyong.core;

import org.springframework.util.Assert;

public class MyContextHolder {
	private static final ThreadLocal<Env> contextHolder = 
            new ThreadLocal<Env>();
	
   public static void setCustomerType(Env customerType) {
      Assert.notNull(customerType, "customerType cannot be null");
      contextHolder.set(customerType);
   }

   public static Env getCustomerType() {
      return (Env) contextHolder.get();
   }

   public static void clearCustomerType() {
      contextHolder.remove();
   }
}
