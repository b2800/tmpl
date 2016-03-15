package Model.Builder;

import Model.Layout;

public class LayoutBuilder {

    public static Layout BuildMainMenuLayout() {
        Layout menu = new Layout();
        
        return menu;
    }
    
    public static Layout BuildSoloPlayerLayout(){
        
        return null;
    }
    
    public static Layout BuildLocalMultiplayerLayout(int player_count){
        
        return null;
    }
    
    public static Layout BuildNetworkMultiplayerLayout(boolean is_server){
        
        return null;
    }
}
