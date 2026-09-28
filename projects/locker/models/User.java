package projects.locker.models;

import java.util.*;
import projects.locker.enums.*;

public class User {
    private final String id;
    private final String name;
    private final UserType type;
    private final List<Parcel> parcels;
    private final UserLevel level;

    public User(String name, UserType type, UserLevel level) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.level = level;
        this.parcels = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }

    public UserType getType(){
        return this.type;
    }

    public UserLevel getLevel(){
        return this.level;
    }

    public List<Parcel> getAllParcels(){
        return this.parcels;
    }

    public void addParcel(Parcel parcel){
        if(parcel == null){
            throw new IllegalArgumentException("Cannot add a null parcel");
        }
        if(this.parcels.contains(parcel)){
            throw new IllegalStateException("User already has the parcel");
        }
        this.parcels.add(parcel);
    }

    public void removeParcel(Parcel parcel){
        if(parcel == null){
            throw new IllegalArgumentException("Cannot remove a null parcel");
        }
        if(!parcels.contains(parcel)){
            throw new IllegalArgumentException("Cannot remove a parcel which is not present here");
        }
        this.parcels.remove(parcel);
    }
}
