/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ReplaceCharecter;

/**
 *
 * @author nazarov
 * 
 * методы для замены символов в строке(применяю в проекте Конфигуратора убираю запятую в Generator)
 * https://stackoverflow.com/questions/6952363/replace-a-character-at-a-specific-index-in-a-string
 * 
 */
public class ReplacedChar {
    public static void main(String[] args)
    {
        ReplacedChar runReplacedChar = new ReplacedChar();
        String tmpStrreplace = "1234567890";
        System.out.println(runReplacedChar.replaceCharStr(tmpStrreplace));
        System.out.println(runReplacedChar.replaceCharStrArray(tmpStrreplace));
        System.out.println(runReplacedChar.replaceCharStrBuilder(tmpStrreplace));
    }
    
    // --------------------------------------------
    public String replaceCharStr(String str)
    {
        int indeChar = 4;
        return  str.substring(0, indeChar)+'x'+str.substring(indeChar + 1); // Заменим  4 символ
    }
    
    // --------------------------------------------
    // Замена с помощью StringBuilder
    public String replaceCharStrBuilder(String str)
    {
        StringBuilder tmpStr = new StringBuilder(str);
        tmpStr.setCharAt(4, 'x'); // Заменим  4 символ
        return  tmpStr.toString(); 
    }
    
    // --------------------------------------------
    // Замена с помощью массивов символов
    public String replaceCharStrArray(String str)
    {
        char[] myNameChars = str.toCharArray();
        myNameChars[4] = 'x';
        str = String.valueOf(myNameChars);
        return  str; 
    }
    
}
