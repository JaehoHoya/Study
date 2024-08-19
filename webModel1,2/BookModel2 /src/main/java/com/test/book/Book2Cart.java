package com.test.book;


import java.util.*;

public class Book2Cart
{
   private List<Book2> list = new ArrayList<>();
   
   public boolean add(Book2 b) {
      Book2 found = find(b);
      
      if(found==null) list.add(b);
      else {
         found.setQty(found.getQty()+b.getQty());
      }
      return true;
   }
   
   public Book2 find(Book2 b) {
      if(list.contains(b)) {
         return list.get(list.indexOf(b));
      }
      return null;
   }
   
   public int getTotalPrice() {  // ${cart.totalprice}
      int total = 0;
      for(int i=0;i<list.size();i++) {
         total += list.get(i).getPrice()*list.get(i).getQty();
      }
      return total;
   }
   
   public List<Book2> getItems() {
      return list;
   }
   
   public boolean updateQty(Book2 b) {
      if(list.contains(b)) {
         Book2 found = list.get(list.indexOf(b));
         found.setQty(b.getQty());
         return true;
      }
      return false;
   }
   
   public boolean deleteItems(String delItems) {
	      String[] items = delItems.trim().split("\\,");
	      int delcnt = items.length;
	      int cnt = 0;
	      for(int i=0;i<items.length;i++) {
	         int no = Integer.parseInt(items[i]);
	         Book2 delbook = new Book2(no);
	         if(list.contains(delbook)) {
	            list.remove(delbook);
	            cnt++;
	         }
	      }
	      return delcnt==cnt;
	   }
   
   
   
   
   
	}


