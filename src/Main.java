import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	  
	  public static void main(String... aArgs) throws IOException {
		  
		  ArrayList<String> list = new ArrayList<String>();
		  int counth1 = 0;
		  int count_ = 0;
		  int count_bold = 0;
		  int countp = 0;
		  int count_link_text = 0;
		  int count_image_text = 0;
		  String link_text = "";
		  String image_text = "";
		  Scanner s = new Scanner(new File("input.txt"));
		  
		  try   //programýn dosyayý okuma testi
		  {			  
		  while (s.hasNext())
		  {
		      list.add(s.next());
		  }
		  }
		  catch(Exception filexception)
		  {
			  filexception.printStackTrace();
		  }
		  finally
		  {
			  s.close();
		  }
		  
		  try (Writer wr = new FileWriter("output.html"))   // dosyaya yazma testi
		  {
		  for(int i = 0; i < list.size(); i++)
		  {
			  
			  /*----------------- ] link metni kapama ozelligi sýnanýyor.--------------- */
			  if(count_link_text == 1)
			  {
				  if(list.get(i).contains("]"))
				  {
					  
					  int selected_1 = 0;
					  int selected_2 = 0;
					  int selected_3 = 0;
				      
				      if(list.get(i).contains("]"))  // ayrýþtýrýlan text te açýlan link metni iþaretçisinin ayný satýrda kapanma durumunu test eder.
				      {
				      selected_1 = list.get(i).indexOf("]");			            	  
				      link_text = link_text + " " + list.get(i).substring(0, selected_1);
				      count_link_text = 0;
				      
				      if(list.get(i).contains("("))
				      {
					      selected_2 = list.get(i).indexOf("(");
					      selected_3 = list.get(i).indexOf(")");
					      
					      String url = list.get(i).substring(selected_2 + 1, selected_3);
					      
					      if(!url.isEmpty())
					      {
					      String deleted_link_text = "" + list.get(i).substring(selected_1+1) ;
					      String replace = deleted_link_text.replace("(", "<a href=" + '"').replace(")", '"' + ">" + link_text + "</a>");
					      list.set(i, replace);
					      }
				      }
				      
				     link_text = "";
				     
				  }
				  }
				  
				  else
				  {
				     link_text = link_text + " " + list.get(i).toString();
				     list.set(i, "");
				     count_link_text = 1;
				  }
				  
			  }
			  
			  /*----------------- ] image metni kapama ozelligi sýnanýyor.--------------- */
			  if(count_image_text == 1)
			  {
				  if(list.get(i).contains("]"))
				  {
					  
					  int selected_1 = 0;
					  int selected_2 = 0;
					  int selected_3 = 0;
				      
				      if(list.get(i).contains("]"))  // ayrýþtýrýlan text te açýlan link metni iþaretçisinin ayný satýrda kapanma durumunu test eder.
				      {
				      selected_1 = list.get(i).indexOf("]");			            	  
				      image_text = image_text + " " + list.get(i).substring(0, selected_1);
				      count_image_text = 0;
				      
				      if(list.get(i).contains("("))
				      {
					      selected_2 = list.get(i).indexOf("(");
					      selected_3 = list.get(i).indexOf(")");
					      
					      String url = list.get(i).substring(selected_2 + 1, selected_3);
					      
					      if(!url.isEmpty())
					      {
					      String deleted_image_text = "" + list.get(i).substring(selected_1+1) ;
					      String replace = deleted_image_text.replace("(", "<img src=" + '"').replace(")", '"' + " " + "alt=" + '"' + image_text + '"' + ">");
					      list.set(i, replace);
					      }
				      }
				      
				     image_text = "";
				     
				  }
				  }
				  
				  else
				  {
					  image_text = image_text + " " + list.get(i).toString();
				     list.set(i, "");
				     count_image_text = 1;
				  }
				  
			  }
			  
			  
			  /*----------------- <p> ozelligi sýnanýyor.--------------- */
			  if(countp == 0)
			  {
				  String newString = "<p>" + list.get(i);
				  list.set(i, newString);
				  countp = 1;
			  }
			  
			  /*----------------- </p> ozelligi sýnanýyor.--------------- */
			  if(list.get(i).contains("\\n"))
			  {
				  String replace = list.get(i).replace("\\n", "</p>");
				  list.set(i, replace);
				  countp = 0;
			  }
			  
			  /*----------------- <h1> ozelligi sýnanýyor.--------------- */
			  if(list.get(i).contains("***"))
			  {
				  if(counth1 == 1)
				  {
					  String replace = list.get(i).replace("***", "</h1>");
					  list.set(i, replace);
					  counth1 = 0;
				  }
				  else
				  {
				  
				  int lastIndex = 0;
				  int count = 0;
				  int index = 0;
				  
				  /*----------------- ayný satýrda iki *** gelmiþse ikincisinin indexini buluyor ve </h1> ile deðiþtiriyor. --------------- */
				  while(lastIndex != -1){

					    lastIndex = list.get(i).indexOf("***", lastIndex);

					    if(lastIndex != -1){
					        count ++;					        
    				        lastIndex += "***".length();
    				        index = lastIndex - 3;
					    }
					}
				  
				  if(count > 1)
				  {
					  String replace = list.get(i).substring(0, index) + "</h1>" + list.get(i).substring(index+3);
					  list.set(i, replace);
					  counth1 = 0;
				  }
				  
				  
				  String replace = list.get(i).replace("***", "<h1>");
				  list.set(i, replace);
				  counth1 = 1;
				  
				  }
			  }
			  
			  /*----------------- <em> ozelligi sýnanýyor.--------------- */
			  if(list.get(i).contains("_"))
			  {
				  if(count_ == 1)
				  {
					  String replace = list.get(i).replace("_", "</em>");
					  list.set(i, replace);
					  count_ = 0;
				  }
			     else
				  {
			      char toCheck = '_';
			      int count = 0;
			      int selected = 0;				 
			      
			      /*----------------- gelen kelimede aranan ozellik iki taneyse (oluþturulup bitirilmiþse)  --------------- */
			      for (char ch: list.get(i).toCharArray()) { 
			          if (ch == toCheck) {
			              count++;
			              
			              if(count == 2)
			              {
			            	  try //index'in array sýnýrlarý içinde olup olmadýðý kontrol ediliyor.
			            	  {
			            		  selected = list.get(i).lastIndexOf(toCheck);
			            		  String replace = list.get(i).substring(0, selected) + "</em>" + list.get(i).substring(selected+1);
			            		  list.set(i, replace);
			            		  count_ = 0;
			            	  }
			            	  catch(ArrayIndexOutOfBoundsException ex)
			            	  {
			            		  ex.printStackTrace();
			            	  }
			              }
			          }
			      }
				  
			      if(count == 2)
			      {
					  String replace = list.get(i).replace("_", "<em>");
					  list.set(i, replace);
					  count_ = 0;
			      }
			      else
			      {
					  String replace = list.get(i).replace("_", "<em>");
					  list.set(i, replace);
					  count_ = 1;
			      }

				  }				
				  
			  }
			  
			  /*----------------- <strong> ozelligi sýnanýyor.--------------- */
			  if(list.get(i).contains("*"))
			  {
				  if(count_bold == 1)
				  {
					  String replace = list.get(i).replace("*", "</strong>");
					  list.set(i, replace);
					  count_bold = 0;
				  }
				  
				  else
			      {
				      char toCheck = '*';
				      int count = 0;
				      int selected = 0;				 
				      
				      /*----------------- gelen kelimede aranan ozellik iki taneyse (oluþturulup bitirilmiþse)  --------------- */
				      for (char ch: list.get(i).toCharArray()) { 
				          if (ch == toCheck) {
				              count++;
				              
				              if(count == 2)
				              {
				            	  try //index'in array sýnýrlarý içinde olup olmadýðý kontrol ediliyor.
				            	  {
				            		  selected = list.get(i).lastIndexOf(toCheck);
				            		  String replace = list.get(i).substring(0, selected) + "</strong>" + list.get(i).substring(selected+1);
				            		  list.set(i, replace);
				            		  count_bold = 0;
				            	  }
				            	  catch(ArrayIndexOutOfBoundsException ex)
				            	  {
				            		  ex.printStackTrace();
				            	  }
				              }
				          }
				      }
					  
				      if(count == 2)
				      {
						  String replace = list.get(i).replace("*", "<strong>");
						  list.set(i, replace);
						  count_bold = 0;
				      }
				      else
				      {
						  String replace = list.get(i).replace("*", "<strong>");
						  list.set(i, replace);
						  count_bold = 1;
				      }

					  }	
			  }
			  
			  
			  /*----------------- resim metni ozelligi sýnanýyor.--------------- */
			  if(list.get(i).contains("!["))
			  {
				 
				  int selected_1 = 0;
				  int selected_2 = 0;
				  int selected_3 = 0;
				  int selected_4 = 0;

			      selected_1 = list.get(i).indexOf("[");
			      
			      if(list.get(i).contains("]"))  // ayrýþtýrýlan text te açýlan link metni iþaretçisinin ayný satýrda kapanma durumunu test eder.
			      {
			      selected_2 = list.get(i).indexOf("]");			            	  
			      image_text = list.get(i).substring(selected_1 + 1, selected_2);
			      count_image_text = 0;
			      
			      if(list.get(i).contains("("))
			      {
				      selected_3 = list.get(i).indexOf("(");
				      selected_4 = list.get(i).indexOf(")");
				      
				      String url = list.get(i).substring(selected_3 + 1, selected_4);
				      if(!url.isEmpty())
				      {
				      String deleted_image_text = list.get(i).substring(0, selected_1 - 1) + "" + list.get(i).substring(selected_2 + 1);
				      String replace = deleted_image_text.replace("(", "<img src=" + '"').replace(")", '"' + " " + "alt=" + '"' + image_text + '"' + ">");
				      list.set(i, replace);
				      }
			      }
			      
			      image_text ="";
			      
			      }
			      else
			      {
			    	  image_text = list.get(i).substring(selected_1 +1);
			    	  String deleted_image_text = list.get(i).substring(0, selected_1 - 1) + "";
			    	  list.set(i, deleted_image_text);
			    	  count_image_text = 1;  //bunun anlamý bir link metni iþaretçisi açýlmýþ ve kapanmamýþ.
			      }
			  }
			  
			  
			  /*----------------- link metni sýnanýyor.--------------- */
			  if(list.get(i).contains("["))
			  {
				 
				  int selected_1 = 0;
				  int selected_2 = 0;
				  int selected_3 = 0;
				  int selected_4 = 0;

			      selected_1 = list.get(i).indexOf("[");
			      
			      if(list.get(i).contains("]"))  // ayrýþtýrýlan text te açýlan link metni iþaretçisinin ayný satýrda kapanma durumunu test eder.
			      {
			      selected_2 = list.get(i).indexOf("]");			            	  
			      link_text = list.get(i).substring(selected_1 + 1, selected_2);
			      count_link_text = 0;
			      
			      if(list.get(i).contains("("))
			      {
				      selected_3 = list.get(i).indexOf("(");
				      selected_4 = list.get(i).indexOf(")");
				      
				      String url = list.get(i).substring(selected_3 + 1, selected_4);
				      if(!url.isEmpty())
				      {
				      String deleted_link_text = list.get(i).substring(0, selected_1) + "" + list.get(i).substring(selected_2 + 1);
				      String replace = deleted_link_text.replace("(", "<a href=" + '"').replace(")", '"' + ">" + link_text + "</a>");
				      list.set(i, replace);
				      }
			      }
			      
			      link_text ="";
			      
			      }
			      else
			      {
			    	  link_text = list.get(i).substring(selected_1 +1);
			    	  String deleted_link_text = list.get(i).substring(0, selected_1) + "";
			    	  list.set(i, deleted_link_text);
			    	  count_link_text = 1;  //bunun anlamý bir link metni iþaretçisi açýlmýþ ve kapanmamýþ.
			      }
			  }
			  

			  wr.write(list.get(i).toString() + " "); // write string

			
		  
			  System.out.println(list.get(i).toString());
			  
		  }		  
		  
		  wr.flush();
		  wr.close();
		  
		  }
		  catch(IOException ex)  //yazma testinin hatasý yakalanýyor.
		  {
			  ex.printStackTrace();
		  }
		 
	  }	 

}
