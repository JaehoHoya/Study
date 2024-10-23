package PDS;

import java.io.Serializable;
import java.util.List;

// 서버측에서 클라이언트에 전달되는 모든 응답 데이터를 포함하는 클래스
public class PDSResponse implements Serializable {

   String Menu ;

   String response;

   List<PDSVO> list;

   public String getMenu() {
      return Menu;
   }

   public void setMenu(String menu) {
      Menu = menu;
   }

   public String getResponse() {
      return response;
   }

   public void setResponse(String response) {
      this.response = response;
   }

   public List<PDSVO> getList() {
      return list;
   }

   public void setList(List<PDSVO> list) {
      this.list = list;
   }
}
