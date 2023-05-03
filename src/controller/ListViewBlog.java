/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Blog;
import javafx.scene.control.ListCell;

/**
 *
 * @author hp
 */
public class ListViewBlog extends ListCell<Blog> {
    
    
     @Override
     public void updateItem(Blog e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            BlogItemController data = new BlogItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
