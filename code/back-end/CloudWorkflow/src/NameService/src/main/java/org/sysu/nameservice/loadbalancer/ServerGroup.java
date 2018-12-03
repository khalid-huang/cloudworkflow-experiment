package org.sysu.nameservice.loadbalancer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: Gordan Lin
 * @create: 2018/11/30
 **/
public class ServerGroup {

    private Set<Server> serverSets;

    private long lastMod;

    private long lastUse;

    public ServerGroup() {
        serverSets = new HashSet<>();
        lastMod = new Date().getTime();
    }

    public Set<Server> getServerSets() {
        return serverSets;
    }

    public void addServerToServerSets(Server server) {
        if (serverSets == null) {
            serverSets = new HashSet<>();
        }
        serverSets.add(server);
        lastMod = new Date().getTime();
    }

    public void deleteServerFromServerSets(Server server) {
        if (serverSets != null) {
            serverSets.remove(server);
        }
        lastMod = new Date().getTime();
    }

    public long getLastMod() {
        return lastMod;
    }

    public void setLastMod(long lastMod) {
        this.lastMod = lastMod;
    }

    public long getLastUse() {
        return lastUse;
    }

    public void setLastUse(long lastUse) {
        this.lastUse = lastUse;
    }
}
