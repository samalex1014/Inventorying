/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorying;

/**
 *
 * @author sam
 */
public class Computer {
    static int SERIAL = 0;
    static int MAC = 1;
    static int HOST = 2;
    static int ASSET = 3;
    static int USER = 4;
    static int NUMATTR = 5;
    private String[] attr;
    private boolean surplussed;
    private boolean active;
    private boolean unverified;
    
    public Computer() {
        attr = new String[NUMATTR];
        surplussed = false;
        active = false;
        unverified = true;
    }
    
    public Computer(String serial, String mac, String host, String asset, String user) {
        attr = new String[NUMATTR];
        attr[SERIAL] = serial;
        attr[MAC] = mac;
        attr[HOST] = host;
        attr[ASSET] = asset;
        attr[USER] = user;
        
        surplussed = false;
        active = false;
        unverified = true;
    }
    
    public Computer(String serial, String mac, String host, String asset) {
        attr = new String[NUMATTR];
        attr[SERIAL] = serial;
        attr[MAC] = mac;
        attr[HOST] = host;
        attr[ASSET] = asset;
        
        surplussed = false;
        active = false;
        unverified = true;
    }
    
    public Computer(String serial, String mac, String host) {
        attr = new String[NUMATTR];
        attr[SERIAL] = serial;
        attr[MAC] = mac;
        attr[HOST] = host;
        attr[ASSET] = "";
        
        surplussed = false;
        active = false;
        unverified = true;
    }
    
    public Computer(String serial, String mac) {
        attr = new String[NUMATTR];
        attr[SERIAL] = serial;
        attr[MAC] = mac;
        attr[HOST] = "";
        attr[ASSET] = "";
        
        surplussed = false;
        active = false;
        unverified = true;
    }
    
    public void verify() {
        if(this.surplussed == true || this.active == true) {
            unverified = false;
        }
    }
    
    public void checkSerial(String serial, boolean surplus, boolean inUse) {
        if(this.attr[SERIAL].equals(serial)) {
            if(surplus) {
                this.surplussed = true;
                this.unverified = false;
            } else if (inUse) {
                this.active = true;
                this.unverified = false;
            }
        }
    }
    
    public void checkMac(String mac, boolean surplus, boolean inUse) {
        if(this.attr[MAC].equals(mac)) {
            if(surplus) {
                this.surplussed = true;
                this.unverified = false;
            } else if (inUse) {
                this.active = true;
                this.unverified = false;
            }
        }
    }
    
    public void checkHost(String host, boolean surplus, boolean inUse) {
        if(this.attr[HOST].equals(host)) {
            if(surplus) {
                this.surplussed = true;
                this.unverified = false;
            } else if (inUse) {
                this.active = true;
                this.unverified = false;
            }
        }
                
    }
    
    public void checkAsset(String asset, boolean surplus, boolean inUse) {
        if(this.attr[ASSET].equals(asset)) {
            if(surplus) {
                this.surplussed = true;
                this.unverified = false;
            } else if (inUse) {
                this.active = true;
                this.unverified = false;
            }
        }
    }
    
    public String printInfo(){
        String res = "";
        
        for (String attr1 : this.attr) {
            res += attr1 + ",";
        }
        
        return res;
    }
    
    public boolean active() {
        return this.active;
    }
    
    public String assetTag() {
        return this.attr[ASSET];
    }
    
    public boolean surplussed() {
        return this.surplussed;
    }
    
    public boolean verified() {
        return !this.unverified;
    }
    
    public String getSerial() {
        return this.attr[SERIAL];
    }
    
    public String getMac() {
        return this.attr[MAC];
    }
    
    public String getHost() {
        return this.attr[HOST];
    }
}
